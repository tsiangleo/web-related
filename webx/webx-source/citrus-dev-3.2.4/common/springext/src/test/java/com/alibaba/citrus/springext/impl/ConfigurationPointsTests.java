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

package com.alibaba.citrus.springext.impl;

import static com.alibaba.citrus.test.TestUtil.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import com.alibaba.citrus.springext.ConfigurationPoint;
import com.alibaba.citrus.springext.ConfigurationPointException;
import com.alibaba.citrus.test.TestEnvStatic;
import com.alibaba.citrus.test.runner.TestNameAware;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(TestNameAware.class)
public class ConfigurationPointsTests {
    private ConfigurationPointsImpl cps;

    static {
        TestEnvStatic.init();
    }

    @Test
    public void test1_getConfigurationPoints() {
        createConfigurationPoints("TEST-INF/test1/cps");

        assertEquals("ConfigurationPoints[uninitialized]", cps.toString());

        // init
        cps.getConfigurationPoints();

        String str = cps.toString();

        System.out.println("--");
        System.out.println(str);

        assertThat(str, containsString("ConfigurationPoints[2 cps, loaded from TEST-INF/test1/cps]"));
        assertThat(str, containsString("[1/2] ConfigurationPoint[cp1=http://www.alibaba.com/test1/cp1"));
        assertThat(str, containsString("[2/2] ConfigurationPoint[cp2=http://www.alibaba.com/test1/cp2"));
    }

    @Test
    public void test1_getConfigurationPointByName() {
        createConfigurationPoints("TEST-INF/test1/cps");

        assertNull(cps.getConfigurationPointByName(null)); // no NPE

        ConfigurationPoint cp = cps.getConfigurationPointByName("cp1");
        assertThat(cp.toString(), containsString("cp1=http://www.alibaba.com/test1/cp1"));

        cp = cps.getConfigurationPointByName("cp2");
        assertThat(cp.toString(), containsString("cp2=http://www.alibaba.com/test1/cp2"));
    }

    @Test
    public void test1_getConfigurationPointByNamespaceUri() {
        createConfigurationPoints("TEST-INF/test1/cps");

        assertNull(cps.getConfigurationPointByNamespaceUri(null)); // no NPE
        assertNull(cps.getConfigurationPointByNamespaceUri("http://www.alibaba.com/test1/cp3")); // invalid and ignored

        ConfigurationPoint cp = cps.getConfigurationPointByNamespaceUri("http://www.alibaba.com/test1/cp1");
        assertThat(cp.toString(), containsString("cp1=http://www.alibaba.com/test1/cp1"));

        cp = cps.getConfigurationPointByNamespaceUri("http://www.alibaba.com/test1/cp2");
        assertThat(cp.toString(), containsString("cp2=http://www.alibaba.com/test1/cp2"));
    }

    @Test
    public void test1_getAvailableNamespaces() {
        createConfigurationPoints("TEST-INF/test1/cps");

        String[] namespaces = cps.getAvailableNamespaces().toArray(new String[0]); // already sorted

        assertArrayEquals(new String[] { "http://www.alibaba.com/test1/cp1",
                                         "http://www.alibaba.com/test1/cp2" }, namespaces);
    }

    @Test
    public void test6_toString() {
        createConfigurationPoints("TEST-INF/test6/cps");

        ConfigurationPointImpl cp = (ConfigurationPointImpl) cps.getConfigurationPointByName("cp1");

        assertEquals(6, cp.getContributions().size());

        String str = cps.toString();

        System.out.println("--");
        System.out.println(str);

        assertThat(str, containsString("ConfigurationPoints[1 cps, loaded from TEST-INF/test6/cps]"));
        assertThat(str, containsString("ConfigurationPoint[cp1=http://www.alibaba.com/test6/cp1"));
        assertThat(str, containsString("loaded contributions from TEST-INF/test6/cp1."));

        assertThat(
                str,
                containsString("[1/6] Contribution[toConfigurationPoint=cp1, name=my1, type=BEAN_DEFINITION_PARSER, class=com.alibaba.citrus.springext.contrib.MyBeanDefinitionParser]"));
        assertThat(
                str,
                containsString("[2/6] Contribution[toConfigurationPoint=cp1, name=my1, type=BEAN_DEFINITION_DECORATOR, class=com.alibaba.citrus.springext.contrib.MyBeanDefinitionDecorator]"));
        assertThat(
                str,
                containsString("[3/6] Contribution[toConfigurationPoint=cp1, name=my1, type=BEAN_DEFINITION_DECORATOR_FOR_ATTRIBUTE, class=com.alibaba.citrus.springext.contrib.MyBeanDefinitionDecorator]"));
        assertThat(
                str,
                containsString("[4/6] Contribution[toConfigurationPoint=cp1, name=my2, type=BEAN_DEFINITION_PARSER, class=com.alibaba.citrus.springext.contrib.MyBeanDefinitionParser2]"));
        assertThat(
                str,
                containsString("[5/6] Contribution[toConfigurationPoint=cp1, name=my2, type=BEAN_DEFINITION_DECORATOR, class=com.alibaba.citrus.springext.contrib.MyBeanDefinitionDecorator2]"));
        assertThat(
                str,
                containsString("[6/6] Contribution[toConfigurationPoint=cp1, name=my2, type=BEAN_DEFINITION_DECORATOR_FOR_ATTRIBUTE, class=com.alibaba.citrus.springext.contrib.MyBeanDefinitionDecorator2]"));
    }

    @Test
    public void test7_normalizeNameAndNs() {
        createConfigurationPoints("TEST-INF/test7/cps");

        ConfigurationPointImpl cp = (ConfigurationPointImpl) cps.getConfigurationPointByName("dir/cp1");

        assertNotNull(cp);
        assertSame(cp, cps.getConfigurationPointByName("/dir/cp1/"));
        assertSame(cp, cps.getConfigurationPointByNamespaceUri("http://www.alibaba.com//test7/dir/cp1/"));
    }

    @Test
    public void test8_namingConvention_ForNameAndNsUri() {
        createConfigurationPoints("TEST-INF/test8/cps");

        try {
            cps.getConfigurationPointByName("dir/cp1");
            fail();
        } catch (ConfigurationPointException e) {
            assertThat(
                    e,
                    exception("Naming Convention Violation", "http://www.alibaba.com/test8/cp1", "end with", "dir/cp1",
                              "TEST-INF/test8/cps"));
        }
    }

    private void createConfigurationPoints(String location) {
        cps = new ConfigurationPointsImpl((ClassLoader) null, location);
    }
}
