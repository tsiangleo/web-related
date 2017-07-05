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

package com.alibaba.citrus.service.requestcontext.rewrite;

import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.service.requestcontext.parser.ParserRequestContext;
import com.alibaba.citrus.util.regex.MatchResultSubstitution;

/**
 * 代表一个替换过程的上下文信息。
 * <p>
 * 用户<code>RewriteSubstitutionHandler</code>处理程序可以修改path和参数。
 * </p>
 *
 * @author Michael Zhou
 */
public interface RewriteSubstitutionContext extends RewriteContext {
    String getPath();

    void setPath(String path);

    ParserRequestContext getParserRequestContext();

    ParameterParser getParameters();

    MatchResultSubstitution getMatchResultSubstitution();
}
