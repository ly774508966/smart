package com.rabbit.smart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by xuzhang on 2018/5/13.
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String index(HttpSession session) {
        return "Hello World";
    }

}
