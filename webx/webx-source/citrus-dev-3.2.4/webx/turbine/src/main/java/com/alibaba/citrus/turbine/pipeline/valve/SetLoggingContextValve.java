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

import static com.alibaba.citrus.util.BasicConstant.*;
import static java.util.Collections.*;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.citrus.service.configuration.ProductionModeAware;
import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.service.pipeline.support.AbstractValveDefinitionParser;
import com.alibaba.citrus.webx.util.SetLoggingContextHelper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 设置或清除logging MDC的valve。
 *
 * @author Michael Zhou
 */
public class SetLoggingContextValve extends AbstractValve implements ProductionModeAware {
    public static final String MDC_PRODUCTION_MODE = "productionMode";

    @Autowired
    private HttpServletRequest request;
    private Boolean            productionMode;

    public void setProductionMode(boolean productionMode) {
        this.productionMode = productionMode;
    }

    public String getProductionModeDesc() {
        if (productionMode == null) {
            return EMPTY_STRING;
        } else if (productionMode) {
            return "Production Mode";
        } else {
            return "Development Mode";
        }
    }

    public void invoke(PipelineContext pipelineContext) throws Exception {
        SetLoggingContextHelper helper = new SetLoggingContextHelper(request);

        Map<String, String> extra = singletonMap(MDC_PRODUCTION_MODE, getProductionModeDesc());

        try {
            helper.setLoggingContext(extra);
            pipelineContext.invokeNext();
        } finally {
            helper.clearLoggingContext();
        }
    }

    public static class DefinitionParser extends AbstractValveDefinitionParser<SetLoggingContextValve> {
    }
}
