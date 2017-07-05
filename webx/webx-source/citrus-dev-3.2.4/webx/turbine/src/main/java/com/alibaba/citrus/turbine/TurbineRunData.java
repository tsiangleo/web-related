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

package com.alibaba.citrus.turbine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.citrus.service.requestcontext.RequestContext;
import com.alibaba.citrus.service.requestcontext.parser.CookieParser;
import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;

/**
 * 可被应用程序使用的request scope数据接口。
 *
 * @author Michael Zhou
 */
public interface TurbineRunData {
    RequestContext getRequestContext();

    HttpServletRequest getRequest();

    HttpServletResponse getResponse();

    ParameterParser getParameters();

    CookieParser getCookies();

    String getTarget();

    String getRedirectTarget();

    void setRedirectTarget(String redirectTarget);

    String getAction();

    String getActionEvent();

    String getRedirectLocation();

    void setRedirectLocation(String redirectLocation);

    boolean isRedirected();

    boolean isLayoutEnabled();

    void setLayoutEnabled(boolean enabled);

    /**
     * 明确指定layout模板，覆盖默认的layout规则。 注意如果指定了layout，则<code>layoutEnabled</code>
     * 将被设置成<code>true</code>。
     */
    void setLayout(String layoutTemplate);
}
