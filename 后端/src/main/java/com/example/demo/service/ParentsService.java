package com.example.demo.service;

import com.example.demo.entity.parents;
import com.example.demo.mapper.ParentsMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ParentsService {
    @Resource
    ParentsMapper parentsMapper;
    //查询家长表
    public List<parents> Findallparents(){
        return parentsMapper.Findallparents();
    }
    //登录查询
    public Integer login(String usernanem,String password){
        return parentsMapper.login(usernanem,password);
    }

    //根据账号删除
    public Boolean deletepar(String username){
        return parentsMapper.deletepar(username);
    }

    //查询所有
    public ArrayList<parents> findall(){
        return parentsMapper.Findallparents();
    }

    //增添家长
    public Integer insterpar(parents giao){
        return parentsMapper.insert(giao);
    }

    //修改家长信息
    public Integer updatepar(parents giao){
        return parentsMapper.updateById(giao);
    }

    public Boolean addimg(String img){
        return parentsMapper.addimg(img);
    }

    //获取头像
    public String findimg(String username){
        return parentsMapper.findimg(username);
    }

}
