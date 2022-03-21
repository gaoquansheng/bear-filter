package com.bear.filter.controller;


import com.bear.filter.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class TestController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;


    @PostMapping("/user")
    public String user(@RequestBody User user, @RequestHeader("name") String name) throws ServletException, IOException {
        System.out.println(user);
        System.out.println(name);
        return "hello";
    }
}
