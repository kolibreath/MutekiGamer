package com.guineap_pig_329.guinea_pig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@SpringBootApplication
public class GuineaPigApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(GuineaPigApplication.class, args);
//    }
    @RequestMapping("/index")
    public String index()
    {
        return "index";
    }
}
