package cn.itcast.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * LoginFilter
 * hasee
 * 2019/1/31
 * 16:37
 * 自定义过滤器....   进行登录验证
 * @Version 1.0
 **/
@Component
public class LoginFilter extends ZuulFilter {

    @Override
    public String filterType() {    //过滤器类型：  前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {      //执行顺序
        return FilterConstants.PRE_DECORATION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {     //是否开启过滤器
        return true;
    }

    @Override
    public Object run() throws ZuulException {  //过滤器的具体逻辑
        //获取请求上下文
        RequestContext ctx=RequestContext.getCurrentContext();
        //获取请求request
        HttpServletRequest request=ctx.getRequest();
        //获取请求参数 access-token
        String token=request.getParameter("access-token");
        //判断是否存在
        if (StringUtils.isBlank(token)){
            //不存在，未登录，则拦截
            ctx.setSendZuulResponse(false);
            //返回403
            ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        }
        return null;
    }
}
