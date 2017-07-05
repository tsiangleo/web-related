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

package com.alibaba.citrus.service.velocity.impl;

import static org.junit.Assert.*;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler;
import org.apache.velocity.context.Context;
import org.apache.velocity.util.ContextAware;

public class ConditionalEscapeHandler implements ReferenceInsertionEventHandler, ContextAware, Cloneable {
    public final static ThreadLocal<ConditionalEscapeHandler> handlerHolder = new ThreadLocal<ConditionalEscapeHandler>();
    private transient Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public Object referenceInsert(String reference, Object value) {
        assertNotNull(context);

        handlerHolder.set(this);

        if ("true".equals(context.get("escape"))) {
            return "escaped " + value;
        } else {
            return value;
        }
    }
}
