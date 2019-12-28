package com.guineap_pig_329.guinea_pig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController2 {

    @RequestMapping("/1")
    public String turn2TestAjax(){
        return "TestAjax";
    }
}
