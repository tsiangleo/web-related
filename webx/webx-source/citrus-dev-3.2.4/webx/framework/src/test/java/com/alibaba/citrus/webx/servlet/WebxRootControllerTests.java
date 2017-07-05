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

package com.alibaba.citrus.webx.servlet;

import static com.alibaba.citrus.springext.util.SpringExtUtil.*;
import static com.alibaba.citrus.test.TestUtil.*;
import static com.alibaba.citrus.util.StringUtil.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.Map;
import javax.servlet.ServletException;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.requestcontext.RequestContext;
import com.alibaba.citrus.service.requestcontext.rundata.RunData;
import com.alibaba.citrus.service.requestcontext.support.AbstractRequestContextFactory;
import com.alibaba.citrus.webx.AbstractWebxTests;
import com.alibaba.citrus.webx.BadRequestException;
import com.alibaba.citrus.webx.ResourceNotFoundException;
import com.alibaba.citrus.webx.handler.RequestHandler;
import com.alibaba.citrus.webx.handler.RequestHandlerContext;
import com.alibaba.citrus.webx.handler.RequestHandlerMapping;
import com.alibaba.citrus.webx.impl.WebxRootControllerImpl;
import com.alibaba.citrus.webx.pipeline.TestValve;
import com.alibaba.citrus.webx.pipeline.ValveRunner;
import com.alibaba.citrus.webx.util.ErrorHandlerHelper;
import com.meterware.httpunit.HeadMethodWebRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.web.context.WebApplicationContext;

public class WebxRootControllerTests extends AbstractWebxTests {
    private WebxFrameworkFilter    filter;
    private WebxRootControllerImpl controller;

    @Before
    public void init() throws Exception {
        // 特别测试当有/contextPath存在时，passthru参数正确工作
        prepareWebClient(null, "/myapps");

        filter = (WebxFrameworkFilter) client.newInvocation("http://www.taobao.com/myapps/app1").getFilter();
        controller = (WebxRootControllerImpl) filter.getWebxComponents().getWebxRootController();

        setErrorHandler(controller, new TestErrorHandler());
    }

    @Test
    public void passthru() throws Exception {
        // passthru
        invokeServlet("/myapps/app1/plaintext.txt");

        assertEquals(200, clientResponseCode);
        assertEquals("hello, app1", clientResponseContent); // text from plain text file

        // 普通request
        invokeServlet("/myapps/app1/plaintext2.txt");

        assertEquals(200, clientResponseCode);
        assertEquals("hello!", clientResponseContent.trim()); // text from valve
    }

    @Test
    public void requestContextFailed() throws Exception {
        requestContextFactoryHolder.set(new AbstractRequestContextFactory() {
            @Override
            public RequestContext getRequestContextWrapper(RequestContext wrappedContext) {
                throw new IllegalArgumentException("ouch!");
            }

            public String[] getFeatures() {
                return null;
            }

            public FeatureOrder[] featureOrders() {
                return null;
            }
        });

        invokeServlet("/myapps/app1/test.htm");

        assertEquals(500, clientResponseCode);
        assertEquals("text/html", clientResponse.getContentType());
        assertThat(clientResponseContent,
                   containsAll("<pre>", "</pre>", IllegalArgumentException.class.getName(), "ouch!"));
    }

    @Test
    public void internalRequest_illegal() throws Exception {
        // internal
        invokeServlet("/myapps/internal/notexist");

        // 由main handler接手，然后抛出异常
        assertEquals(404, clientResponseCode);
        assertEquals("text/html", clientResponse.getContentType());
        assertThat(
                clientResponseContent,
                containsAll("<pre>", "</pre>", ResourceNotFoundException.class.getName(),
                            "Resource Not Found: notexist"));
    }

    @Test
    public void internalRequest_error() throws Exception {
        invokeServlet("/myapps/internal/error");

        assertEquals(200, clientResponseCode);
        assertEquals("text/html", clientResponse.getContentType());
        assertEquals(null, trimToNull(clientResponseContent));
    }

    @Test
    public void nonInternalRequest() throws Exception {
        invokeServlet("/myapps/app1/internal.htm");

        assertEquals(200, clientResponseCode);
        assertThat(clientResponseContent, containsAll("hello!"));
    }

    @Test
    public void internalRequestMapping() throws Exception {
        WebApplicationContext context = controller.getComponents().getParentApplicationContext();

        class Holder {
            @Autowired
            private RequestHandlerMapping mapping;
        }

        Holder holder = autowireAndInitialize(new Holder(), context, AbstractBeanDefinition.AUTOWIRE_AUTODETECT,
                                              "holder");

        assertArrayEquals(new String[] { "Webx/Info/Environment+Variables", "Webx/Info/System+Properties",
                                         "Webx/Info/Request+Info", "Webx/Info/System+Info", "Webx/Schema" },
                          holder.mapping.getRequestHandlerNames());
    }

    @Test
    public void valve_breakToTop() throws Exception {
        TestValve.runnerHolder.set(new ValveRunner() {
            public void run(RunData rundata, PipelineContext pipelineContext) throws Exception {
                pipelineContext.breakPipeline(0);
            }
        });

        invokeServlet("/myapps/app1/plaintext.txt");

        assertEquals(200, clientResponseCode);
        assertEquals("text/plain", clientResponse.getContentType());
        assertThat(clientResponseContent, containsAll("hello, app1"));
    }

    @Test
    public void valve_exception() throws Exception {
        TestValve.runnerHolder.set(new ValveRunner() {
            public void run(RunData rundata, PipelineContext pipelineContext) throws Exception {
                throw new IllegalStateException("wrong!");
            }
        });

        invokeServlet("/myapps/app1/test.htm");

        assertEquals(500, clientResponseCode);
        assertEquals("text/html", clientResponse.getContentType());
        assertThat(clientResponseContent,
                   containsAll("<pre>", "</pre>", IllegalStateException.class.getName(), "wrong!"));
    }

    @Test
    public void valve_not_found_exception() throws Exception {
        TestValve.runnerHolder.set(new ValveRunner() {
            public void run(RunData rundata, PipelineContext pipelineContext) throws Exception {
                throw new ResourceNotFoundException("not found!");
            }
        });

        invokeServlet("/myapps/app1/test.htm");

        assertEquals(404, clientResponseCode);
        assertEquals("text/html", clientResponse.getContentType());
        assertThat(clientResponseContent,
                   containsAll("<pre>", "</pre>", ResourceNotFoundException.class.getName(), "not found!"));
    }

    @Test
    public void valve_bad_request_exception() throws Exception {
        TestValve.runnerHolder.set(new ValveRunner() {
            public void run(RunData rundata, PipelineContext pipelineContext) throws Exception {
                throw new BadRequestException("bad request!");
            }
        });

        invokeServlet("/myapps/app1/test.htm");

        assertEquals(400, clientResponseCode);
        assertEquals("text/html", clientResponse.getContentType());
        assertThat(clientResponseContent,
                   containsAll("<pre>", "</pre>", BadRequestException.class.getName(), "bad request!"));
    }

    @Test
    public void valve_exception_exception() throws Exception {
        TestValve.runnerHolder.set(new ValveRunner() {
            public void run(RunData rundata, PipelineContext pipelineContext) throws Exception {
                throw new IllegalStateException("wrong!");
            }
        });

        setErrorHandler(controller, new RequestHandler() {
            public void handleRequest(RequestHandlerContext ctx) throws Exception {
                throw new IllegalArgumentException("wrong again!");
            }
        });

        try {
            invokeServlet("/myapps/app1/test.htm");
            fail();
        } catch (ServletException e) {
            // 真正的servlet engine在这里会显示web.xml中的错误页面，而测试时只会接收异常。
            assertThat(e, exception(IllegalStateException.class)); // 所抛出的是第一个应用产生的异常
            assertThat(e, not(exception(IllegalArgumentException.class))); // 而不是第二个errorHandler产生的异常
        }
    }

    @Test
    public void valve_exception_exception_2() throws Exception {
        TestValve.runnerHolder.set(new ValveRunner() {
            public void run(RunData rundata, PipelineContext pipelineContext) throws Exception {
                throw new IllegalStateException("wrong!");
            }
        });

        setErrorHandler(controller, new RequestHandler() {
            public void handleRequest(RequestHandlerContext ctx) throws Exception {
                throw new ServletException(ErrorHandlerHelper.getInstance(ctx.getRequest()).getException());
            }
        });

        try {
            invokeServlet("/myapps/app1/test.htm");
            fail();
        } catch (ServletException e) {
            // 真正的servlet engine在这里会显示web.xml中的错误页面，而测试时只会接收异常。
            assertThat(e, exception(IllegalStateException.class));
        }
    }

    @Test
    public void head() throws Exception {
        clientResponse = client.getResponse(new HeadMethodWebRequest("http://localhost/myapps/app1/internal.htm"));
        clientResponseCode = clientResponse.getResponseCode();
        clientResponseContent = clientResponse.getText();

        assertEquals(200, clientResponseCode);
        assertEquals(null, trimToNull(clientResponseContent));
    }

    @Test
    public void loadInternalRequestHandlers() throws Exception {
        Map<String, RequestHandler> handlers;

        // names are normalized and sorted
        // skipped empty name/class
        // skipped reserved name: error
        // skipped abstract and wrong type
        handlers = loadInternalHandlers("internal-request-handlers-test-1");
        assertArrayEquals(new String[] { "aa/bb/cc", "aa/cc" }, handlers.keySet().toArray(new String[0]));
    }

    @SuppressWarnings("unchecked")
    private Map<String, RequestHandler> loadInternalHandlers(String location) throws Exception {
        location = "META-INF/" + location;

        Object internalHandlerMapping = getFieldValue(controller, "internalHandlerMapping", null); // of inner type InternalHandlerMapping
        Method loadInternalHandlers = getAccessibleMethod(internalHandlerMapping.getClass(), "loadInternalHandlers",
                                                          new Class<?>[] { String.class });

        return (Map<String, RequestHandler>) loadInternalHandlers.invoke(internalHandlerMapping, location);
    }
}
