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

package com.alibaba.citrus.service.requestcontext.rewrite.support;

import com.alibaba.citrus.service.requestcontext.parser.ParserRequestContext;
import com.alibaba.citrus.service.requestcontext.rewrite.RewriteSubstitutionContext;
import com.alibaba.citrus.service.requestcontext.rewrite.RewriteSubstitutionHandler;

/**
 * 替换处理程序，可用来规格化URL，使后续的rewrite配置更统一。
 *
 * @author Michael Zhou
 */
public class UrlNormalizer implements RewriteSubstitutionHandler {
    public void postSubstitution(RewriteSubstitutionContext context) {
        ParserRequestContext parser = context.getParserRequestContext();
        String path = context.getPath();
        String normalizedPath = parser.convertCase(path);

        context.setPath(normalizedPath);
    }
}
