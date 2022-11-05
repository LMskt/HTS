package com.example.demo.service;

import com.example.demo.entity.teachers;
import com.example.demo.mapper.TeachersMapper;
//import com.sun.org.apache.xpath.internal.operations.Bool;
//import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
@Slf4j
public class TeachersService {
    @Resource
    TeachersMapper teachersMapper;
    //登录
    public Integer login(String username,String password){
        return teachersMapper.login(username, password);
    }

    //添加新教师
    public Integer insertteacher(teachers giao){
        return teachersMapper.insert(giao);
    }

    //根据老师工号删除教师
    public Boolean deletebyteaid(String teacherid){
        return teachersMapper.deletebyteaid(teacherid);
    }

    //更行老师信息
    public Integer updateteacher(teachers giao){
        return teachersMapper.updateById(giao);
    }

    //查询所有老师
    public ArrayList<teachers> findall(){
        return teachersMapper.findall();
    }

    public Boolean addimg(String img){
        return teachersMapper.addimg(img);
    }

    //获取头像
    public String findimg(String teacherid){
        return teachersMapper.findimg(teacherid);
    }
}
