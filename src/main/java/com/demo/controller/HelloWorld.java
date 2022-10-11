package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloWorld {

    @RequestMapping("/hello")
    @ResponseBody
    public String getHello() {
        Map<String,Object> map = new HashMap<>();
        map.put("msg","hello world");

        String str = "hello world";
        return str;
    }
}
