package com.example.demo.util;

import com.example.demo.result.ResultFailure;
import com.example.demo.result.ResultModel;
import lombok.Data;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;

@Data
public class Fileupload {
    //文件上传方法
    public static String load(MultipartFile file,String storagepath,ArrayList<String> format){
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
                String filepath=storagepath.replaceAll("\\\\",Matcher.quoteReplacement(File.separator)).replaceAll("/",Matcher.quoteReplacement(File.separator));
                File datedir=new File(filepath);
                if(!datedir.exists())
                    datedir.mkdir();
                file.transferTo(new File(datedir,filename));
                return filepath+Matcher.quoteReplacement(File.separator)+filename;
            } catch (IOException e) {
                e.printStackTrace();
                return "文件上传失败";
            }
        }
    }
}
