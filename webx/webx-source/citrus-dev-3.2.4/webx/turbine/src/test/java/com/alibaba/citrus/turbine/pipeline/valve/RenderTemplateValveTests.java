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

import static com.alibaba.citrus.service.requestcontext.util.RequestContextUtil.*;
import static com.alibaba.citrus.test.TestUtil.*;
import static org.junit.Assert.*;

import com.alibaba.citrus.service.pipeline.PipelineException;
import com.alibaba.citrus.service.pipeline.impl.PipelineImpl;
import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import com.alibaba.citrus.service.requestcontext.util.RequestContextUtil;
import com.alibaba.citrus.service.template.TemplateNotFoundException;
import com.alibaba.citrus.turbine.TurbineConstant;
import com.meterware.httpunit.WebResponse;
import org.junit.Before;
import org.junit.Test;

public class RenderTemplateValveTests extends AbstractValveTests {
    @Before
    public void init() throws Exception {
        super.init();

        pipeline = (PipelineImpl) factory.getBean("renderTemplate");
        assertNotNull(pipeline);
        assertNotNull(rundata);
    }

    @Test
    public void render_with_layout() throws Exception {
        getInvocationContext("http://localhost/app1/aaa/bbb/myModule.vm");
        initRequestContext();

        rundata.setLayoutEnabled(true);
        pipeline.newInvocation().invoke();
        String text = (String) rundata.getContext().get(TurbineConstant.SCREEN_PLACEHOLDER_KEY);
        assertEquals("hello", text);

        text = findRequestContext(newRequest, BufferedRequestContext.class).popCharBuffer();
        assertEquals("hello and layout", text);
    }

    @Test
    public void render_no_buffering() throws Exception {
        getInvocationContext("http://localhost/app1/aaa/bbb/myModule.vm");
        initRequestContext();

        // 关闭buffering
        RequestContextUtil.findRequestContext(newRequest, BufferedRequestContext.class).setBuffering(false);

        rundata.setLayoutEnabled(true);
        pipeline.newInvocation().invoke();
        String text = (String) rundata.getContext().get(TurbineConstant.SCREEN_PLACEHOLDER_KEY);
        assertEquals(null, text);

        WebResponse webResponse = commitRequestContext();
        assertEquals("hello", webResponse.getText());
    }

    @Test
    public void render_override_layout() throws Exception {
        getInvocationContext("http://localhost/app1/aaa/bbb/myModule.vm");
        initRequestContext();

        rundata.setLayout("aaa/bbb/myOtherModule");
        pipeline.newInvocation().invoke();

        String text = (String) rundata.getContext().get(TurbineConstant.SCREEN_PLACEHOLDER_KEY);
        assertEquals("hello", text);

        text = findRequestContext(newRequest, BufferedRequestContext.class).popCharBuffer();
        assertEquals("hello and layout2", text);
    }

    @Test
    public void render_template_not_found() throws Exception {
        // template not found
        try {
            getInvocationContext("http://localhost/app1/myModule.vm");
            initRequestContext();
            pipeline.newInvocation().invoke();
        } catch (PipelineException e) {
            assertThat(e, exception(TemplateNotFoundException.class, "/myscreen/myModule.vm"));
        }
    }
}
