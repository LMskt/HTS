package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.teachers;
//import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.*;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TeachersMapper extends BaseMapper<teachers> {
    //查询整张教师表
    @Select("SELECT * FROM teachers")
    ArrayList<teachers> findall();
    //登录查询
    @Select("SELECT COUNT(*) FROM teachers where teacherid=#{username} AND password=#{password}")
    Integer login(String username,String password);

    //添加新教师
    @Insert("INSERT INTO teachers(teacherid,name,sex,age,phone_number,address,img,password) VALUES (#{teacherid},#{name},#{sex},#{age},#{phonenumber},#{address},#{img},#{password})")
    Boolean insertteacher(String teacherid,String name,String sex,String age,String phonenumber,String address,String img,String password);

    //根据老师工号删除老师
    @Delete("DELETE FROM teachers WHERE teacherid=#{teacherid}")
    Boolean deletebyteaid(String teacherid);

    //插入图片
    @Update("UPDATE teachers SET img=#{img} WHERE id=@@identity")
    Boolean addimg(String img);

    //获取头像
    @Select("SELECT img FROM teachers WHERE teacherid=#{teacherid}")
    String findimg(String teacherid);


}
