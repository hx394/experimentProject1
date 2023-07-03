package com.example.demo.controller.test1;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.URLEncoder;
@RestController
public class DownloadController {
    public String getFileName(HttpServletRequest request, String filename) throws Exception {
        //IE不同版本User-Agent中出现的各种关键词
        String[] IEBrowserKeyWords = {"MSIE", "Trident", "Edge"};
        //获取请求头代理信息
        String userAgent = request.getHeader("User-Agent");
        for (String keyWord : IEBrowserKeyWords) {
            if (userAgent.contains(keyWord)) {
                //IE内核浏览器，统一为UTF-8编码显示
                return URLEncoder.encode(filename, "UTF-8");
            }
        }
        //其它浏览器统一为ISO-8859-1编码显示
        return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
    }
    // 文件下载
    @PostMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request, String filename) throws Exception {
        // 确定要下载的文件路径
        String dirPath = "C:\\Users\\Hongzhen\\IdeaProjects\\project1\\demo\\uploadFiles";

        // 创建该文件对象(谷歌所用)
        File file = new File(dirPath + "/" + filename);
        System.out.println(file);

        //对文件名编码，防止中文乱码
        filename = this.getFileName(request,filename);

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        // 通知浏览器以下载的方式打开文件
        headers.setContentDispositionFormData("attachment", filename);
        // 定义以流的方式下载返回文件的数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 将该对象读取时按字节传送
        byte[] array = FileUtils.readFileToByteArray(file);
        // 使用SpringMVC框架的ResponseEntity对象封装返回下载数据
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(array, headers, HttpStatus.OK);
        return responseEntity;
    }
}
