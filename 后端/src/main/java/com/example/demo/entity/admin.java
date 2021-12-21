package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("admin")
public class admin {
    @TableId(type = IdType.AUTO)
    Integer id;

    String username;
    String password;
}
