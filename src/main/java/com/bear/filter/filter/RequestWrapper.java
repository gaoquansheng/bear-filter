package com.bear.filter.filter;

import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 修改request的请求头的包装器，为了演示和修改request 请求体的包装器分开了
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    // 新增的请求头键值对
    Map<String, String> map = new HashMap<String, String>(16);
    // 请求体字节数组
    byte[] requestBody;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        requestBody = StreamUtils.copyToByteArray(request.getInputStream());
    }

    // 请求体的流只能读一次， 所以重写其方法，使用可重复读读流, 这里使得流可以重复读取
    @Override
    public ServletInputStream getInputStream() throws IOException {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody);

        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    // ===== 下面是修改请求体
    public void setRequestBody(byte[] requestBody) {
        this.requestBody = requestBody;
    }


    // ===== 下面是修改和新增请求头

    // 1。 自定义方法，新增请求头信息
    public void setHeader(String name, String value) {
        map.put(name, value);
    }

    // 3。 这里是将自定义的请求头内容返回
    @Override
    public Enumeration<String> getHeaders(String name) {

        if (map.containsKey(name)) {
            String value = map.get(name);
            return Collections.enumeration(List.of(value));
        }
        return super.getHeaders(name);
    }

    // 2。 将新增的请求头加入到原有到请求头列表中,同名怎么办？可以追加，也可以覆盖
    @Override
    public Enumeration<String> getHeaderNames() {
        // 将自定义的请求头塞进去
        ArrayList<String> list = Collections.list(super.getHeaderNames());
        list.addAll(map.keySet());
        return Collections.enumeration(list);
    }
}
