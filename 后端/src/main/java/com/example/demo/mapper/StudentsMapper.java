package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.students;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface StudentsMapper extends BaseMapper<students> {
    //根据学号查询学生
    @Select("SELECT * FROM students WHERE studentid=#{studentid}")
    ArrayList<students> findbystudentid(String studentid);

    //查询所有的学生
    @Select("SELECT students.`id`,students.`studentid`,students.`NAME`,students.`sex`,students.`age`,students.`grade`,students.`phone_number`,\n" +
            "students.`address`,students.`img` ,students.`teacherid`,teachers.`NAME` AS teachername FROM students,teachers WHERE students.`teacherid`=teachers.`teacherid`")
    ArrayList<students> findall();

    //根据学号删除学生
    @Delete("DELETE FROM students WHERE studentid=#{studentid}")
    Boolean deletebystudentid(String studentid);

    //增添学生
    @Insert("INSERT INTO students(studentid,NAME,sex,age,grade,phone_number,address,img,teacherid)VALUES(#{studentid},#{name},#{sex},#{age},#{grade},#{phonenumber},#{address},#{img},#{teacherid})")
    Boolean addstudent(String studentid,String name,String sex,Integer age,String grade,String phonenmber,String address,String img,String teacherid);

    //根据老师工号查询学生
    @Select("SELECT * FROM students WHERE teacherid= #{teacherid}")
    ArrayList<students> findbyteaid(String teacherid);
    //根据家长账号查询
    @Select("SELECT students.`id`,students.`studentid`,students.`NAME`,students.`sex`,students.`age`,students.`grade`,students.`phone_number`,\n" +
            "students.`address`,students.`img` ,students.`teacherid`,teachers.`NAME` AS teachername FROM students,teachers WHERE studentid \n" +
            "IN(SELECT studentid FROM relationship WHERE parentid=#{parentid})AND students.`teacherid`=teachers.`teacherid`")
    ArrayList<students> findbyparid(String parentid);

    //根据学号查询该学生是否存在
    @Select("SELECT COUNT(*) FROM students WHERE studentid=#{studentid}")
    Integer seestu(String studentid);

    @Update("UPDATE students SET img=#{img} WHERE id=@@identity")
    Boolean addimg(String img);
}

