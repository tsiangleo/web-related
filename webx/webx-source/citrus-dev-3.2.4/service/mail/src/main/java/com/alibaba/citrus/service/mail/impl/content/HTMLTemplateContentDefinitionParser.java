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

package com.alibaba.citrus.service.mail.impl.content;

import static com.alibaba.citrus.springext.util.DomUtil.*;
import static com.alibaba.citrus.springext.util.SpringExtUtil.*;

import java.util.Map;

import com.alibaba.citrus.service.mail.builder.content.HTMLTemplateContent;
import com.alibaba.citrus.springext.support.parser.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class HTMLTemplateContentDefinitionParser extends AbstractSingleBeanDefinitionParser<HTMLTemplateContent> {
    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        attributesToProperties(element, builder, "template", "contentType");

        ElementSelector inlineResourceSelector = and(sameNs(element), name("inline-resource"));
        Map<Object, Object> inlineResources = createManagedMap(element, parserContext);

        for (Element subElement : subElements(element, inlineResourceSelector)) {
            String id = subElement.getAttribute("id");
            String prefix = subElement.getAttribute("prefix");

            inlineResources.put(id, prefix);
        }

        builder.addPropertyValue("inlineResources", inlineResources);
    }
}
