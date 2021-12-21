package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.homebehave;
import com.example.demo.entity.schoolbehave;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface HomebehaveMapper extends BaseMapper<homebehave> {
    //根据学生id查询他的家庭任务
    @Select("SELECT homebehave.`id`,homebehave.`studentid`,homebehave.`taskname`,homebehave.`taskcontent`,homebehave.`taskbehave`,homebehave.`TIME`," +
            "homebehave.`img`,homebehave.`username`,students.`NAME` AS studentname,parents.`name` AS parentname FROM homebehave,students,parents " +
            "WHERE homebehave.studentid=students.studentid AND homebehave.`username`=parents.`username`  AND students.`studentid`=#{studentid}")
    ArrayList<homebehave> findbystuid(String studentid);

    //根据家长的username查询她手下的所有任务
    @Select("SELECT homebehave.`id`,homebehave.`studentid`,homebehave.`taskname`,homebehave.`taskbehave`,homebehave.`TIME`,homebehave.`img`,homebehave.`username`,students.`NAME` AS studentname FROM students JOIN homebehave WHERE homebehave.`studentid` = students.`studentid` AND homebehave.username=#{username}")
    ArrayList<homebehave> findbyparid(String username);

    //家长添加任务
    @Insert("INSERT INTO homebehave(studentid,taskname,taskbehave,img,username)VALUES(#{studentid},#{taskname},#{taskbehave},#{img},#{username})")
    Boolean inserttask(String studentid,String taskname,String taskbehave,String img,String username);

    //家长根据任务id删除任务
    @Delete("DELETE FROM homebehave WHERE id=#{id}")
    Boolean deletebyid(Integer id);

    //根据id来查询任务
    @Select("SELECT homebehave.`id`,homebehave.`studentid`,homebehave.`taskname`,homebehave.`taskcontent`,homebehave.`taskbehave`,homebehave.`TIME`," +
            "homebehave.`img`,homebehave.`username`,students.`NAME` AS studentname,parents.`name` AS parentname FROM homebehave,students,parents " +
            "WHERE homebehave.studentid=students.studentid AND homebehave.`username`=parents.`username`  AND homebehave.`id`=#{id}")
    homebehave find(Integer id);

    //插入图片
    @Update("UPDATE homebehave SET img=#{img} WHERE id=@@identity")
    Boolean addimg(String img);

}
