package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("relationship")
public class relationship {
    //关系id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //学生id
    private String studentid;
    //父母id
    private String parentid;
    //请亲关系
    private String relation;
    //-----------------------以下为数据库不存在字段---------------------------
    //父母名字
    private String parentname;
    //学生名字
    private String studentname;

    public relationship(String studentid, String parentid, String relation) {
        this.studentid = studentid;
        this.parentid = parentid;
        this.relation = relation;
    }


    public relationship() {
    }
}
