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

package com.alibaba.citrus.service.velocity.impl;

import static com.alibaba.citrus.springext.util.DomUtil.*;
import static com.alibaba.citrus.springext.util.SpringExtUtil.*;
import static com.alibaba.citrus.util.Assert.*;
import static com.alibaba.citrus.util.StringUtil.*;

import java.util.List;
import java.util.Map;

import com.alibaba.citrus.springext.ConfigurationPoint;
import com.alibaba.citrus.springext.Contribution;
import com.alibaba.citrus.springext.ContributionAware;
import com.alibaba.citrus.springext.support.parser.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class VelocityEngineDefinitionParser extends AbstractSingleBeanDefinitionParser<VelocityEngineImpl> implements
                                                                                                           ContributionAware {
    private ConfigurationPoint pluginsConfigurationPoint;

    public void setContribution(Contribution contrib) {
        this.pluginsConfigurationPoint = getSiblingConfigurationPoint("services/template/engines/velocity/plugins",
                                                                      contrib);
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        attributesToProperties(element, "configuration.", builder, "path", "cacheEnabled", "modificationCheckInterval",
                               "strictReference", "templateEncoding");

        ElementSelector globalMacros = and(sameNs(element), name("global-macros"));
        ElementSelector plugins = and(sameNs(element), name("plugins"));
        ElementSelector advancedProperties = and(sameNs(element), name("advanced-properties"));

        for (Element subElement : subElements(element)) {
            if (globalMacros.accept(subElement)) {
                parseGlobalMacros(subElement, parserContext, builder);
            } else if (plugins.accept(subElement)) {
                parsePlugins(subElement, parserContext, builder);
            } else if (advancedProperties.accept(subElement)) {
                parseAdvancedProperties(subElement, parserContext, builder);
            }
        }
    }

    private void parseGlobalMacros(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        List<Object> macros = createManagedList(element, parserContext);

        for (Element subElement : subElements(element, and(sameNs(element), name("name")))) {
            String name = trimToNull(subElement.getTextContent());

            if (name != null) {
                macros.add(name);
            }
        }

        builder.addPropertyValue("configuration.globalMacros", macros);
    }

    private void parsePlugins(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        List<Object> plugins = createManagedList(element, parserContext);

        for (Element subElement : subElements(element)) {
            plugins.add(parseConfigurationPointBean(subElement, pluginsConfigurationPoint, parserContext, builder));
        }

        builder.addPropertyValue("configuration.plugins", plugins);
    }

    private void parseAdvancedProperties(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        Map<Object, Object> props = createManagedMap(element, parserContext);

        for (Element subElement : subElements(element, and(sameNs(element), name("property")))) {
            String name = assertNotNull(trimToNull(subElement.getAttribute("name")), "propertyName");
            String value = trimToEmpty(subElement.getAttribute("value"));

            props.put(name, value);
        }

        builder.addPropertyValue("configuration.advancedProperties", props);
    }
}
