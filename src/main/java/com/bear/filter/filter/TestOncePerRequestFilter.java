package com.bear.filter.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class TestOncePerRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println(request.getHeader("name"));

        RequestHeaderWrapper requestHeaderWrapper = new RequestHeaderWrapper(request);
        requestHeaderWrapper.setHeader("name", "hhhh");

        filterChain.doFilter(requestHeaderWrapper, response);

        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = {1,2};
        outputStream.write(bytes);
        outputStream.flush();
    }

}
