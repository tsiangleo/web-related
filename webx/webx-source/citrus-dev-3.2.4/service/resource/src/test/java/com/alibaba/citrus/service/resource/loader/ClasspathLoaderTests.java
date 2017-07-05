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

package com.alibaba.citrus.service.resource.loader;

import static com.alibaba.citrus.test.TestEnvStatic.*;
import static com.alibaba.citrus.test.TestUtil.*;
import static org.junit.Assert.*;

import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Before;
import org.junit.Test;

public class ClasspathLoaderTests extends AbstractResourceLoaderTests<ClasspathResourceLoader> {
    @Before
    public void init() throws Exception {
        loader = new ClasspathResourceLoader();
        loader.setClassLoader(new URLClassLoader(new URL[] { srcdir.toURI().toURL() }));
        loader.init(null);
    }

    @Test
    public void getResource() throws Exception {
        assertResourceLoader("/classpath/WEB-INF", "WEB-INF/", true); // dir
        assertResourceLoader("/classpath/WEB-INF/web.xml", "WEB-INF/web.xml", true);
    }

    @Test
    public void _toString() {
        assertThat(loader.toString(), containsAll("ClasspathResourceLoader[", "URLClassLoader", "]"));
    }

    @Override
    protected String getPrefix() {
        return "classpath";
    }
}
