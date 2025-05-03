package com.changmin.securewebapp.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

//        HttpServletResponse res = (HttpServletResponse) response;
//        res.setHeader("Access-Control-Allow-Origin", "*");
//        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE OPTIONS");
//        res.setHeader("Access-Control-Allow-Headers","*");
        HttpServletRequest req = (HttpServletRequest) request;
        if("OPTIONS".equalsIgnoreCase(req.getMethod())){
            log.info("OPTIONS 요청 들어옴: {}", req.getRequestURI());
        }

        chain.doFilter(request, response);
    }
}