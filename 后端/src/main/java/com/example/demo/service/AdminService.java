package com.example.demo.service;

import com.example.demo.mapper.AdminMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AdminService {
    @Resource
    private AdminMapper adminMapper;

    //登录查询
    public Integer login(String username,String password){
        return adminMapper.login(username,password);
    }
}
