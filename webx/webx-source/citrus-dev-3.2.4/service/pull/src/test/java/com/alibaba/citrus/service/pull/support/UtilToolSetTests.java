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

package com.alibaba.citrus.service.pull.support;

import static com.alibaba.citrus.util.CollectionUtil.*;
import static org.junit.Assert.*;

import java.util.List;

import com.alibaba.citrus.util.StringEscapeUtil;
import com.alibaba.citrus.util.Utils;
import org.junit.Test;

public class UtilToolSetTests {
    @Test
    public void toolSet() {
        UtilToolSet utils = new UtilToolSet();

        List<String> names = createArrayList(utils.getToolNames());

        assertEquals(Utils.getUtils().size(), names.size());
        assertTrue(names.contains("stringEscapeUtil"));
        assertTrue(utils.createTool("stringEscapeUtil") instanceof StringEscapeUtil);
    }
}
