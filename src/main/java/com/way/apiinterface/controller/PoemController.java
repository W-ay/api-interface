package com.way.apiinterface.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/poem")
public class PoemController {
    @GetMapping
    public String getOnePoem(){
        return "海内存知己 天涯若比邻";
    }
}
