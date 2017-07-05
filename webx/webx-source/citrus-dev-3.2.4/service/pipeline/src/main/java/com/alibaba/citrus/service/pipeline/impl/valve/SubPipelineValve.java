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

package com.alibaba.citrus.service.pipeline.impl.valve;

import static com.alibaba.citrus.util.Assert.*;

import com.alibaba.citrus.service.pipeline.Pipeline;
import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.service.pipeline.support.AbstractValveDefinitionParser;
import com.alibaba.citrus.util.ToStringBuilder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * 用来执行一个子pipeline。
 *
 * @author Michael Zhou
 */
public class SubPipelineValve extends AbstractValve {
    private Pipeline subPipeline;

    public Pipeline getSubPipeline() {
        return subPipeline;
    }

    public void setSubPipeline(Pipeline subPipeline) {
        this.subPipeline = subPipeline;
    }

    @Override
    protected void init() throws Exception {
        assertNotNull(subPipeline, "no sub-pipeline");
    }

    public void invoke(PipelineContext pipelineContext) throws Exception {
        subPipeline.newInvocation(pipelineContext).invoke();
        pipelineContext.invokeNext();
    }

    @Override
    public String toString() {
        return new ToStringBuilder().append("SubPipelineValve").start("{", "}").append(subPipeline).end().toString();
    }

    public static class DefinitionParser extends AbstractValveDefinitionParser<SubPipelineValve> {
        @Override
        protected final void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
            builder.addPropertyValue("subPipeline", parsePipeline(element, null, parserContext, "ref"));
        }
    }
}
