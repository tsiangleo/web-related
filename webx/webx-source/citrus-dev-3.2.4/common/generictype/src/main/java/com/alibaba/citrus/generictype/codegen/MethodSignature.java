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

package com.alibaba.citrus.generictype.codegen;

import static com.alibaba.citrus.generictype.codegen.CodegenConstant.*;
import static com.alibaba.citrus.generictype.codegen.TypeUtil.*;
import static com.alibaba.citrus.generictype.codegen.asm.Type.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.alibaba.citrus.generictype.codegen.asm.Type;

/**
 * 代表一个构造函数或方法的签名，用来辨别相同的方法。
 * <p>
 * 比较方法签名时，只有方法的类型（构造函数或普通方法）、方法名和参数参与了比较。
 * </p>
 *
 * @author Michael Zhou
 */
public class MethodSignature extends com.alibaba.citrus.generictype.codegen.asm.Method {
    public MethodSignature(String name, Class<?> returnType, Class<?>... parameterTypes) {
        super(name, getType(returnType), getTypes(parameterTypes));
    }

    public MethodSignature(Method method) {
        super(method.getName(), Type.getMethodDescriptor(method));
    }

    public MethodSignature(Constructor<?> constructor) {
        super(CONSTRUCTOR_NAME, Type.getConstructorDescriptor(constructor));
    }
}
