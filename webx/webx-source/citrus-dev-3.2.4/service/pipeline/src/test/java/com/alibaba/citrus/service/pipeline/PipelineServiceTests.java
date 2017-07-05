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

package com.alibaba.citrus.service.pipeline;

import static com.alibaba.citrus.springext.util.SpringExtUtil.*;
import static com.alibaba.citrus.util.CollectionUtil.*;
import static org.junit.Assert.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * 测试pipeline的配置，以及valve的功能。
 *
 * @author Michael Zhou
 */
public class PipelineServiceTests extends AbstractPipelineTests {
    private static ThreadLocal<Map<String, Object>> beansHolder = new ThreadLocal<Map<String, Object>>();

    @BeforeClass
    public static void initFactory() {
        createFactory("services-pipeline.xml");
    }

    @Test
    public void simple_pipeline() {
        pipeline = getPipelineImplFromFactory("simple");

        assertInvoke(pipeline, false);
        assertLog("1-1", "1-2", "1-3");
    }

    @Test
    public void scoped_pipeline() {
        Pipeline pipelineProxy = assertProxy(getPipelineFromFactory("scoped"));

        assertInvoke(pipelineProxy, false);
        assertLog("1-1", "1-2", "1-3");

        Pipeline real1 = getProxyTarget(pipelineProxy);
        assertSame(real1, beansHolder.get().get("proxyTarget.scoped"));

        // 清除scope，重新调用proxy
        beansHolder.remove();

        assertInvoke(pipelineProxy, false);
        assertLog("1-1", "1-2", "1-3");

        Pipeline real2 = getProxyTarget(pipelineProxy);
        assertSame(real2, beansHolder.get().get("proxyTarget.scoped"));

        assertNotSame(real1, real2);
    }

    @Test
    public void inject_scoped_pipeline() {
        pipeline = getPipelineImplFromFactory("injectScoped");

        assertInvoke(pipeline, false);
        assertLog("1-1" /* sub-pipeline */, //
                  "2-1", "2-2", "2-3", //
                  "1-3");
    }

    // 由于spring2和spring3的scope接口是不兼容的（后者基于generic type），所以用proxy来测试，确保在两种环境下均可编译。
    public static class TestOnlyScope implements FactoryBean {
        public Class<?> getObjectType() {
            return Scope.class;
        }

        public boolean isSingleton() {
            return true;
        }

        public Object getObject() throws Exception {
            return Proxy.newProxyInstance(getClass().getClassLoader(), new Class<?>[] { Scope.class },
                                          new InvocationHandler() {
                                              public Object invoke(Object proxy, Method method, Object[] args)
                                                      throws Throwable {
                                                  String name = method.getName();
                                                  TestOnlyScope scope = TestOnlyScope.this;

                                                  if ("get".equals(name)) {
                                                      return scope.getClass().getMethod(name, String.class, ObjectFactory.class)
                                                                  .invoke(scope, args);
                                                  } else if ("remove".equals(name)) {
                                                      return scope.getClass().getMethod(name, String.class).invoke(scope, args);
                                                  } else {
                                                      return null;
                                                  }
                                              }
                                          });
        }

        public Object get(String name, ObjectFactory objectFactory) {
            Map<String, Object> beans = beansHolder.get();

            if (beans == null) {
                beans = createHashMap();
                beansHolder.set(beans);
            }

            if (!beans.containsKey(name)) {
                beans.put(name, objectFactory.getObject());
            }

            return beans.get(name);
        }

        public Object remove(String name) {
            Map<String, Object> beans = beansHolder.get();

            if (beans == null) {
                return null;
            } else {
                return beans.remove(name);
            }
        }
    }
}
