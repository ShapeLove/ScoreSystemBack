package com.shape.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Shape on 2018/4/21.
 */
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
