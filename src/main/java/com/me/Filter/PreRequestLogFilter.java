package com.me.Filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by admin on 2017/8/15.
 */
public class PreRequestLogFilter extends ZuulFilter{
    private static final Logger logger = Logger.getLogger(PreRequestLogFilter.class);
    @Override
    /**
     * filterType:1.pre,请求被路由之前2.route.将强求发送给微服务3.post.微服务响应4.error.其他阶段
     */
    public String filterType() {
        return "pre";
    }

    /**
     * 返回值决定过滤器的执行优先级
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * true表示执行,false表示不执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request=requestContext.getRequest();
        logger.info(String.format("send %s request to %s",request.getMethod(),request.getRequestURL().toString()));
        return null;
    }
}
