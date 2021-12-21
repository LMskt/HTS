package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("students")
public class students {
    //学生id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //学生学号
    private String studentid;
    //学生姓名
    private String name;
    //性别
    private String sex;
    //年龄
    private Integer age;
    //年级
    private String grade;
    //手机号
    private String phoneNumber;
    //住址
    private String address;
    //照片
    private String img;
    //班主任工号
    private String teacherid;
    //-------------------------------以下为数据库不包含字段
    private String teachername;

    public students(String studentid, String name, String sex, Integer age, String grade, String phoneNumber, String address, String img, String teacherid) {
        this.studentid = studentid;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.grade = grade;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.img = img;
        this.teacherid = teacherid;
    }

    public students() {
    }
}
