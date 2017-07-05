package com.alibaba.webx.tutorial.app1.valve;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.PipelineException;
import com.alibaba.citrus.service.pipeline.Valve;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.service.uribroker.uri.URIBroker;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.alibaba.webx.tutorial.app1.domain.User;
import com.alibaba.webx.tutorial.app1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tsiang on 2017/5/30.
 */
public class MyValve extends AbstractValve implements Valve {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    public void invoke(PipelineContext pipelineContext) throws Exception {

        boolean isAuthenticated = false;    //是否登录了
        TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);

        //获取请求对应的Target
        String targetURL = rundata.getTarget();


        System.out.println("\n################### in MyValve.invoke()  ############################\n");

        System.out.println("targetURL:" + targetURL);

        User user = new User();
        user.setIp(request.getRemoteAddr());
        user.setTime(new java.util.Date().toString());
        userService.addUser(user);

        List list = userService.listAllUser();

        System.out.println("\nUserList size:"+list.size()+",userList:" + Arrays.toString(list.toArray()));

        System.out.println("\nCurrent Time:"+ userService.getCurrentTime());

        if(targetURL.startsWith("/redirect1")){
            rundata.setRedirectTarget("index.vm");
            return;
        }
        if(targetURL.startsWith("/redirect2")){
            rundata.setRedirectLocation("/internal/Explorer");
            return;
        }
        pipelineContext.invokeNext();

    }
}
