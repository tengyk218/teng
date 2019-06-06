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

public class TokenAuthenticationFilter extends ZuulFilter {

    private RedisTemplate redisTemplate;

    public TokenAuthenticationFilter(RedisTemplate redisTemplate){
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
        if(StringUtils.isNotBlank(uri) && uri.startsWith("/auth/")){
            //不需要验证
            return false;
        }else{
            return true;// 是否执行该过滤器，此处为true，说明需要过滤
        }
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        boolean result = checkToken(request);
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

    private Boolean checkToken(HttpServletRequest request){
        String token = getHeaderToken(request);
        if(StringUtils.isBlank(token)){
            return false;
        }

        DecodedJWT jwt = JWT.decode(token);
        Map<String, Claim> claims = jwt.getClaims();
        Claim claim = claims.get("user_name");
        if(claim == null){
            claim = claims.get("client_id");
        }
        if(claim == null){
            return false;
        }
        Boolean result = redisTemplate.opsForSet().isMember(claim.asString(),token);
        return result;
    }

    /**
     * 获取token
     * @param request
     * @return
     */
    private String getHeaderToken(HttpServletRequest request) {
        Enumeration headers = request.getHeaders("Authorization");
        String value;
        do {
            if (!headers.hasMoreElements()) {
                return null;
            }
            value = (String)headers.nextElement();
        } while(!value.toLowerCase().startsWith("Bearer".toLowerCase()));

        String token = value.substring("Bearer".length()).trim();
        return token;
    }

}