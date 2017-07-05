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

package com.alibaba.citrus.logconfig.spi;

import java.net.URL;
import java.util.Map;

import com.alibaba.citrus.logconfig.LogConfigurator;

public class MyLogConfigurator extends LogConfigurator {
    @Override
    protected void doConfigure(URL configFile, Map<String, String> props) {
    }

    @Override
    public void shutdown() {
    }

    @Override
    protected void setDefaultProperties(Map<String, String> props) {
        props.put("hello", "world");
    }
}
