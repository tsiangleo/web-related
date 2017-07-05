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

package com.github.tsiangleo.bookstore.account.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.github.tsiangleo.bookstore.account.common.AccountConstants;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Login {

    @Autowired
    private HttpServletRequest request;

    public void execute(Context context) {
//        String fullURL = request.getRequestURI();
//        if(null != request.getQueryString()){
//            fullURL += ("?"+request.getQueryString());
//        }
//        context.put("fullURL", fullURL);
//        System.out.println("#################fullURL:"+fullURL);

        String redirectURL = getRedirectURL();
        if (redirectURL != null) {

            try {
                redirectURL = URLDecoder.decode(redirectURL, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            System.out.println("#################after decode redirectURL:" + redirectURL);
            context.put("redirectURL", redirectURL);
        }else{
            context.put("redirectURL", "");
        }
    }

    private String getRedirectURL(){
        String queryString = request.getQueryString();
        String key = AccountConstants.REDIRECT_URL + "=";
        if(queryString == null || !queryString.contains(key)){
            return null;
        }
        return queryString.substring(queryString.indexOf(key)+key.length());
    }
}
