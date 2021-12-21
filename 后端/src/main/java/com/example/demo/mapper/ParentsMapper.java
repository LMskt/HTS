package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.parents;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ParentsMapper extends BaseMapper<parents> {
    //查询所有的父母信息
    @Select("SELECT * FROM parents")
    ArrayList<parents> Findallparents();
    //根据账号密码查询信息
    @Select("SELECT count(*) FROM parents WHERE username=#{username} AND password=#{password}")
    Integer login(String username,String password);

    //根据账号删除家长
    @Delete("DELETE FROM parents WHERE username=#{username}")
    Boolean deletepar(String username);

    @Update("UPDATE parents SET img=#{img} WHERE id=@@identity")
    Boolean addimg(String img);

    @Select("SELECT img FROM parents WHERE username=#{username}")
    String findimg(String username);
}
