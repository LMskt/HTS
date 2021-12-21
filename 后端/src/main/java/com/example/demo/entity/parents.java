package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("parents")
public class parents {
    //家长id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //家长姓名
    private String name;
    //性别
    private String sex;
    //年龄
    private Integer age;
    //手机号
    private String phoneNumber;
    //地址
    private String address;
    //照片
    private String img;
    //用户名
    private String username;
    //密码
    private String password;

    public parents(String name, String sex, Integer age, String phoneNumber, String address, String img, String username, String password) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.img = img;
        this.username = username;
        this.password = password;
    }

    public parents() {
    }
}
