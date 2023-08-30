package com.way.apiinterface.controller;

import com.way.apiinterface.utils.CSVUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController()
@RequestMapping("/poem")
public class PoemController {

    @GetMapping
    public String getOnePoem() {
        ArrayList<String> poems = CSVUtils.getPoems();
        int index = (int) (Math.random() * poems.size());
        return poems.get(index);
    }
}
