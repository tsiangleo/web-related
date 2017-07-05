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

package com.alibaba.citrus.service.upload.impl;

import static com.alibaba.citrus.springext.util.SpringExtUtil.*;
import static com.alibaba.citrus.util.StringUtil.*;

import com.alibaba.citrus.springext.support.parser.AbstractNamedBeanDefinitionParser;
import com.alibaba.citrus.util.StringUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class UploadServiceDefinitionParser extends AbstractNamedBeanDefinitionParser<UploadServiceImpl> {
    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        parseBeanDefinitionAttributes(element, parserContext, builder);
        attributesToProperties(element, builder, "repository", "sizeMax", "fileSizeMax", "sizeThreshold",
                               "keepFormFieldInMemory");

        String fileNameKey = trimToNull(element.getAttribute("fileNameKey"));

        if (fileNameKey != null) {
            builder.addPropertyValue("fileNameKey", StringUtil.split(fileNameKey, ", "));
        }
    }

    @Override
    protected String getDefaultName() {
        return "uploadService";
    }
}
