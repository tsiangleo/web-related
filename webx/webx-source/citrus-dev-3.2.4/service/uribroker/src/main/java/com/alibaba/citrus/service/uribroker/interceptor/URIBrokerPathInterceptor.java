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

package com.alibaba.citrus.service.uribroker.interceptor;

import com.alibaba.citrus.service.uribroker.uri.URIBroker;

/**
 * 在运行时对URI Broker的path进行处理
 *
 * @author Michael Zhou
 */
public interface URIBrokerPathInterceptor extends URIBrokerInterceptor {
    /**
     * 处理指定的uri broker，并返回修改后的path。
     * <p>
     * 对于同一个broker，在reset()之后的多次render中，每次render均会调用此方法。
     * </p>
     */
    String perform(URIBroker broker, String path);
}
