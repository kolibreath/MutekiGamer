package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.dao.ResultBean;
import com.guineap_pig_329.guinea_pig.image.ImageUploader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
public class FileUploadController {

    @PostMapping("upload/")
    public ResultBean singleFileUpload(@RequestParam("avatar") MultipartFile file,
                                       RedirectAttributes redirectAttributes) throws IOException {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return ResultBean.error(ResultBean.bad_request,"错误");
        }

        String path = ImageUploader.upload(file);
        return ResultBean.success(path);
    }
}
