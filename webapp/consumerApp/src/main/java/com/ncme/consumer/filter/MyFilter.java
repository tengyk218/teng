package com.ncme.consumer.filter;

import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Random;

@WebFilter(urlPatterns = "/**")
public class MyFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Random random = new Random();
        int logUUid = random.nextInt(10000);
        MDC.put("logUUid", logUUid+"");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
