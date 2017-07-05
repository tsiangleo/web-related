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

package com.alibaba.citrus.service.requestcontext.lazycommit;

import static org.junit.Assert.*;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.citrus.service.requestcontext.AbstractRequestContextsTests;
import com.alibaba.citrus.test.TestUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 测试<code>LazyCommitRequestContext</code>。
 *
 * @author Michael Zhou
 */
public class LazyCommitRequestContextTests extends AbstractRequestContextsTests<LazyCommitRequestContext> {
    @BeforeClass
    public static void initFactory() {
        createBeanFactory("services-lazycommit.xml");
    }

    @Before
    public void init() throws Exception {
        invokeReadFileServlet("form.html");
        initRequestContext();
    }

    @Test
    public void sendErrorWithoutWrapper() throws IOException {
        assertFalse(response.isCommitted());
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        assertTrue(response.isCommitted());

        try {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            fail("IllegalStateException Expected");
        } catch (IllegalStateException e) {
        }
    }

    @Test
    public void sendError() throws Exception {
        assertFalse(requestContext.isError());

        assertFalse(newResponse.isCommitted());
        newResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        assertFalse(newResponse.isCommitted());

        newResponse.sendError(HttpServletResponse.SC_FORBIDDEN); // 无异常，但忽略之

        requestContexts.commitRequestContext(requestContext);
        assertTrue(newResponse.isCommitted());

        assertTrue(requestContext.isError());
    }

    @Test
    public void sendRedirectWithoutWrapper() throws IOException {
        assertFalse(response.isCommitted());
        response.sendRedirect("mylocation");
        assertTrue(response.isCommitted());

        try {
            response.sendRedirect("mylocation");
            fail("IllegalStateException Expected");
        } catch (IllegalStateException e) {
        }
    }

    @Test
    public void sendRedirect() throws Exception {
        assertFalse(newResponse.isCommitted());
        newResponse.sendRedirect("mylocation");
        assertFalse(newResponse.isCommitted());

        newResponse.sendRedirect("mylocation"); // 无异常，但忽略之

        requestContexts.commitRequestContext(requestContext);
        assertTrue(newResponse.isCommitted());
    }

    @Test
    public void flushBufferWithoutWrapper() throws IOException {
        assertFalse(response.isCommitted());
        response.flushBuffer();
        assertTrue(response.isCommitted());

        response.flushBuffer(); // 重复flush是没问题的
    }

    @Test
    public void flushBuffer() throws Exception {
        assertFalse(newResponse.isCommitted());
        newResponse.flushBuffer();
        assertFalse(newResponse.isCommitted());

        newResponse.flushBuffer(); // 重复flush是没问题的

        requestContexts.commitRequestContext(requestContext);
        assertTrue(newResponse.isCommitted());
    }

    @Test
    public void getRedirectLocation() throws Exception {
        assertNull(requestContext.getRedirectLocation());
        assertFalse(requestContext.isRedirected());

        newResponse.sendRedirect("/aaa/bbb");
        assertEquals("/aaa/bbb", requestContext.getRedirectLocation());

        assertTrue(requestContext.isRedirected());
    }

    @Test
    public void statusCode() throws Exception {
        assertEquals(0, requestContext.getStatus());

        newResponse.setStatus(302);
        assertEquals(302, requestContext.getStatus());
    }

    private boolean resetCalled;

    @Override
    protected void resetCalled() {
        resetCalled = true;
    }

    private void assertResetCalled() {
        assertTrue(resetCalled);
        resetCalled = false;
    }

    @Test
    public void reset() throws Exception {
        // sendError
        newResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        assertTrue(requestContext.isError());
        newResponse.reset();
        assertFalse(requestContext.isError());
        assertResetCalled();

        // sendRedirect
        newResponse.sendRedirect("http://localhost/mylocation");
        assertTrue(requestContext.isRedirected());
        newResponse.reset();
        assertFalse(requestContext.isRedirected());
        assertResetCalled();

        // setHeader:location
        newResponse.setHeader("location", "http://localhost/mylocation");
        assertTrue(requestContext.isRedirected());
        newResponse.reset();
        assertFalse(requestContext.isRedirected());
        assertResetCalled();

        // flushBuffer
        newResponse.flushBuffer();
        assertTrue(TestUtil.getFieldValue(requestContext, "bufferFlushed", Boolean.class));
        newResponse.reset();
        assertFalse(TestUtil.getFieldValue(requestContext, "bufferFlushed", Boolean.class));
        assertResetCalled();

        // setStatus
        newResponse.setStatus(404);
        assertEquals(404, (Object) TestUtil.getFieldValue(requestContext, "status", Integer.class));
        newResponse.reset();
        assertEquals(0, (Object) TestUtil.getFieldValue(requestContext, "status", Integer.class));
        assertResetCalled();
    }
}
