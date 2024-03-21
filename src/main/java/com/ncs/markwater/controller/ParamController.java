package com.ncs.markwater.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParamController {

    @Value("${arg1}")
    private String arg1;

    @Value("${arg2}")
    private Integer arg2;

    @RequestMapping("/justcallback")
    public String callback(){
        return arg1 + arg2;
    }
}
