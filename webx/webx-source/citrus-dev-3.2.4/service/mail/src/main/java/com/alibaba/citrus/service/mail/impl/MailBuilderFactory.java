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

package com.alibaba.citrus.service.mail.impl;

import java.util.Map;

import com.alibaba.citrus.service.mail.builder.MailAddressType;
import com.alibaba.citrus.service.mail.builder.MailBuilder;
import com.alibaba.citrus.service.mail.builder.MailContent;
import org.springframework.beans.factory.FactoryBean;

public class MailBuilderFactory implements FactoryBean {
    private final MailBuilder builder = new MailBuilder();

    public void setCharset(String javaCharset) {
        builder.setCharacterEncoding(javaCharset);
    }

    public void setAddresses(Map<MailAddressType, String[]> addrList) {
        for (Map.Entry<MailAddressType, String[]> entry : addrList.entrySet()) {
            MailAddressType addrType = entry.getKey();
            String[] addrs = entry.getValue();

            for (String addr : addrs) {
                builder.addAddress(addrType, addr);
            }
        }
    }

    public void setSubject(String subject) {
        builder.setSubject(subject);
    }

    public void setContent(MailContent content) {
        builder.setContent(content);
    }

    public Object getObject() throws Exception {
        return builder;
    }

    public Class<?> getObjectType() {
        return builder.getClass();
    }

    public boolean isSingleton() {
        return true;
    }
}
