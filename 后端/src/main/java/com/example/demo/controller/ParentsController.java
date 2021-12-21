package com.example.demo.controller;


import com.example.demo.entity.*;
import com.example.demo.result.ResultFailure;
import com.example.demo.result.ResultModel;
import com.example.demo.result.ResultNull;
import com.example.demo.result.ResultSucess;
import com.example.demo.service.*;
import com.example.demo.util.Filedo;
import com.example.demo.util.JWT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.multipart.MultipartFile;
import sun.dc.pr.PRError;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/parents")
@Api(tags = "父母表单操作")
@Slf4j
public class ParentsController {
    @Resource
    private ParentsService parentsService;
    @Resource
    private RelationshipService relationshipService;
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
    public ResultModel login(@RequestParam(required = true) String username,
                             @RequestParam(required = true) String password){
        try {
            Integer flog = parentsService.login(username,password);
            if(flog==0){
                return new  ResultFailure("查无此用户");
            }else {
                HashMap<String,Object> map=new HashMap<>();
                map.put("jwt", JWT.createJwt(username,username,10800000 ));
                return new ResultSucess("登录成功",map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("系统出错");
        }
    }

    @GetMapping("/findimg")
    @ApiOperation(value = "查询头像")
    public ResultModel findimg(){
        try {
            String username=(String) request.getAttribute("ID");
            String img=parentsService.findimg(username);
            return new ResultSucess("查询成功",img);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }

    }
//    ------------------------------------------血缘关系操作

    @GetMapping("/addrelation")
    @ApiOperation(value = "增添血缘关系")
    public ResultModel addrelation(@RequestParam(required = true)String studentid,
                                   @RequestParam(required = true)String relation){
        try {
            String parentid=(String) request.getAttribute("ID");
            relationship rela=new relationship(studentid,parentid,relation);
            Integer flag=relationshipService.insertrelationship(rela);
//            System.out.println(flag);
            if(flag==0){
                return new ResultFailure("关系添加失败");
            }else if(flag==-10){
                return new ResultFailure(-10,"查无此人");
            }else if(flag==-7){
                return new ResultFailure(-7,"该学生已有负责人");
            }
            else {
                return new ResultSucess("关系添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("系统出错");
        }
    }

    @GetMapping("/findrelationbypar")
    @ApiOperation(value = "根据家长账号查询关系")
    public ResultModel findrelabypid(){
        try {
            String parentid=(String) request.getAttribute("ID");
            ArrayList<relationship> relationships=relationshipService.findbyparid(parentid);
            if(relationships.size()==0){
                return new ResultNull("暂无关系");
            }else {
                HashMap<String,Object> map=new HashMap<>();
                map.put("relationship",relationships);
                return new ResultSucess("查询成功",map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("查询失败");
        }
    }

    @GetMapping("/deleterelationship")
    @ApiOperation(value = "根据id删除关系")
    public ResultModel deletebyid(@RequestParam(required = true) Integer id){
        try {
            Boolean flag=relationshipService.deletebyid(id);
            if(flag){
                return new ResultSucess("删除成功");
            }else {
                return new ResultFailure("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统出差");
        }
    }
//---------------------------------------------------家庭任务操作
    @GetMapping("/findtaskbystuid")
    @ApiOperation(value = "根据学生学号查询家庭任务")
    public ResultModel findtaskbystu(@RequestParam(required = true)String studentid){
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
            return new ResultFailure("查询失败，系统故障");
        }
    }

    @GetMapping("/findpartask")
    @ApiOperation(value = "查找家长之前发布的家庭任务")
    public ResultModel findpartask(){
        try {
            String parentid=(String)request.getAttribute("ID");
            ArrayList<homebehave> behaves=homebehaveService.findbyparid(parentid);
            if(behaves.size()==0){
                return new ResultNull("暂无任务");
            }else {
                HashMap<String,Object> map=new HashMap<>();
                map.put("task",behaves);
                return new ResultSucess("查询成功",map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("查询失败，系统故障");
        }

    }

    @GetMapping("/deletetaskbyid")
    @ApiOperation(value = "根据id删除家庭任务")
    public ResultModel deletetask(@RequestParam(required = true) Integer id){
        try {
            Boolean flag=homebehaveService.deletebyid(id);
            if(!flag){
                return new ResultFailure("删除失败");
            }else {
                return new ResultSucess("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @PostMapping(value = "/addtask")
    @ApiOperation(value = "增添家庭任务")
    public ResultModel addtask(@RequestParam(required = true)String studentid,
                               @RequestParam(required = true)String taskname,
                               @RequestParam(required = true)String taskcontent,
                               @RequestParam(required = true)String taskbehave){
        try {
            String username=(String)request.getAttribute("ID");
            String img=null;
            homebehave behave=new homebehave(studentid,taskname,taskcontent,taskbehave,img,username);
            Integer flag=homebehaveService.inserttask(behave);
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
    @PostMapping(value = "/addtaskimg")
        @ApiOperation(value = "增添图片")
    public ResultModel addimg( MultipartFile file){
        try {
            Filedo fi=new Filedo(file);
            String path=fi.load();
            Boolean flag=homebehaveService.addimg(path);
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

//------------------------------------------------学校任务操作
    @GetMapping("/findstaskbystuid")
    @ApiOperation(value = "根据学号查看学生学校任务")
    public ResultModel findtask(@RequestParam(required = true)String studentid){
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
            return new ResultFailure("查询失败，系统故障");
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

    //-----------------------------------------公告操作
    @GetMapping("/findnotice")
    @ApiOperation(value = "根据家长账号查询其所有孩子的老师发布的公告")
    public ResultModel findnotice(){
        try {
            String parentid=(String) request.getAttribute("ID");
            ArrayList<notice> notices=noticeService.findbypar(parentid);
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

}
