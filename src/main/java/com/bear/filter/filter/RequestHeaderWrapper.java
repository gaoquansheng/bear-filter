package com.bear.filter.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * 修改request的请求头的包装器，为了演示和修改request 请求体的包装器分开了
 */
public class RequestHeaderWrapper extends HttpServletRequestWrapper {

    Map<String, String> map = new HashMap<String, String>(16);

    public RequestHeaderWrapper(HttpServletRequest request) {
        super(request);
    }

    // 1。 自定义方法，新增请求头信息
    public void setHeader(String name, String value) {
        map.put(name, value);
    }

    // 3。 这里是将自定义到请求头到内容追加到原有到请求头内容中
    @Override
    public Enumeration<String> getHeaders(String name) {
        ArrayList<String> list = Collections.list(super.getHeaders(name));
        if (map.containsKey(name)) {
            list.clear(); // 也可以覆盖原有的请求头内容
            list.add(map.get(name));
        }
        return Collections.enumeration(list);
    }

    // 2。 将新增的请求头加入到原有到请求头列表中
    @Override
    public Enumeration<String> getHeaderNames() {
        // 将自定义的请求头塞进去
        ArrayList<String> list = Collections.list(super.getHeaderNames());
        list.addAll(map.keySet());
        return Collections.enumeration(list);
    }
}
