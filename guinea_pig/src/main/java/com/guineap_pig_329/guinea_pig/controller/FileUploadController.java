package com.guineap_pig_329.guinea_pig.controller;

//http://www.ityouknow.com/springboot/2018/01/12/spring-boot-upload-file.html
//前端需要指定multiple part

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller("upload/")
public class FileUploadController {

    @PostMapping("img")
    //和表单中的key 对应
    //上传完成之后会重定向
    public String uploadPic(@RequestParam("avatar")MultipartFile avatar, RedirectAttributes redirectAttributes){

        if(avatar.isEmpty()){
            redirectAttributes.addFlashAttribute("message","Please select file to upload");
            //todo  上传图片的部分
            return"redirect:/";
        }

        try {
            byte[] bytes = avatar.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Path path = Paths.get("muteki_gamer/"+f)
        return null;
    }
}
