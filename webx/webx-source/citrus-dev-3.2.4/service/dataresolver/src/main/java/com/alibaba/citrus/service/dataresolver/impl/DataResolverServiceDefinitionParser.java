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

package com.alibaba.citrus.service.dataresolver.impl;

import static com.alibaba.citrus.springext.util.DomUtil.*;
import static com.alibaba.citrus.springext.util.SpringExtUtil.*;

import java.util.List;

import com.alibaba.citrus.springext.ConfigurationPoint;
import com.alibaba.citrus.springext.Contribution;
import com.alibaba.citrus.springext.ContributionAware;
import com.alibaba.citrus.springext.support.parser.AbstractNamedBeanDefinitionParser;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class DataResolverServiceDefinitionParser extends AbstractNamedBeanDefinitionParser<DataResolverServiceImpl>
        implements ContributionAware {
    private ConfigurationPoint dataResolverFactoriesConfigurationPoint;

    public void setContribution(Contribution contrib) {
        this.dataResolverFactoriesConfigurationPoint = getSiblingConfigurationPoint("services/data-resolver/factories",
                                                                                    contrib);
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        List<Object> factories = createManagedList(element, parserContext);

        for (Element subElement : subElements(element)) {
            Object factory = parseConfigurationPointBean(subElement, dataResolverFactoriesConfigurationPoint,
                                                         parserContext, builder);

            if (factory != null) {
                factories.add(factory);
            }
        }

        builder.addPropertyValue("factories", factories);
    }

    @Override
    protected String getDefaultName() {
        return "dataResolverService, dataResolvers";
    }
}
