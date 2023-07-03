package com.example.demo.controller.test1;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {
    // 文件上传 RequestMapping默认get请求
    @PostMapping("/upload")
    //MultipartFile对象是通过form表单的file类型请求的，是通过表单的name值获取的value值，也可以通过swagger测试
    public void upload(MultipartFile headerImg) throws IOException {
        // 单文件上传
        if (!headerImg.isEmpty()) {
            // 获取上传文件的原始名称，获取上传的文件的文件名 x.xxx（1.jpg）
            String originalFilename = headerImg.getOriginalFilename();
            // 设置上传文件的保存地址目录(存放在项目路径下)
            String dirPath = "C:\\Users\\Hongzhen\\IdeaProjects\\project1\\demo\\uploadFiles";

            // 字符串截取后缀名
//            String suffix = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf('.'));

            // 设置上传后的文件名称（拼接）  //获取uuid ，随机生成一个名字
            String newFileName = UUID.randomUUID() + "_" + originalFilename;

            //File.separator：自动匹配路径的分隔符（在不确定路径的分隔符是\还是/的时候可以使用）
            //上传文件
            headerImg.transferTo(new File(dirPath + File.separator + newFileName));
        }
    }
}