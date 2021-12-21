package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.relationship;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;

@Mapper
public interface RelationshipMapper extends BaseMapper<relationship> {
    //增添新的关系
    @Insert("INSERT INTO relationship(studentid,parentid,relation)VALUES(#{studentid},#{parentid},#{relation})")
    Boolean addrelationship(String studentid,String parentid,String relation);

    //根据家长账号查询关系
    @Select("SELECT relationship.`id`,relationship.`studentid`,relationship.`parentid`,relationship.`relation`," +
            "students.`NAME`AS studentname,parents.`name`AS parentname FROM students,relationship,parents " +
            "WHERE students.`studentid` = relationship.`studentid`AND parents.`username`=#{parentid}")
    ArrayList<relationship> findbyparid(String parentid);

    //根据学生学号查询关系
    @Select("SELECT  * FOMR relationship WHERE studentid=#{studentid}")
    ArrayList<relationship> findbystuid(String studentid);

    //根据id删除关系
    @Delete("DELETE FROM relationship WHERE id=#{id}")
    Boolean deletebyid(Integer id);

    //根据学号查询学生是否已有负责人
    @Select("SELECT COUNT(*) FROM relationship WHERE studentid=#{studentid}")
    Integer seeif(String studentid);


}
