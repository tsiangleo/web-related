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

package com.alibaba.citrus.util;

import java.beans.PropertyEditorSupport;

/**
 * 用于注入<code>HumanReadableSizeEditor</code>类型的property。
 *
 * @author Michael Zhou
 */
public class HumanReadableSizeEditor extends PropertyEditorSupport {
    @Override
    public String getAsText() {
        Object value = getValue();

        if (value instanceof HumanReadableSize) {
            return ((HumanReadableSize) value).getHumanReadable();
        }

        return super.getAsText();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new HumanReadableSize(text));
    }
}
