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

package com.alibaba.citrus.service.mail.mock;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jvnet.mock_javamail.Mailbox;
import org.jvnet.mock_javamail.MockTransport;

public class MyMockTransport extends MockTransport {
    private static boolean err;

    public MyMockTransport(Session session, URLName urlname) {
        super(session, urlname);
    }

    @Override
    public void connect(String host, int port, String user, String password) throws MessagingException {
        if (err) {
            throw new MessagingException();
        }

        setConnected(true);
    }

    @Override
    public void close() throws MessagingException {
        if (err) {
            throw new MessagingException();
        }

        setConnected(false);
    }

    public static void setError(boolean e) {
        err = e;
    }

    @Override
    public void sendMessage(Message msg, Address[] addresses) throws MessagingException {
        for (Address addr : addresses) {
            if (addr instanceof InternetAddress) {
                addr = new InternetAddress(((InternetAddress) addr).getAddress());
            }

            Mailbox mailbox = Mailbox.get(addr);

            if (mailbox.isError()) {
                throw new MessagingException("Simulated error sending message to " + addr);
            }

            mailbox.add(new MimeMessage((MimeMessage) msg));
        }
    }
}
