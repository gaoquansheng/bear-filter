package com.bear.filter.controller;


import com.bear.filter.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @RequestMapping("/test")
    public void test() {
        System.out.println("hello");
    }

    @PostMapping("/user")
    public String user(@RequestBody User user, @RequestHeader("name") String name) {
        System.out.println(user);
        System.out.println(name);
        return "hello";
    }
}
