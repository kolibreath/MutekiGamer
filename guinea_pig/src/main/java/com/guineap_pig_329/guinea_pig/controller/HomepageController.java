package com.guineap_pig_329.guinea_pig.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/***
 * 管理整个主页面的逻辑请求
 */
//todo restcontroller
@Controller
@RequestMapping("homepage")
public class HomepageController {
    //社区跳转
    @RequestMapping("/community")
    public String community(){
        return "community";
    }
    //头条跳转
    @RequestMapping("/news")
    public String news(){
        return "news";
    }
    //战场
    @RequestMapping("/battle")
    public String battle(){
        return "battle";
    }
    //我
    @RequestMapping("/my")
    public String my(){
        return "my";
    }


}
