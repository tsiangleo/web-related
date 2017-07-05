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

package com.alibaba.citrus.turbine.pipeline.valve;

import static com.alibaba.citrus.springext.util.SpringExtUtil.*;
import static com.alibaba.citrus.turbine.TurbineConstant.*;
import static com.alibaba.citrus.turbine.util.TurbineUtil.*;
import static com.alibaba.citrus.util.FileUtil.*;
import static com.alibaba.citrus.util.StringUtil.*;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.citrus.service.mappingrule.MappingRuleService;
import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.service.pipeline.support.AbstractValveDefinitionParser;
import com.alibaba.citrus.turbine.TurbineRunDataInternal;
import com.alibaba.citrus.util.ServletUtil;
import com.alibaba.citrus.util.StringUtil;
import com.alibaba.citrus.util.internal.ActionEventUtil;
import com.alibaba.citrus.webx.WebxComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * 根据URL的内容来设置rundata。根据以下规则：
 * <ol>
 * <li>取得servletPath + pathInfo - componentPath作为target。</li>
 * <li>使用MappingRuleService，将target的后缀转换成统一的内部后缀。例如：将jhtml转换成jsp。</li>
 * </ol>
 *
 * @author Michael Zhou
 */
public class AnalyzeURLValve extends AbstractValve {
    private static final String DEFAULT_ACTION_PARAM_NAME = "action";

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MappingRuleService mappingRuleService;

    @Autowired
    private WebxComponent component;

    private String homepage;
    private String actionParam;

    /** 设置在URL query中代表action的参数名。 */
    public void setActionParam(String actionParam) {
        this.actionParam = trimToNull(actionParam);
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = normalizeAbsolutePath(homepage);
    }

    @Override
    protected void init() throws Exception {
        if (actionParam == null) {
            actionParam = DEFAULT_ACTION_PARAM_NAME;
        }

        if (homepage == null) {
            setHomepage("/index");
        }
    }

    public void invoke(PipelineContext pipelineContext) throws Exception {
        TurbineRunDataInternal rundata = (TurbineRunDataInternal) getTurbineRunData(request);
        String target = null;

        // 取得target，并转换成统一的内部后缀名。
        String pathInfo = ServletUtil.getResourcePath(rundata.getRequest()).substring(
                component.getComponentPath().length());

        if ("/".equals(pathInfo)) {
            pathInfo = getHomepage();
        }

        // 注意，必须将pathInfo转换成camelCase。
        int lastSlashIndex = pathInfo.lastIndexOf("/");

        if (lastSlashIndex >= 0) {
            pathInfo = pathInfo.substring(0, lastSlashIndex) + "/"
                       + StringUtil.toCamelCase(pathInfo.substring(lastSlashIndex + 1));
        } else {
            pathInfo = StringUtil.toCamelCase(pathInfo);
        }

        target = mappingRuleService.getMappedName(EXTENSION_INPUT, pathInfo);

        rundata.setTarget(target);

        // 取得action
        String action = StringUtil.toCamelCase(trimToNull(rundata.getParameters().getString(actionParam)));

        action = mappingRuleService.getMappedName(ACTION_MODULE, action);
        rundata.setAction(action);

        // 取得actionEvent
        String actionEvent = ActionEventUtil.getEventName(rundata.getRequest());
        rundata.setActionEvent(actionEvent);

        pipelineContext.invokeNext();
    }

    public static class DefinitionParser extends AbstractValveDefinitionParser<AnalyzeURLValve> {
        @Override
        protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
            attributesToProperties(element, builder, "homepage", "actionParam");
        }
    }
}
