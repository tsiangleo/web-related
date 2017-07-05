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

package com.alibaba.citrus.webx.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 根据异常的类型，将请求分发给适当的request handler。
 *
 * @author Michael Zhou
 */
public interface ErrorHandlerMapping {
    /**
     * 取得exception对应的handler及相关信息。
     * <p>
     * 如果返回<code>null</code>代表无对应的<code>RequestHandler</code>。
     * </p>
     */
    RequestHandlerContext getRequestHandlerContextForError(HttpServletRequest request, HttpServletResponse response,
                                                           Throwable exception);
}
