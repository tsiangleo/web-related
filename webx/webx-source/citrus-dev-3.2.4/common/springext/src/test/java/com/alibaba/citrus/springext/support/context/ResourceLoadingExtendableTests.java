/*
 * Copyright (c) 2002-2012 Alibaba Group Holding Limited.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.citrus.springext.support.context;

import static com.alibaba.citrus.test.TestEnvStatic.*;
import static com.alibaba.citrus.test.TestUtil.*;
import static com.alibaba.citrus.util.ArrayUtil.*;
import static com.alibaba.citrus.util.CollectionUtil.*;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;
import javax.servlet.ServletContext;

import com.alibaba.citrus.springext.ResourceLoadingExtendable;
import com.alibaba.citrus.test.runner.Prototyped;
import com.alibaba.citrus.test.runner.Prototyped.Prototypes;
import com.alibaba.citrus.test.runner.Prototyped.TestData;
import com.alibaba.citrus.test.runner.Prototyped.TestName;
import com.alibaba.citrus.util.internal.InterfaceImplementorBuilder;
import com.meterware.servletunit.ServletRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourcePatternResolver;

@RunWith(Prototyped.class)
public class ResourceLoadingExtendableTests implements Cloneable {
    private ResourceLoadingExtendable context;
    private Resource                  resource1;
    private boolean                   defaultServletResource;

    @Prototypes
    public static TestData<ResourceLoadingExtendableTests> data() throws Exception {
        TestData<ResourceLoadingExtendableTests> data = TestData.getInstance(ResourceLoadingExtendableTests.class);
        ResourceLoadingExtendableTests prototype;

        // XmlApplicationContext
        prototype = data.newPrototype();
        prototype.context = new XmlApplicationContext(new FileSystemResource(new File(srcdir, "beans.xml")));
        prototype.defaultServletResource = false;

        // XmlWebApplicationContext
        prototype = data.newPrototype();
        XmlWebApplicationContext factory = new XmlWebApplicationContext();
        factory.setConfigLocation("beans.xml");
        factory.setServletContext(createServletContextWrapper(new ServletRunner(new File(srcdir, "WEB-INF/web.xml"), "")
                                                                      .newClient().newInvocation("http://localhost/servlet").getServlet().getServletConfig()
                                                                      .getServletContext()));
        factory.refresh();
        prototype.context = factory;
        prototype.defaultServletResource = true;

        return data;
    }

    @TestName
    public String testName() {
        return context.getClass().getSimpleName();
    }

    @Before
    public void init() {
        resource1 = new UrlResource(getClass().getClassLoader().getResource("java/util/List.class"));
    }

    @Test
    public void noExtender() throws Exception {
        // classpath前缀
        assertResource("classpath:java/lang/String.class", "jar:", "java/lang/String.class");

        // 无classpath前缀
        assertResource("java/lang/String.class", contextResourceType(), "java/lang/String.class");

        // resource pattern
        if (defaultServletResource) {
            assertResources("WEB-INF/*", "web.xml", "web_withRedirect.xml"); // 用spring servlet context resolver
        } else {
            assertResources("WEB-INF/*"); // 用 class resolver，not found
        }
    }

    @Test
    public void extender_returnsNull() throws Exception {
        MyResourceLoadingExtender loader = new MyResourceLoadingExtender();
        context.setResourceLoadingExtender(loader);

        // classpath前缀
        assertResource("classpath:java/lang/String.class", "jar:", "java/lang/String.class");

        // 无classpath前缀
        assertResource("java/lang/String.class", contextResourceType(), "java/lang/String.class");

        // resource pattern
        if (defaultServletResource) {
            assertResources("WEB-INF/*", "web.xml", "web_withRedirect.xml"); // 用spring servlet context resolver
        } else {
            assertResources("WEB-INF/*"); // 用 class resolver，not found
        }
    }

    @Test
    public void extender_returnsNotNull() throws Exception {
        MyResourceLoadingExtender loader = new MyResourceLoadingExtender();
        context.setResourceLoadingExtender(loader);
        loader.setResource(resource1);

        // classpath前缀：永远返回原来的值
        assertResource("classpath:java/lang/String.class", "jar:", "java/lang/String.class");

        // 无classpath前缀
        assertResource("java/lang/String.class", contextResourceType(), "java/util/List.class");

        // resource pattern
        ResourcePatternResolver resolver = createMock(ResourcePatternResolver.class);
        expect(resolver.getResources("WEB-INF/*")).andReturn(
                new Resource[] { new FileSystemResource(new File(srcdir, "beans.xml")) }).anyTimes();

        replay(resolver);

        loader.setResolver(resolver);

        assertResources("WEB-INF/*", "beans.xml");
    }

    @Test
    public void extender_listener() {
        AbstractApplicationContext ac = (AbstractApplicationContext) context;

        // set loader2
        MyResourceLoadingExtender2 loader2 = new MyResourceLoadingExtender2();
        context.setResourceLoadingExtender(loader2);

        // check listener
        assertTrue(new GetApplicationListeners(ac).invoke().contains(loader2));

        // listener be notified
        ac.refresh();
        assertTrue(loader2.contextRefreshed);
    }

    @Test
    public void extender_reset() {
        AbstractApplicationContext ac = (AbstractApplicationContext) context;

        // set loader
        MyResourceLoadingExtender loader = new MyResourceLoadingExtender();
        context.setResourceLoadingExtender(loader);

        assertFalse(new GetApplicationListeners(ac).invoke().contains(loader));

        // set loader2
        MyResourceLoadingExtender2 loader2 = new MyResourceLoadingExtender2();
        context.setResourceLoadingExtender(loader2);

        assertTrue(new GetApplicationListeners(ac).invoke().contains(loader2));

        // set loader2
        MyResourceLoadingExtender2 loader3 = new MyResourceLoadingExtender2();
        context.setResourceLoadingExtender(loader3);

        assertFalse(new GetApplicationListeners(ac).invoke().contains(loader2));
        assertTrue(new GetApplicationListeners(ac).invoke().contains(loader3));

        // listener be notified
        ac.refresh();
        assertFalse(loader2.contextRefreshed);
        assertTrue(loader3.contextRefreshed);
    }

    private String contextResourceType() {
        return defaultServletResource ? "file:" : "jar:";
    }

    private void assertResource(String resourceName, String... strings) throws Exception {
        Resource resource = context.getResource(resourceName);
        assertThat(resource.getURL().toString(), containsAll(strings));
    }

    private void assertResources(String resourceName, String... regexps) throws Exception {
        Resource[] resources;

        try {
            resources = context.getResources(resourceName);
        } catch (IOException e) {
            resources = new Resource[0];
        }

        assertEquals(regexps.length, resources.length);

        Set<String> regexpSet = createHashSet(regexps);

        for (int i = 0; i < resources.length; i++) {
            for (int j = 0; j < regexps.length; j++) {
                String regexp = regexps[j];

                try {
                    assertThat(resources[i].getURL().toString(), containsRegex(regexp));
                    regexpSet.remove(regexp);
                } catch (AssertionError e) {
                }
            }
        }

        assertTrue(regexpSet.isEmpty());
    }

    /**
     * httpunit未实现getResourcePaths方法。
     * 以下实现可在servlet 2.5/3.0上执行。
     */
    private static ServletContext createServletContextWrapper(final ServletContext servletContext) {
        return (ServletContext) new InterfaceImplementorBuilder().addInterface(ServletContext.class).toObject(new Object() {
            public Set<String> getResourcePaths(String path) {
                try {
                    URL baseURL = servletContext.getResource(path);
                    File basedir = null;

                    if (baseURL != null) {
                        basedir = new File(baseURL.toURI());
                    } else {
                        return null;
                    }

                    String[] names = basedir.list();

                    if (isEmptyArray(names)) {
                        return null;
                    }

                    Set<String> nameSet = createHashSet();

                    if (!path.endsWith("/")) {
                        path += "/";
                    }

                    for (String name : names) {
                        File file = new File(basedir, name);
                        nameSet.add(path + name + (file.isDirectory() ? "/" : ""));
                    }

                    return nameSet;
                } catch (Exception e) {
                    return null;
                }
            }
        }, servletContext);
    }
}
