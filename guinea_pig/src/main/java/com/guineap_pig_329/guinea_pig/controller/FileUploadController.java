package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.dao.ResultBean;
import com.guineap_pig_329.guinea_pig.image.ImageUploader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class FileUploadController {

    @PostMapping("upload/img")
    public ResultBean singleFileUpload(
            HttpSession httpSession,
            @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResultBean.error(ResultBean.internal_error,"资源路径错误");
        }
        String src = null;
        try {
            src = ImageUploader.upload(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(src == null)
            return ResultBean.error(ResultBean.bad_request,"生成外链异常");
        else
            return ResultBean.success(src);
    }
}
