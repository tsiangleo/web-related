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

package com.alibaba.citrus.service.form.impl.validation;

import com.alibaba.citrus.service.form.support.AbstractValidator;
import com.alibaba.citrus.service.form.support.AbstractValidatorDefinitionParser;
import com.alibaba.citrus.util.StringUtil;

/**
 * 验证必填字段的validator。
 *
 * @author Michael Zhou
 */
public class RequiredValidator extends AbstractValidator {
    public boolean validate(Context context) {
        Object value = context.getValue();

        if (value instanceof String) {
            // 在trimming=false模式下，空白也算有值。
            return !StringUtil.isEmpty((String) value);
        } else {
            return value != null;
        }
    }

    public static class DefinitionParser extends AbstractValidatorDefinitionParser<RequiredValidator> {
    }
}
