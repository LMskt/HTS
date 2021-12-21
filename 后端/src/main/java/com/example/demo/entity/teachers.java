package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("teachers")
public class teachers {
    //老师id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //老师工号
    private String teacherid;
    //老师姓名
    private String name;
    //性别
    private String sex;
    //年龄
    private Integer age;
    //手机号
    private String phoneNumber;
    //住址
    private String address;
    //照片
    private String img;
    //密码
    private String password;

    public teachers(String teacherid, String name, String sex, Integer age, String phoneNumber, String address, String img, String password) {
        this.teacherid = teacherid;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.img = img;
        this.password = password;
    }

    public teachers() {
    }
}
