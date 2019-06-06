package com.ncme.zuul.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

public class TokenRefreshFilter extends ZuulFilter {

    private RedisTemplate redisTemplate;

    public TokenRefreshFilter(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE; // 可以在请求被路由之前调用
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER; //filter执行顺序，通过数字指定，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();
        if(StringUtils.isNotBlank(uri) && uri.equalsIgnoreCase("/auth/oauth/token")){
            String refreshToken = request.getParameter("refresh_token");
            if(StringUtils.isNotBlank(refreshToken)){
                return true;
            }else{
                //不需要验证
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String refreshToken = request.getParameter("refresh_token");
        boolean result = checkToken(refreshToken);
        if (result) {
            ctx.setSendZuulResponse(true); //对请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        } else {
            ctx.setSendZuulResponse(false); //不对其进行路由
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("{\"error\": \"invalid_grant\",\"error_description\": \"token invalid\"}");
            ctx.set("isSuccess", false);
            return null;
        }
    }

    private Boolean checkToken(String refreshToken){
        DecodedJWT jwt = JWT.decode(refreshToken);
        Map<String, Claim> claims = jwt.getClaims();
        Claim claim = claims.get("user_name");
        if(claim == null){
            return false;
        }
        Boolean result = redisTemplate.opsForSet().isMember(claim.asString(),refreshToken);
        return result;
    }

}