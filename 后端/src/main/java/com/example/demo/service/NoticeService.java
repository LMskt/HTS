package com.example.demo.service;

import com.example.demo.entity.notice;
import com.example.demo.mapper.NoticeMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
@Slf4j
public class NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    //根据家长账号查询其所有孩子的老师发布的公告
    public ArrayList<notice> findbypar(String parentid){
        return noticeMapper.findbypar(parentid);
    }

    //根据教师工号查询
    public ArrayList<notice> findbytea(String teacherid){
        return noticeMapper.findbytea(teacherid);
    }

    //教师发布公告
    public Integer addnotice(notice giao){
        return noticeMapper.insert(giao);
    }

    //教师根据id来删除公告
    public Boolean deletenotice(Integer id){
        return noticeMapper.deletenotice(id);
    }



}
