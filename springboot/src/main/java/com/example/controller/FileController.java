package com.example.controller;

import com.example.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    //文件上传
    public Result upload(MultipartFile file){
        //返回文件的url路径
        return Result.success();
    }

    //文件下载
    public void download(){

    }
}
