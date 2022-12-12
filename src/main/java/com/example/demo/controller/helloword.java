package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloword {

    @GetMapping("/helloworld")
    public int hello(){
        return 1;
    }
}
