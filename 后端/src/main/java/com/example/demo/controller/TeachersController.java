package com.example.demo.controller;

import com.example.demo.entity.homebehave;
import com.example.demo.entity.notice;
import com.example.demo.entity.schoolbehave;
import com.example.demo.entity.students;
import com.example.demo.result.ResultFailure;
import com.example.demo.result.ResultModel;
import com.example.demo.result.ResultNull;
import com.example.demo.result.ResultSucess;
import com.example.demo.service.*;
import com.example.demo.util.Filedo;
import com.example.demo.util.JWT;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.prism.shader.DrawEllipse_LinearGradient_REFLECT_AlphaTest_Loader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.HsqlMaxValueIncrementer;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@Slf4j
@Validated
@RequestMapping(value = "/teachers")
@Api(tags = "老师操作层")
public class TeachersController {
    @Resource
    private TeachersService teachersService;
    @Resource
    private StudentService studentService;
    @Resource
    private HomebehaveService homebehaveService;
    @Resource
    private SchoolbehaveService schoolbehaveService;
    @Resource
    private NoticeService noticeService;
    @Autowired
    HttpServletRequest request;


    @GetMapping("/login")
    @ApiOperation(value = "查询成功返回1，否则返回0")
    //usernama是老师的工号
    public ResultModel login(@RequestParam(required = true)String username,
                             @RequestParam(required = true)String password){
        try {
            Integer flag=teachersService.login(username, password);
            if (flag==0){
                return new ResultFailure("查无此用户");
            }else {
                HashMap<String,Object> map=new HashMap<>();
                map.put("jwt", JWT.createJwt(username,username,10800000));
                return new ResultSucess("登录成功",map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("系统出错");
        }
    }

    @GetMapping("/findimg")
    @ApiOperation(value = "获取头像")
    public ResultModel findimg(){
        try {
            String username=(String) request.getAttribute("ID");
            String img= teachersService.findimg(username);
            return new ResultSucess("查询成功",img);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("查询失败，系统故障");
        }
    }
    //-----------------------------------------对负责学生的操作
    @GetMapping("/findstu")
    @ApiOperation(value = "根据老师工号查找所负责的学生")
    public ResultModel finstu(){
        try {
            String teacherid=(String) request.getAttribute("ID");
            ArrayList<students> students=studentService.findbyteaid(teacherid);
            if(students.size()==0){
                return new ResultNull("暂无负责学生");
            }else {
                HashMap<String,Object> map=new HashMap<>();
                map.put("student",students);
                return new ResultSucess("查询成功",map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    //-------------------------------------对家庭任务操作
    @GetMapping("/findtaskh")
    @ApiOperation(value = "根据学号查询家庭任务")
    public ResultModel findtaskh(@RequestParam(required = true) String studentid){
        try {
            ArrayList<homebehave> behaves=homebehaveService.findbystuid(studentid);
            if(behaves.size()==0){
                return new ResultNull("暂无任务");
            }else {
                HashMap<String,Object> map=new HashMap<>();
                map.put("task",behaves);
                return new ResultSucess("查询成功",map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @GetMapping("/findhome")
    @ApiOperation(value = "通过id来查询任务")
    public ResultModel findhome(@RequestParam(required = true) Integer id){
        try {
            homebehave giao=homebehaveService.find(id);
            HashMap<String,Object> map=new HashMap<>();
            map.put("task",giao);
            return new ResultSucess("查询成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    //-------------------------------------对学校任务的操作
    @GetMapping("/findtasks")
    @ApiOperation(value = "根据学生学号查询学校任务")
    public ResultModel findtasks(@RequestParam(required = true)String studentid){
        try {
            ArrayList<schoolbehave> behaves=schoolbehaveService.findbystuid(studentid);
            if(behaves.size()==0){
                return new ResultNull("暂无任务");
            }else {
                HashMap<String,Object> map=new HashMap<>();
                map.put("task",behaves);
                return new ResultSucess("查询成功",map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @GetMapping("/find")
    @ApiOperation(value = "根据id来查询")
    public ResultModel find(@RequestParam(required = true)Integer id){
        try {
            schoolbehave giao=schoolbehaveService.find(id);
            HashMap<String,Object> map=new HashMap<>();
            map.put("task",giao);
            return new ResultSucess("查询成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @GetMapping("/findteatask")
    @ApiOperation(value = "查询老师之前发布的学校任务")
    public ResultModel findteatask(){
        try {
            String teacherid=(String)request.getAttribute("ID");
//            System.out.println(teacherid);
            ArrayList<schoolbehave> behaves=schoolbehaveService.findbyteaid(teacherid);
            if(behaves.size()==0){
                return new ResultNull("暂无任务");
            }else {
                HashMap<String,Object> map=new HashMap<>();
                map.put("task",behaves);
                return new ResultSucess("查询成功",map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @GetMapping("/deletetask")
    @ApiOperation(value = "根据任务id删除任务")
    public ResultModel deletetask(@RequestParam(required = true)Integer id){
        try {
            Boolean flag=schoolbehaveService.deletebyid(id);
            if (!flag){
                return new  ResultFailure("删除失败");
            }else {
                return new ResultSucess("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @PostMapping("/addtask")
    @ApiOperation(value = "发布学校任务")
    public ResultModel addtask(@RequestParam(required = true)String studentid,
                               @RequestParam(required = true)String taskname,
                               @RequestParam(required = true)String taskcontent,
                               @RequestParam(required = true)String taskbehave){
        try {
            String teacherid=(String) request.getAttribute("ID");
            String img=null;
            schoolbehave schoolbehave=new schoolbehave(studentid,taskname,taskcontent,taskbehave,img,teacherid);
            Integer flag=schoolbehaveService.inserttask(schoolbehave);
            if(flag>=0){
                return new ResultSucess("发布成功");
            }else {
                return new ResultFailure("发布失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统崩溃");
        }
    }

    @PostMapping("/addtaskimg")
    @ApiOperation(value = "插入图片")
    public ResultModel addimg(MultipartFile file){
        try {
            Filedo fi=new Filedo(file);
            String path=fi.load();
            Boolean flag=schoolbehaveService.addimg(path);
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
    @GetMapping("/findnoticebytea")
    @ApiOperation(value = "教师查询公告")
    public ResultModel findnoticebytea(){
        try {
            String teacherid=(String) request.getAttribute("ID");
            ArrayList<notice> notices=noticeService.findbytea(teacherid);
            if(notices.size()==0){
                return new ResultNull("暂无公告");
            }else {
                HashMap<String,Object> map=new HashMap<>();
                map.put("notice",notices);
                return new ResultSucess("查询成功",map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @GetMapping("/deletenoticebyid")
    @ApiOperation(value = "教师根据id来删除公告")
    public ResultModel deletenotice(@RequestParam(required = true)Integer id){
        try {
            Boolean flag=noticeService.deletenotice(id);
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

    @PostMapping("/addnotice")
    @ApiOperation(value = "教师添加新的公告")
    public ResultModel addnotice(@RequestParam(required = true) String noticetitle,
                                 @RequestParam(required = true) String noticecontent){
        try {
            String teacherid=(String) request.getAttribute("ID");
            notice not=new notice(noticetitle,noticecontent,teacherid);
            Integer flag=noticeService.addnotice(not);
            if(flag<=0){
                return new ResultFailure("添加任务失败");
            }else {
                return new ResultSucess("添加任务成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }
}
