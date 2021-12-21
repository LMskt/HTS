package com.example.demo.service;

import com.example.demo.entity.relationship;
import com.example.demo.mapper.RelationshipMapper;
import com.example.demo.mapper.StudentsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Slf4j
@Service
public class RelationshipService {
    @Resource
    RelationshipMapper relationshipMapper;
    @Resource
    StudentsMapper studentsMapper;

    //添加关系
    public Integer insertrelationship(relationship giao){
        Integer flag=studentsMapper.seestu(giao.getStudentid());
        if(flag<=0){
            return -10;//查无此人
        }else {
            Integer md= relationshipMapper.seeif(giao.getStudentid());
            if(md>0){
                return -7;//已有负责人
            }else {
                return relationshipMapper.insert(giao);
            }
        }
    }

    //根据家长账号查询
    public ArrayList<relationship> findbyparid(String parentid){
        return relationshipMapper.findbyparid(parentid);
    }

    //根据学生学号查询
    public ArrayList<relationship> findbystuid(String studentid){
        return relationshipMapper.findbyparid(studentid);
    }

    //根据id删除
    public Boolean deletebyid(Integer id){
        return relationshipMapper.deletebyid(id);
    }

    //修改关系信息
    public Integer updaterel(relationship giao){
        return relationshipMapper.updateById(giao);
    }

}
