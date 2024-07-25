package com.gou.priyanshu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/welcome")
public class Welcome {

    @GetMapping
    public String welcome() {
        return "Welcome";
    }
}
