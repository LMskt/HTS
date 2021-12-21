package com.example.demo.service;

import com.example.demo.entity.homebehave;
import com.example.demo.mapper.HomebehaveMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Slf4j
@Service
public class HomebehaveService {
    @Resource
    private HomebehaveMapper homebehaveMapper;

    //根据学生id查询
    public ArrayList<homebehave> findbystuid(String studentid){
        return homebehaveMapper.findbystuid(studentid);
    }

    //根据家长的username查询她手下的所有任务
    public ArrayList<homebehave> findbyparid(String username){
        return homebehaveMapper.findbyparid(username);
    }

    //增添任务
    public Integer inserttask(homebehave giao){
       return homebehaveMapper.insert(giao);
    }

    //根据任务id删除任务
    public Boolean deletebyid(Integer id){
        return homebehaveMapper.deletebyid(id);
    }

    //根据任务id来查询任务
    public homebehave find(Integer id){
        return homebehaveMapper.find(id);
    }

    public Boolean addimg(String img){
        return homebehaveMapper.addimg(img);
    }
}
