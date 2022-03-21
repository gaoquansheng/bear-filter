package com.bear.filter.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class MyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        RequestWrapper requestWrapper = new RequestWrapper(request);
        ResponseWrapper responseWrapper = new ResponseWrapper(response);
        // 添加自定义请求头
        requestWrapper.setHeader("name", "自定义请求头");
        // 解析请求体， 修改请求体
        ServletInputStream inputStream = requestWrapper.getInputStream();
        byte[] bytes = inputStream.readAllBytes();
        JSONObject jsonObject = JSONObject.parseObject(new String(bytes));
        Object data = jsonObject.get("data");
        byte[] bytes1 = JSON.toJSONBytes(data);
        requestWrapper.setRequestBody(bytes1);
        filterChain.doFilter(requestWrapper, responseWrapper);
    }
}
