package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.schoolbehave;
//import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface SchoolbehaveMapper extends BaseMapper<schoolbehave> {
    //根据学生id查询他的学校任务
    @Select("SELECT schoolbehave.id,schoolbehave.`studentid`,schoolbehave.`taskname`,schoolbehave.`taskcontent`,schoolbehave.`taskbehave`,\n" +
            "schoolbehave.`time`,schoolbehave.`img`,schoolbehave.`teacherid`,students.`NAME` AS studentname,teachers.`NAME` AS teachername\n" +
            "FROM schoolbehave,students,teachers WHERE schoolbehave.`studentid`=students.`studentid` AND schoolbehave.`teacherid`=teachers.`teacherid` AND\n" +
            "students.`studentid`=#{studentid}")
    ArrayList<schoolbehave> findbystuid(String studentid);

    //根据老师的teacherid查询她手下的所有任务
    @Select("SELECT schoolbehave.id,schoolbehave.`studentid`,schoolbehave.`taskname`,schoolbehave.`taskcontent`,schoolbehave.`taskbehave`,\n" +
            "schoolbehave.`time`,schoolbehave.`img`,schoolbehave.`teacherid`,students.`NAME` AS studentname,teachers.`NAME` AS teachername\n" +
            "FROM schoolbehave,students,teachers WHERE schoolbehave.`studentid`=students.`studentid` AND schoolbehave.`teacherid`=teachers.`teacherid` AND\n" +
            "schoolbehave.`teacherid`=#{teacherid}")
    ArrayList<schoolbehave> findbyteaid(String teacherid);

    //老师添加任务
    @Insert("INSERT INTO schoolbehave(studentid,taskname,taskbehave,img,teacherid)VALUES(#{studentid},#{taskname},#{taskbehave},#{img},#{teacherid})")
    Boolean inserttask(String studentid,String taskname,String taskbehave,String img,String teacherid);

    //老师根据任务id删除任务
    @Delete("DELETE FROM schoolbehave WHERE id=#{id}")
    Boolean deletebyid(Integer id);

    //根据id查询学校任务
    @Select("SELECT schoolbehave.id,schoolbehave.`studentid`,schoolbehave.`taskname`,schoolbehave.`taskcontent`,schoolbehave.`taskbehave`,\n" +
            "schoolbehave.`time`,schoolbehave.`img`,schoolbehave.`teacherid`,students.`NAME` AS studentname,teachers.`NAME` AS teachername\n" +
            "FROM schoolbehave,students,teachers WHERE schoolbehave.`studentid`=students.`studentid` AND schoolbehave.`teacherid`=teachers.`teacherid` AND\n" +
            "schoolbehave.id=#{id}")
    schoolbehave find(Integer id);

    //添加图片
    @Update("UPDATE schoolbehave SET img=#{img} WHERE id=@@identity")
    Boolean addimg(String img);
}
