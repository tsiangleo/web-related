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

package com.alibaba.citrus.service.moduleloader.impl.adapter;

import static org.junit.Assert.*;

import com.alibaba.citrus.service.AbstractWebTests;
import com.alibaba.citrus.service.moduleloader.ModuleEvent;
import com.alibaba.citrus.service.moduleloader.ModuleLoaderService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataBindingTests extends AbstractWebTests {
    private ModuleLoaderService moduleLoader;

    @BeforeClass
    public static void initServlet() throws Exception {
        prepareServlet();
        factory = createContext("adapter/services.xml", false);
    }

    @Before
    public void init() {
        moduleLoader = (ModuleLoaderService) factory.getBean("moduleLoaderService");
        assertNotNull(moduleLoader);
    }

    @Test
    public void isModuleEvent() throws Exception {
        getInvocationContext("/app1");
        initRequestContext();

        DataBindingAdapter adapter = (DataBindingAdapter) moduleLoader.getModule("control", "class.myprod.MyControl");

        assertFalse(adapter instanceof ModuleEvent);
    }

    @Test
    public void execute() throws Exception {
        getInvocationContext("/app1");
        initRequestContext();

        DataBindingAdapter adapter = (DataBindingAdapter) moduleLoader.getModule("control", "class.myprod.MyControl");

        adapter.execute();
        assertEquals("execute", rundata.getAttribute("handler"));

        assertNull(adapter.executeAndReturn());
    }

    @Test
    public void executeAndReturn() throws Exception {
        getInvocationContext("/app1");
        initRequestContext();

        DataBindingAdapter adapter = (DataBindingAdapter) moduleLoader.getModule("control", "class.myprod.MyControl2");

        assertEquals("myresult", adapter.executeAndReturn());
        assertEquals("execute", rundata.getAttribute("handler"));
    }

    @Test
    public void toString_() {
        DataBindingAdapter adapter = (DataBindingAdapter) moduleLoader.getModule("control", "class.myprod.MyControl");

        String s = "";

        s += "DataBindingAdapter {\n";
        s += "  moduleClass   = com.alibaba.test.app1.module.control.myprod.MyControl\n";
        s += "  executeMethod = public void com.alibaba.test.app1.module.control.myprod.MyControl.execute()\n";
        s += "}";

        assertEquals(s, adapter.toString());
    }
}
