package com.bear.filter.filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ResponseWrapper extends HttpServletResponseWrapper {

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
    }

}
