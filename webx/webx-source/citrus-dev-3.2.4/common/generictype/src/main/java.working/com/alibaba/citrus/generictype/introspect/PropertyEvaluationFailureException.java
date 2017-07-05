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

package com.alibaba.citrus.generictype.introspect;

/**
 * 对property取值失败的异常。
 *
 * @author Michael Zhou
 */
public class PropertyEvaluationFailureException extends PropertyException {
    private static final long serialVersionUID = 6310808071994767820L;

    public PropertyEvaluationFailureException() {
        super();
    }

    public PropertyEvaluationFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyEvaluationFailureException(String message) {
        super(message);
    }

    public PropertyEvaluationFailureException(Throwable cause) {
        super(cause);
    }
}
