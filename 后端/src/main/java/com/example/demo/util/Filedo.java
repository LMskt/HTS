package com.example.demo.util;

import lombok.Data;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
@Data
public class Filedo {
    private String storagepath;
    private MultipartFile file;
    private ArrayList<String> format;

    public Filedo(MultipartFile file){
        ArrayList<String> geshi=new ArrayList<>();
        geshi.add("image/jpeg");
        geshi.add("image/png");
        this.format=geshi;
        this.storagepath="D:\\桌面文件\\test";
//        this.storagepath="D:\\桌面文件\\hts实验\\前端\\app\\public\\upfile";
        this.file=file;
    }
    //文件上传方法
    public  String load(){
        if (file.isEmpty()){
            return "不可上传空文件";
        }else {
            Boolean flag=false;
            for(String i:format){
                System.out.println(i);
                System.out.println(file.getContentType());
                if(file.getContentType().equals(i)){
                    flag=true;
                    break;
                }
            }
            if(!flag)
                return "文件格式错误";
            try {
                String filename = UUID.randomUUID().toString().replace("-","")+"."+ FilenameUtils.getExtension(file.getOriginalFilename());
                String filepath=storagepath.replaceAll("\\\\", Matcher.quoteReplacement(File.separator)).replaceAll("/",Matcher.quoteReplacement(File.separator));
                File datedir=new File(filepath);
                if(!datedir.exists())
                    datedir.mkdir();
                file.transferTo(new File(datedir,filename));

                return filename;//返回的是asd/asdas/类型的
//                return filepath+Matcher.quoteReplacement(File.separator)+filename;

            } catch (IOException e) {
                e.printStackTrace();
                return "文件上传失败";
            }
        }
    }
}
