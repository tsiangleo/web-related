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

package com.alibaba.citrus.generictype;

import java.lang.reflect.GenericArrayType;

/**
 * 和{@link GenericArrayType}及数组型{@link Class}对应，代表一个Generic数组的信息。
 * <p>
 * 和{@link GenericArrayType}和{@link Class}不同的是，{@link ArrayTypeInfo}包含数组维度的信息。
 * </p>
 *
 * @author Michael Zhou
 */
public interface ArrayTypeInfo extends TypeInfo {
}
