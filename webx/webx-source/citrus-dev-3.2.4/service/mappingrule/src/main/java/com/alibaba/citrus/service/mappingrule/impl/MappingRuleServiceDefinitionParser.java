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

package com.alibaba.citrus.service.mappingrule.impl;

import static com.alibaba.citrus.springext.util.DomUtil.*;
import static com.alibaba.citrus.springext.util.SpringExtUtil.*;
import static com.alibaba.citrus.util.StringUtil.*;

import java.util.Map;

import com.alibaba.citrus.springext.ConfigurationPoint;
import com.alibaba.citrus.springext.Contribution;
import com.alibaba.citrus.springext.ContributionAware;
import com.alibaba.citrus.springext.support.parser.AbstractNamedBeanDefinitionParser;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class MappingRuleServiceDefinitionParser extends AbstractNamedBeanDefinitionParser<MappingRuleServiceImpl>
        implements ContributionAware {
    private ConfigurationPoint mappingRulesConfigurationPoint;

    public void setContribution(Contribution contrib) {
        mappingRulesConfigurationPoint = getSiblingConfigurationPoint("services/mapping-rules", contrib);
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        parseBeanDefinitionAttributes(element, parserContext, builder);

        Map<Object, Object> rules = createManagedMap(element, parserContext);

        for (Element subElement : subElements(element)) {
            BeanDefinitionHolder rule = parseConfigurationPointBean(subElement, mappingRulesConfigurationPoint,
                                                                    parserContext, builder);

            String name = trimToNull(subElement.getAttribute("id"));

            if (rule != null) {
                rules.put(name, rule);
            }
        }

        builder.addPropertyValue("rules", rules);

        String parentRef = trimToNull(element.getAttribute("parentRef"));

        if (parentRef != null) {
            builder.addPropertyValue("parent", new RuntimeBeanReference(parentRef));
        }
    }

    @Override
    protected String getDefaultName() {
        return "mappingRuleService, mappingRules";
    }
}
