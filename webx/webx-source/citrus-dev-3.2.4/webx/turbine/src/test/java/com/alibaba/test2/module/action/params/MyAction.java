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

package com.alibaba.test2.module.action.params;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.citrus.turbine.dataresolver.Params;
import org.springframework.beans.factory.annotation.Autowired;

public class MyAction {
    @Autowired
    private HttpServletRequest request;

    public void doSetData(@Params MyData data) {
        setAttribute(data);
    }

    private void setAttribute(Object data) {
        request.setAttribute("actionLog", data);
    }

    public static class MyData {
        private List<String> names;
        private int          value;

        public List<String> getNames() {
            return names;
        }

        public void setNames(List<String> names) {
            this.names = names;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
