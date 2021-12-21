package com.example.demo.service;

import com.example.demo.entity.schoolbehave;
import com.example.demo.mapper.SchoolbehaveMapper;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Slf4j
@Service
public class SchoolbehaveService {
    @Resource
    private SchoolbehaveMapper schoolbehaveMapper;

    //根据学生id查询
    public ArrayList<schoolbehave> findbystuid(String studentid){
        return schoolbehaveMapper.findbystuid(studentid);
    }

    //根据老师teacherid查询他的学校任务
    public ArrayList<schoolbehave> findbyteaid(String teacherid){
        return schoolbehaveMapper.findbyteaid(teacherid);
    }

    //添加任务
    public Integer inserttask(schoolbehave giao){
        return schoolbehaveMapper.insert(giao);
    }

    //根据任务id删除
    public Boolean deletebyid(Integer id){
        return schoolbehaveMapper.deletebyid(id);
    }

    //根据id来查询
    public schoolbehave find(Integer id){
        return schoolbehaveMapper.find(id);
    }

    public Boolean addimg(String img){
        return schoolbehaveMapper.addimg(img);
    }
}
