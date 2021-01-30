package com.fastinjava.application.web.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public JSONObject test(){
        JSONObject jsonObject = new JSONObject().fluentPut("name", "lifuyong").fluentPut("age", 12);
        return jsonObject;
    }
}
