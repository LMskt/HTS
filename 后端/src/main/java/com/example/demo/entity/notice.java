package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("notice")
public class notice {
    //id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //公告名字
    private String noticeTitle;
    //公告内容
    private String noticeContent;
    //教师id
    private String teacherid;
    //发布时间
    private Timestamp time;
    //-----------------------------以下为数据库中不存在字段
    //教师姓名
    private String teachername;

    public notice(String noticeTitle, String noticeContent, String teacherid) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.teacherid = teacherid;
    }

    public notice() {
    }
}
