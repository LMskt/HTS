package com.example.demo.controller;

import com.example.demo.entity.parents;
import com.example.demo.entity.students;
import com.example.demo.entity.teachers;
import com.example.demo.result.ResultFailure;
import com.example.demo.result.ResultModel;
import com.example.demo.result.ResultNull;
import com.example.demo.result.ResultSucess;
import com.example.demo.service.AdminService;
import com.example.demo.service.ParentsService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeachersService;
import com.example.demo.util.Filedo;
import com.example.demo.util.JWT;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@Slf4j
@Validated
@RequestMapping(value = "/admin")
@Api(tags = "管理员")
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private StudentService studentService;
    @Resource
    private TeachersService teachersService;
    @Resource
    private ParentsService parentsService;
    @Autowired
    HttpServletRequest request;

    @GetMapping("/login")
    @ApiOperation("管理员登录接口")
    public ResultModel login(@RequestParam(required = true) String username,
                             @RequestParam(required = true) String password){
        try {
            Integer flag=adminService.login(username,password);
            if(flag<=0){
                return new ResultFailure("登录失败，查无此用户");
            }else {
                HashMap<String,Object> map=new HashMap<>();
                map.put("jwt", JWT.createJwt(username,username,10800000));
                return new ResultSucess("登录成功",map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    //对学生的操作--------------------------------------------------------------------------
    @PostMapping("/addstudent")
    @ApiOperation("增添学生")
    public ResultModel addstudent(@RequestParam(required = true)String studentid,
                                  @RequestParam(required = true)String name,
                                  @RequestParam(required = true)String sex,
                                  @RequestParam(required = true)Integer age,
                                  @RequestParam(required = true)String grade,
                                  @RequestParam(required = true)String phoneNumber,
                                  @RequestParam(required = true)String address,
                                  @RequestParam(required = true)String teacherid){
        try {
            String img=null;
            students student=new students(studentid,name,sex,age,grade,phoneNumber,address,img,teacherid);
            Integer flag=studentService.insertstudent(student);
            if(flag<=0){
                return new ResultFailure("添加失败");
            }else {
                return new ResultSucess("添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @PostMapping("/addstuimg")
    @ApiOperation(value = "添加图片")
    public ResultModel addimg(MultipartFile file){
        try {
            Filedo fi=new Filedo(file);
            String path=fi.load();
            Boolean flag=studentService.addimg(path);
            if(flag){
                return new ResultSucess("添加成功");
            }else {
                return new ResultFailure("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @GetMapping("/deletestu")
    @ApiOperation("删除学生")
    public ResultModel deletestu(String studentid){
        try {
            Boolean flag=studentService.deletebystudentid(studentid);
            if(flag){
                return new ResultSucess("删除成功");
            }else {
                return new ResultFailure("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @GetMapping("/findstu")
    @ApiOperation("查询所有学生")
    public ResultModel findstu(){
        try {
            ArrayList<students> stus=studentService.findall();
            if(stus.size()<=0){
                return new ResultNull("暂无学生");
            }else {
                HashMap<String,Object> map=new HashMap<>();
                map.put("student",stus);
                return new ResultSucess("查询成功",map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @GetMapping("/updatestu")
    @ApiOperation("修改学生信息")
    public ResultModel update(@RequestParam(required = true)Integer id,
                              @RequestParam(required = true)String studentid,
                              @RequestParam(required = true)String name,
                              @RequestParam(required = true)String sex,
                              @RequestParam(required = true)Integer age,
                              @RequestParam(required = true)String grade,
                              @RequestParam(required = true)String phoneNumber,
                              @RequestParam(required = true)String address,
                              @RequestParam(required = true)String teacherid,
                              @RequestParam(required = false)MultipartFile file){
        try {
            String img=null;
            if(file!=null){
                Filedo fi=new Filedo(file);
                img=fi.load();
            }
            students student=new students(studentid,name,sex,age,grade,phoneNumber,address,img,teacherid);
            student.setId(id);
            Integer flag=studentService.updatestu(student);
            if(flag<=0){
                return new ResultFailure("修改失败");
            }else {
                return new ResultSucess("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }
    //-----------------------------------------------------------对老师操作
    @PostMapping("/addtea")
    @ApiOperation("增添新教师")
    public ResultModel addtea(@RequestParam(required = true)String teacherid,
                              @RequestParam(required = true)String name,
                              @RequestParam(required = true)String sex,
                              @RequestParam(required = true)Integer age,
                              @RequestParam(required = true)String phoneNumber,
                              @RequestParam(required = true)String address,
                              @RequestParam(required = true)String password){
        try {
            String img=null;
            teachers teacher=new teachers(teacherid,name,sex,age,phoneNumber,address,img,password);
            Integer flag=teachersService.insertteacher(teacher);
            if(flag<=0){
                return new ResultFailure("添加失败");
            }else {
                return new ResultSucess("添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @PostMapping("/addteaimg")
    @ApiOperation(value = "添加图片")
    public ResultModel addteaimg( MultipartFile file){
        try {
            Filedo fi=new Filedo(file);
            String path=fi.load();
            Boolean flag=teachersService.addimg(path);
            if(flag){
                return new ResultSucess("添加成功");
            }else {
                return new ResultFailure("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @GetMapping("/deletetea")
    @ApiOperation("删除教师")
    public ResultModel deletetea(@RequestParam(required = true)String teacherid){
        try {
            Boolean flag=teachersService.deletebyteaid(teacherid);
            if(flag){
                return new ResultSucess("删除成功");
            }else {
                return new ResultFailure("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @GetMapping("findtea")
    @ApiOperation("查询所有教师")
    public ResultModel findtea(){
        try {
            ArrayList<teachers> stus=teachersService.findall();
            if(stus.size()<=0){
                return new ResultNull("暂无老师");
            }else {
                HashMap<String,Object> map=new HashMap<>();
                map.put("teacher",stus);
                return new ResultSucess("查询成功",map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @PostMapping("/updatetea")
    @ApiOperation("修改教师信息")
    public ResultModel updatetea(@RequestParam(required = true)Integer id,
                                 @RequestParam(required = true)String teacherid,
                                 @RequestParam(required = true)String name,
                                 @RequestParam(required = true)String sex,
                                 @RequestParam(required = true)Integer age,
                                 @RequestParam(required = true)String phoneNumber,
                                 @RequestParam(required = true)String address,
                                 @RequestParam(required = true)String password,
                                 @RequestParam(required = false)MultipartFile file){
        try {
            String img=null;
            if(file!=null){
                Filedo fi=new Filedo(file);
                img=fi.load();
            }
            teachers teacher=new teachers(teacherid,name,sex,age,phoneNumber,address,img,password);
            teacher.setId(id);
            Integer flag=teachersService.updateteacher(teacher);
            if(flag<=0){
                return new ResultFailure("修改失败");
            }else {
                return new ResultSucess("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    //----------------------------------------------------------------对父母的操作
    @PostMapping("/addpar")
    @ApiOperation("增添父母")
    public ResultModel addpar(@RequestParam(required = true)String name,
                              @RequestParam(required = true)String sex,
                              @RequestParam(required = true)Integer age,
                              @RequestParam(required = true)String phoneNumber,
                              @RequestParam(required = true)String address,
                              @RequestParam(required = true)String username,
                              @RequestParam(required = true)String password){
        try {
            String img=null;
            parents parent=new parents(name,sex,age,phoneNumber,address,img,username,password);
            Integer flag=parentsService.insterpar(parent);
            if(flag<=0){
                return new ResultFailure("添加失败");
            }else {
                return new ResultSucess("添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }

    }

    @PostMapping("/addparimg")
    @ApiOperation(value = "添加图片")
    public ResultModel addparimg(MultipartFile file){
        try {
            Filedo fi=new Filedo(file);
            String path=fi.load();
            Boolean flag=parentsService.addimg(path);
            if(flag){
                return new ResultSucess("发布成功");
            }else {
                return new ResultFailure("发布失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @GetMapping("/deletepar")
    @ApiOperation("删除父母")
    public ResultModel deletepar(@RequestParam(required = true) String username){
        try {
            Boolean flag=parentsService.deletepar(username);
            if(flag){
                return new ResultSucess("删除成功");
            }else {
                return new ResultFailure("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @GetMapping("/findpar")
    @ApiOperation("查询所有父母")
    public ResultModel finpar(){
        try {
            ArrayList<parents> stus=parentsService.findall();
            if(stus.size()<=0){
                return new ResultNull("暂无家长");
            }else {
                HashMap<String,Object> map=new HashMap<>();
                map.put("parent",stus);
                return new ResultSucess("查询成功",map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @PostMapping("/updatepar")
    @ApiOperation("修改父母信息")
    public ResultModel updatepar(@RequestParam(required = true)Integer id,
                                 @RequestParam(required = true)String name,
                                 @RequestParam(required = true)String sex,
                                 @RequestParam(required = true)Integer age,
                                 @RequestParam(required = true)String phoneNumber,
                                 @RequestParam(required = true)String address,
                                 @RequestParam(required = true)String username,
                                 @RequestParam(required = true)String password,
                                 @RequestParam(required = false)MultipartFile file){
        try {
            String img=null;
            if(file!=null){
                Filedo fi=new Filedo(file);
                img=fi.load();
            }
            parents parent=new parents(name,sex,age,phoneNumber,address,img,username,password);
            parent.setId(id);
            Integer flag=parentsService.updatepar(parent);
            if(flag<=0){
                return new ResultFailure("修改失败");
            }else {
                return new ResultSucess("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }
}
