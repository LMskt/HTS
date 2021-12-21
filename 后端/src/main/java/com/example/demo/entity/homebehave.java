package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("homebehave")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class homebehave {
    //id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //学生id
    private String studentid;
    //任务名称
    private String taskname;
    //任务内容
    private String taskcontent;
    //任务表现
    private String taskbehave;
    //发布时间
    private Timestamp TIME;
    //图片地址
    private String img;
    //发布家长账号
    private String username;
    //-------------------------以下为数据库没有字段
    //学生名字


    public homebehave(String studentid, String taskname, String taskcontent, String taskbehave, String img, String username) {
        this.studentid = studentid;
        this.taskname = taskname;
        this.taskcontent = taskcontent;
        this.taskbehave = taskbehave;
        this.img = img;
        this.username = username;
    }

    public homebehave() {
    }

    private String studentname;

    //家长姓名
    private String parentname;
}
