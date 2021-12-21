package com.example.demo.service;

import com.example.demo.entity.students;
import com.example.demo.mapper.StudentsMapper;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class StudentService {
    @Resource
    private StudentsMapper studentsMapper;
    //根据学号查询学生
    public ArrayList<students> findbystudentid(String studentid){
        return studentsMapper.findbystudentid(studentid);
    }

    //查询所有的学生
    public ArrayList<students> findall(){
        return studentsMapper.findall();
    }

    //根据学号删除学生
    public Boolean deletebystudentid(String studentid){
        return studentsMapper.deletebystudentid(studentid);
    }

    //添加学生
    public Integer insertstudent(students giao){
        return studentsMapper.insert(giao);
    }

    //根据老师工号查询学生
    public ArrayList<students> findbyteaid(String teacherid){
        return studentsMapper.findbyteaid(teacherid);
    }

    //根据家长账号查询学生
    public ArrayList<students> findbyparid(String parentid){
        return studentsMapper.findbyparid(parentid);
    }

    //修改学生信息
    public Integer updatestu(students giao){
        return studentsMapper.updateById(giao);
    }

    //插入图片
    public Boolean addimg(String img){
        return studentsMapper.addimg(img);
    }
}
