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

package com.github.tsiangleo.bookstore.book.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.github.tsiangleo.bookstore.account.context.UserInfoUtil;
import com.github.tsiangleo.bookstore.book.service.BookService;
import com.github.tsiangleo.bookstore.book.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

public class Index {

    @Autowired
    private BookService bookService;

    public void execute(Context context) {
        try {
            context.put("bookList", bookService.list());

            System.out.println("###### WWW.Index UserInfoUtil.getLoginContext().getLoginName():"+UserInfoUtil.getLoginContext().getLoginName());
            boolean isLogin = UserInfoUtil.getLoginContext().getLoginName() != null;
            context.put("isLogin",isLogin);
            context.put("loginName",UserInfoUtil.getLoginContext().getLoginName());

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
