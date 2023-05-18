package com.sp.jwtdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
    @GetMapping("/welcome")
    public String home() {
        System.out.println("welcome");
        return "this is a private page";
    }

}
