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

package com.alibaba.citrus.service.mail.support;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;

import com.alibaba.citrus.service.mail.MailException;
import com.alibaba.citrus.service.mail.session.MailStoreHandler;

/**
 * 默认的接收e-mail的处理器。
 *
 * @author Michael Zhou
 */
public class DefaultMailStoreHandler implements MailStoreHandler {
    /** 预处理连接。 */
    public void prepareConnection(Store store) throws MailException, MessagingException {
    }

    /**
     * 取得一次接收的邮件数。参数<code>messageCount</code>
     * 代表当前邮箱中包含的邮件总数，方法返回一个数字，代表一次接收邮件的数量，必须小于或等于<code>messageCount</code>。
     */
    public int getMessageCount(int messageCount) throws MailException {
        return messageCount;
    }

    /** 处理一个邮件。返回<code>true</code>代表删除邮件。 */
    public boolean processMessage(Message message) throws MailException, MessagingException {
        return false;
    }
}
