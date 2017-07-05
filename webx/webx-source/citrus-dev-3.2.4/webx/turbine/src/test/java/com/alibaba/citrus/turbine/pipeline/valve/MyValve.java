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

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.Valve;
import com.alibaba.citrus.service.requestcontext.rundata.RunData;
import org.springframework.beans.factory.annotation.Autowired;

/** @author dux.fangl */
public class MyValve implements Valve {
    @Autowired
    private RunData rundata;

    public void invoke(PipelineContext pipelineContext) throws Exception {
        if (rundata != null) {
            rundata.setAttribute("MyValve", "execute success!");
        }
    }
}
