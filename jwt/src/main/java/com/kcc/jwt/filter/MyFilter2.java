package com.kcc.jwt.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class MyFilter2 implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter2 :: doFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
