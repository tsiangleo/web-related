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

package com.alibaba.citrus.service.template;

import java.util.Set;

/**
 * 一个通用的模板context接口。该接口相当于一个被简化的map。
 *
 * @author Michael Zhou
 */
public interface TemplateContext {
    /** 添加一个值。 */
    void put(String key, Object value);

    /** 取得指定值。 */
    Object get(String key);

    /** 删除一个值。 */
    void remove(String key);

    /** 判断是否包含指定的键。 */
    boolean containsKey(String key);

    /** 取得所有key的集合。 */
    Set<String> keySet();
}
