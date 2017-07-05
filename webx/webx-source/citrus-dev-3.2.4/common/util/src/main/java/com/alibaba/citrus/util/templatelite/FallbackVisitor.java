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

package com.alibaba.citrus.util.templatelite;

/**
 * 当visit方法找不到时，调用此visitor。
 *
 * @author Michael Zhou
 */
public interface FallbackVisitor {
    /**
     * 访问<code>${placeholder:param1, param2, ...}</code>。 返回<code>true</code>
     * 表示访问成功，否则还是会抛出<code>NoSuchMethodException</code>。
     */
    boolean visitPlaceholder(String name, Object[] params) throws Exception;
}
