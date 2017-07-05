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

package com.alibaba.citrus.service.pull.tool;

import static java.util.Collections.*;

import com.alibaba.citrus.service.pull.ToolFactory;
import com.alibaba.citrus.service.pull.ToolSetFactory;

public class SimpleToolSet extends BaseFactory implements ToolFactory, ToolSetFactory {
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public Iterable<String> getToolNames() {
        return singletonList(id);
    }

    public Object createTool(String name) {
        return new Object();
    }
}
