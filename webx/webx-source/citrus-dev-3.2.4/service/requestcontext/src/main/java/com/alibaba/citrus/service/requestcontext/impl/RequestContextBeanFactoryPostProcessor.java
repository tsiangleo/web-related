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

package com.alibaba.citrus.service.requestcontext.impl;

import static com.alibaba.citrus.springext.util.SpringExtUtil.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.citrus.service.requestcontext.RequestContext;
import com.alibaba.citrus.service.requestcontext.RequestContextChainingService;
import com.alibaba.citrus.service.requestcontext.RequestContextInfo;
import com.alibaba.citrus.service.requestcontext.util.RequestContextUtil;
import com.alibaba.citrus.springext.util.ProxyTargetFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 创建全局的request context对象，以及request、response对象。
 * <p>
 * 无论是何种bean都可以注入这些对象：request context、request、response。
 * </p>
 *
 * @author Michael Zhou
 */
public class RequestContextBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private final static Logger log = LoggerFactory.getLogger(RequestContextBeanFactoryPostProcessor.class);
    private final String requestContextsName;

    public RequestContextBeanFactoryPostProcessor(String requestContextsName) {
        this.requestContextsName = requestContextsName;
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 先注册request/response/session，再从beanFactory中取得requestContexts。

        // 创建全局的request实例。
        register(
                beanFactory,
                ServletRequest.class,
                createProxy(HttpServletRequest.class, beanFactory.getBeanClassLoader(), new RequestProxyTargetFactory()));

        // 创建全局的session实例。
        register(beanFactory, HttpSession.class,
                 createProxy(HttpSession.class, beanFactory.getBeanClassLoader(), new SessionProxyTargetFactory()));

        // 创建全局的response实例。
        register(
                beanFactory,
                ServletResponse.class,
                createProxy(HttpServletResponse.class, beanFactory.getBeanClassLoader(),
                            new ResponseProxyTargetFactory()));

        // 取得requestContexts时会激活requestContexts的初始化。
        // 由于request/response/session已经被注册，因此已经可被注入到requestContexts的子对象中。
        RequestContextChainingService requestContexts = (RequestContextChainingService) beanFactory.getBean(
                requestContextsName, RequestContextChainingService.class);

        // 创建全局的request context实例。
        for (RequestContextInfo<?> info : requestContexts.getRequestContextInfos()) {
            Class<? extends RequestContext> requestContextInterface = info.getRequestContextInterface();
            Class<? extends RequestContext> requestContextProxyInterface = info.getRequestContextProxyInterface();

            // 避免对没有子接口的request context对象创建proxy，否则没有意义。
            if (!RequestContext.class.equals(requestContextProxyInterface)) {
                register(
                        beanFactory,
                        requestContextInterface,
                        createProxy(requestContextProxyInterface, beanFactory.getBeanClassLoader(),
                                    new RequestContextProxyTargetFactory(requestContextProxyInterface)));
            }
        }
    }

    private void register(ConfigurableListableBeanFactory beanFactory, Class<?> intfs, Object instance) {
        beanFactory.registerResolvableDependency(intfs, instance);

        log.debug("Registered Global Proxy for interface {}", intfs.getName());
    }

    private static class RequestProxyTargetFactory implements ProxyTargetFactory {
        public Object getObject() {
            RequestAttributes requestAttrs = RequestContextHolder.currentRequestAttributes();

            if (!(requestAttrs instanceof ServletRequestAttributes)) {
                throw new IllegalStateException("Current request is not a servlet request");
            }

            HttpServletRequest request = ((ServletRequestAttributes) requestAttrs).getRequest();

            if (request == null) {
                throw new IllegalStateException("Current request is not a servlet request");
            }

            return request;
        }
    }

    private final class ResponseProxyTargetFactory extends RequestProxyTargetFactory {
        @Override
        public Object getObject() {
            HttpServletRequest request = (HttpServletRequest) super.getObject();
            RequestContext requestContext = RequestContextUtil.getRequestContext(request);

            if (requestContext == null) {
                throw new IllegalStateException("Current request does not support request context");
            }

            return requestContext.getResponse();
        }
    }

    private final class SessionProxyTargetFactory extends RequestProxyTargetFactory {
        @Override
        public Object getObject() {
            HttpServletRequest request = (HttpServletRequest) super.getObject();
            RequestContext requestContext = RequestContextUtil.getRequestContext(request);

            if (requestContext == null) {
                throw new IllegalStateException("Current request does not support request context");
            }

            return requestContext.getRequest().getSession();
        }
    }

    private final class RequestContextProxyTargetFactory extends RequestProxyTargetFactory {
        private final Class<? extends RequestContext> requestContextInterface;

        private RequestContextProxyTargetFactory(Class<? extends RequestContext> requestContextInterface) {
            this.requestContextInterface = requestContextInterface;
        }

        @Override
        public Object getObject() {
            HttpServletRequest request = (HttpServletRequest) super.getObject();

            RequestContext requestContext = RequestContextUtil.findRequestContext(request, requestContextInterface);

            if (requestContext == null) {
                throw new IllegalStateException("Current request does not support request context: "
                                                + requestContextInterface.getName());
            }

            return requestContext;
        }
    }
}
