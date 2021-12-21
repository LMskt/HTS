package com.example.demo.controller;

import com.example.demo.result.ResultModel;
import com.example.demo.util.Filedo;
import com.example.demo.util.Fileupload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@RestController
@Validated
@Slf4j
@RequestMapping(value = "/test")
public class TestController {

    @PostMapping(value = "/img")
    public String giao( MultipartFile file){
//        ArrayList<String> geshi=new ArrayList<>();
//        geshi.add("image/jpeg");
//        geshi.add("image/png");
//        String url="D:\\桌面文件\\test";
//        return Fileupload.load(file,url,geshi);
        Filedo fi=new Filedo(file);
        return fi.load();
    }
}
