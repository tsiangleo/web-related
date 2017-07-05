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

package com.alibaba.citrus.service.jsp;

import static org.easymock.EasyMock.*;

import java.util.Locale;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.citrus.service.jsp.impl.JspResponse;
import org.junit.Before;
import org.junit.Test;

public class JspResponseTests {
    private HttpServletResponse response;
    private HttpServletResponse jspResponse;

    @Before
    public void init() {
        response = createMock(HttpServletResponse.class);
        replay(response);

        jspResponse = new JspResponse(response);
    }

    @Test
    public void setContentType() {
        jspResponse.setContentType("text/html");
    }

    @Test
    public void setLocale() {
        jspResponse.setLocale(Locale.CHINA);
    }

    @Test
    public void setCharacterEncoding() {
        jspResponse.setCharacterEncoding("GBK");
    }
}
