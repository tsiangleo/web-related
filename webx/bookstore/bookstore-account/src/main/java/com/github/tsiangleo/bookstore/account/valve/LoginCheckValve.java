package com.github.tsiangleo.bookstore.account.valve;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.service.uribroker.URIBrokerService;
import com.alibaba.citrus.service.uribroker.uri.URIBroker;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.github.tsiangleo.bookstore.account.common.AccountConstants;
import com.github.tsiangleo.bookstore.account.context.LoginContext;
import com.github.tsiangleo.bookstore.account.context.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by tsiang on 2017/6/13.
 */
public class LoginCheckValve extends AbstractValve {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private URIBrokerService uriBrokerService;

    private List<String> targetWhiteList; //登陆白名单

    private void printxx(){
        System.out.println("##############################################");
        System.out.println("getRequestURL: " + request.getRequestURL());
        System.out.println("getRequestURI: "+request.getRequestURI());
        System.out.println("getQueryString: "+request.getQueryString());
        System.out.println("getRemoteAddr: "+request.getRemoteAddr());
        System.out.println("getRemoteHost: "+request.getRemoteHost());
        System.out.println("getRemotePort: "+request.getRemotePort());
        System.out.println("getRemoteUser: "+request.getRemoteUser());
        System.out.println("getLocalAddr: "+request.getLocalAddr());
        System.out.println("getLocalName: "+request.getLocalName());
        System.out.println("getLocalPort: "+request.getLocalPort());
        System.out.println("getMethod: "+request.getMethod());
        System.out.println("##############################################");
    }

    @Override
    public void invoke(PipelineContext pipelineContext) throws Exception {

        settingLoginContext();

        printxx();

        TurbineRunData runData = TurbineUtil.getTurbineRunData(request);

        String target = runData.getTarget();



        if(target.endsWith(".js") || target.endsWith(".ico") || target.endsWith(".css") || target.endsWith(".png")){
            pipelineContext.invokeNext();
            return;
        }


        if(targetWhiteList.contains(target)) {
            pipelineContext.invokeNext();
            return;
        }

        if(isLogin()){
            pipelineContext.invokeNext();
            return;
        }

        String redirectURL = getRedirectURL(runData);

        URIBroker loginPageURI = uriBrokerService.getURIBroker("accountLoginLink");
        loginPageURI.addQueryData(AccountConstants.REDIRECT_URL, redirectURL);

        runData.setRedirectLocation(loginPageURI.toString());

    }

    private void settingLoginContext() {
        HttpSession session = request.getSession();
        if(session.isNew()){
            System.out.println("new session, id is:" + session.getId());
        }

        //从session中获取登陆用户信息
        LoginContext loginContext = (LoginContext)session.getAttribute(AccountConstants.USER_SESSION_KEY);
        if(loginContext == null){
            System.out.println("############# LoginCheckValve.invoke() -> Current Thread Id:" + Thread.currentThread().getId()+",found user NOT login,URL:"+request.getRequestURL());
        }else {
            //将session中的userInfo设置到当前线程中。
            /**
             * 感想：为什么要放在线程上下文中？
             *
             * 1. 一个线程处理一个请求。
             * 2. 一个请求的处理流程会经过多个组件。在valve等靠前的组件中，可以将userid等信息放在ThreadLocal中，便于后续的组件直接从ThreadLocal中提取对应的信息。
             *
             */
            UserInfoUtil.setLoginContext(loginContext);
            System.out.println("############# LoginCheckValve.isLogin() -> Current Thread Id:" + Thread.currentThread().getId() + ",setting LoginContext in ThreadLocal,URL:" + request.getRequestURL());
        }
    }

    private boolean isLogin(){
        boolean isLogin = UserInfoUtil.getLoginContext().getLoginName() != null;
        return  isLogin;
    }

    private String getRedirectURL(TurbineRunData runData){

        String redirectURL = runData.getRequest().getRequestURL().toString();
        String query = runData.getRequest().getQueryString();
        if(query != null){
            redirectURL = redirectURL + "?"+ query;
        }

        return redirectURL;
    }

    public void setTargetWhiteList(List<String> targetWhiteList){
        this.targetWhiteList = targetWhiteList;
    }
}
