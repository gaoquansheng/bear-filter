package com.bear.filter.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 读取响应体
 * 参考链接 https://blog.csdn.net/u011410529/article/details/78873404
 */
public class ResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream outputStream;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        outputStream = new ByteArrayOutputStream();
    }

    public byte[] getResponseData() {
        return outputStream.toByteArray();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {

            }

            @Override
            public void write(int i) throws IOException {
                outputStream.write(i);
            }
        };
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(outputStream);
    }
}
