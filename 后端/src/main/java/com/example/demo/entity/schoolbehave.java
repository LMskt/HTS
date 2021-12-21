package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("schoolbehave")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class schoolbehave {
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
    //时间
    private Timestamp time;
    //图片地址
    private String img;
    //老师工号
    private String teacherid;
    //--------------------------以下为数据库没有的字段
    //学生姓名
    private String studentname;
    //老师姓名
    private String teachername;

    public schoolbehave(String studentid, String taskname, String taskcontent, String taskbehave, String img, String teacherid) {
        this.studentid = studentid;
        this.taskname = taskname;
        this.taskcontent = taskcontent;
        this.taskbehave = taskbehave;
        this.img = img;
        this.teacherid = teacherid;
    }

    public schoolbehave() {

    }
}
