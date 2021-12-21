package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.notice;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface NoticeMapper extends BaseMapper<notice> {
    //根据家长账号查询其所有孩子的老师发布的公告
//    @Select("SELECT DISTINCT notice.`id`,notice.`notice_content`,notice.`notice_title`,notice.`teacherid`," +
//            "notice.`time`,teachers.`NAME`AS teachername  FROM notice,teachers,students \n" +
//            "WHERE students.`studentid` IN (SELECT studentid FROM relationship " +
//            "WHERE parentid=#{parentid}) AND students.`teacherid`=teachers.`teacherid`")
//    ArrayList<notice> findbypar(String parentid);
    @Select("SELECT DISTINCT notice.`id`,notice.`notice_content`,notice.`notice_title`,notice.`teacherid`,\n" +
            "            notice.`time`,teachers.`NAME`AS teachername  FROM notice,teachers,students \n" +
            "            WHERE students.`studentid` AND students.`teacherid`=teachers.teacherid AND notice.`teacherid`=students.teacherid ")
    ArrayList<notice> findbypar(String parentid);
    //根据教师的工号查询
    @Select("SELECT DISTINCT notice.`id`,notice.`notice_content`,notice.`notice_title`,notice.`teacherid`, notice.`time`,teachers.`NAME`AS teachername FROM notice,teachers WHERE notice.`teacherid`=#{teacherid}")
    ArrayList<notice> findbytea(String teacherid);

    //根据id删除公告
    @Delete("DELETE FROM notice WHERE id=#{id}")
    Boolean deletenotice(Integer id);
}
