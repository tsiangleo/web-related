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

package com.alibaba.citrus.util.internal;

import static com.alibaba.citrus.util.Assert.*;
import static com.alibaba.citrus.util.BasicConstant.*;
import static com.alibaba.citrus.util.ClassUtil.*;
import static com.alibaba.citrus.util.CollectionUtil.*;

import java.lang.reflect.Method;
import java.util.Map;

import com.alibaba.citrus.util.ToStringBuilder;
import com.alibaba.citrus.util.ToStringBuilder.MapBuilder;
import net.sf.cglib.asm.Type;
import net.sf.cglib.core.DefaultNamingPolicy;
import net.sf.cglib.core.Predicate;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

/**
 * 将一组静态方法组合成一个对象。
 *
 * @author Michael Zhou
 */
public class StaticFunctionDelegatorBuilder extends DynamicClassBuilder {
    private final Map<Signature, Method> methods = createHashMap();
    private Class<?> mixinInterface;
    private Enhancer generator;

    public StaticFunctionDelegatorBuilder() {
    }

    public StaticFunctionDelegatorBuilder(ClassLoader cl) {
        super(cl);
    }

    public StaticFunctionDelegatorBuilder addClass(Class<?> utilClass) {
        for (Method method : utilClass.getMethods()) {
            if (isPublicStatic(method)) {
                addMethod(method);
            }
        }

        return this;
    }

    public StaticFunctionDelegatorBuilder addMethod(Method method) {
        return addMethod(method, null);
    }

    public StaticFunctionDelegatorBuilder addMethod(Method method, String rename) {
        assertNotNull(method, "Method is null");
        assertTrue(isPublicStatic(method), "Method is not public static: %s", method);

        Signature sig = getSignature(method, rename);

        if (methods.containsKey(sig)) {
            throw new IllegalArgumentException("Duplicated method signature: " + sig + "\n  method: "
                                               + methods.get(sig));
        }

        methods.put(sig, method);

        return this;
    }

    public Class<?> getMixinInterface() {
        if (mixinInterface == null) {
            InterfaceMaker im = new InterfaceMaker();

            for (Map.Entry<Signature, Method> entry : methods.entrySet()) {
                Signature sig = entry.getKey();
                Method method = entry.getValue();

                Type[] exceptionTypes = new Type[method.getExceptionTypes().length];

                for (int i = 0; i < exceptionTypes.length; i++) {
                    exceptionTypes[i] = Type.getType(method.getExceptionTypes()[i]);
                }

                im.add(sig, exceptionTypes);
            }

            im.setClassLoader(getClassLoader());
            im.setNamingPolicy(new DefaultNamingPolicy() {
                @Override
                public String getClassName(String prefix, String source, Object key, Predicate names) {
                    return super.getClassName(EMPTY_STRING, getSimpleClassName(StaticFunctionDelegatorBuilder.class),
                                              key, names);
                }
            });

            mixinInterface = im.create();
        }

        return mixinInterface;
    }

    public Object toObject() {
        if (generator == null) {
            final Class<?> intfs = getMixinInterface();
            final Map<Method, FastMethod> methodMappings = getMethodMappings(intfs);

            generator = new Enhancer();

            generator.setClassLoader(getClassLoader());
            generator.setSuperclass(Object.class);
            generator.setInterfaces(new Class<?>[] { intfs });

            generator.setCallbacks(new Callback[] {
                    // default callback
                    new MethodInterceptor() {
                        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                                throws Throwable {
                            return proxy.invokeSuper(obj, args);
                        }
                    },

                    // toString callback
                    new MethodInterceptor() {
                        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                                throws Throwable {
                            MapBuilder mb = new MapBuilder().setPrintCount(true).setSortKeys(true);

                            for (Map.Entry<Method, FastMethod> entry : methodMappings.entrySet()) {
                                mb.append(
                                        entry.getKey().getName(),
                                        getSimpleMethodSignature(entry.getValue().getJavaMethod(), false, true, true, false));
                            }

                            return new ToStringBuilder().append(intfs.getName()).append(mb).toString();
                        }
                    },

                    // proxied callback
                    new MethodInterceptor() {
                        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                                throws Throwable {
                            FastMethod realMethod = assertNotNull(methodMappings.get(method), "unknown method: %s", method);
                            return realMethod.invoke(null, args);
                        }
                    } });

            generator.setCallbackFilter(new CallbackFilter() {
                public int accept(Method method) {
                    if (isEqualsMethod(method) || isHashCodeMethod(method)) {
                        return 0; // invoke super
                    } else if (isToStringMethod(method)) {
                        return 1; // invoke toString
                    } else {
                        return 2; // invoke proxied object
                    }
                }
            });
        }

        Object obj = generator.create();

        log.debug("Generated mixin function delegator: {}", obj);

        return obj;
    }

    private Map<Method, FastMethod> getMethodMappings(Class<?> intfs) {
        // 查找interface中的方法和被代理方法之间的对应关系
        Map<Method, FastMethod> methodMappings = createHashMap();

        for (Method method : intfs.getMethods()) {
            Signature sig = getSignature(method, null);
            Method realMethod = assertNotNull(methods.get(sig), "unknown method signature: %s", sig);
            FastClass fc = FastClass.create(getClassLoader(), realMethod.getDeclaringClass());
            FastMethod fm = fc.getMethod(realMethod);

            methodMappings.put(method, fm);
        }

        return methodMappings;
    }
}
