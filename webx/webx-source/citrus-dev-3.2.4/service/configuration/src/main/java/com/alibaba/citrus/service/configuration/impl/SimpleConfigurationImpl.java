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

package com.alibaba.citrus.service.configuration.impl;

import com.alibaba.citrus.service.configuration.Configuration;
import com.alibaba.citrus.service.configuration.support.PropertiesConfigurationSupport;

public class SimpleConfigurationImpl extends PropertiesConfigurationSupport<Configuration> implements Configuration {
    public final static String DEFAULT_NAME = "simpleConfiguration";

    public SimpleConfigurationImpl() {
        super();
    }

    public SimpleConfigurationImpl(SimpleConfigurationImpl parent) {
        super(parent);
    }

    @Override
    protected String getDefaultName() {
        return DEFAULT_NAME;
    }
}
