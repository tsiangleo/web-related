package com.alibaba.webx.tutorial.app1.valve;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.PipelineException;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.service.uribroker.URIBrokerService;
import com.alibaba.citrus.service.uribroker.uri.URIBroker;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.alibaba.citrus.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tsiang on 2017/5/30.
 */
public class SecurityCheckValveWebx3 extends AbstractValve {
    public static final String     REDIRECT_URL = "redirectURL";
    private String[]               unProtectedURLs;   //fURLs列表即为无需登录态检查的白名单URL。
    private Logger log;
    @Autowired
    private URIBrokerService uriBrokerService;
    @Autowired
    private HttpServletRequest request;

    /**
     * initialize SecurityMappingService and SecurityCheck Since this <code>Valve</code> is singleton, this is done
     * only once.
     */
    public void init() throws PipelineInitializationException {
        log = LoggerFactory.getLogger(SecurityCheckValveWebx3.class);

        if (log.isDebugEnabled()) {
            log.debug("SecurityCheckValve: entering init");
        }

        if (uriBrokerService == null) {
            log.error("could not get SecurityMappingService");
            throw new PipelineInitializationException("could not get URIBrokerService");
        }
    }

    /**
     * 判断是否登录
     * @param rundata
     * @return
     */
    private boolean isLogin(TurbineRunData rundata) {
        return false;
    }
    /**
     * 处理请求。
     *
     * @param url the url user is trying to access
     * @return true or false. Whether authentication is required
     */
    private boolean authenticationRequired(String url) {
        boolean authenticationRequired = true;

        if(unProtectedURLs != null) {
            for (int i = 0; i < unProtectedURLs.length; i++) {
                if (url.equals(unProtectedURLs[i])) {
                    authenticationRequired = false;
                    break;
                }
            }
        }
        return authenticationRequired;
    }


    @Override
    public void invoke(PipelineContext pipelineContext) throws Exception {

        boolean isAuthenticated = false;    //是否登录了
        TurbineRunData rundata = TurbineUtil.getTurbineRunData(request);

        //获取请求对应的Target
        String targetURL = rundata.getTarget();

        URIBroker loginPageURI =  uriBrokerService.getURIBroker("wluserLoginLink");// 登录菜鸟
        if (loginPageURI == null) {
            throw new PipelineException("no URI broker: ssoLoginLink");
        }
        //检查页面是否不允许访问
        boolean authenticationRequired = authenticationRequired(targetURL);
        if(targetURL.endsWith(".js")||targetURL.endsWith(".css")||targetURL.endsWith(".png")){
            authenticationRequired = false;
        }

        //该请求URL在白名单内，不需要授权,直接放行。
        if(!authenticationRequired) {
            pipelineContext.invokeNext();
            return;
        }

        //该请求URL没在白名单内，需要授权
        if (authenticationRequired) {
            if(targetURL.equals("/homepage")){
                rundata.setRedirectTarget("index.vm");
            }
            //检查登录状态
            isAuthenticated = isLogin(rundata);

            //用户已登录,直接放行。
            if(isAuthenticated) {
                pipelineContext.invokeNext();
                return;
            }
            //没登录，重定向到登录页面
            if (!isAuthenticated) {
                String redirectURL = rundata.getRequest().getRequestURL().toString();
                String query = rundata.getRequest().getQueryString();
                if (query != null) {
                    redirectURL = redirectURL + "?" + query;
                }
                loginPageURI.addQueryData(REDIRECT_URL, redirectURL);
                rundata.setRedirectLocation(loginPageURI.render());
            }
        }

    }

    public void setUrls(String urls) throws PipelineException {
        if(!StringUtil.isBlank(urls)){
            if(!urls.matches("^([^,]+)?$|^([^,]+){1}(,[^,]+)+$")){
                throw new PipelineException("bat format URLs:"+urls);
            }
            String[] fmtURLs = urls.split(",");
            if(fmtURLs != null){
                this.unProtectedURLs = fmtURLs;
            }
        }
    }

}