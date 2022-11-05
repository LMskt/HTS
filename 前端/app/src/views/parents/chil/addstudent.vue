<template>
<div>
  <div id="body">
    <div style="height: 30px;width: 100%;text-align: center;line-height: 30px;padding-top: 1px;">
      <h2>请根据学号添加负责学生</h2>
    </div>
    <div id="card">
      <div style="width: 50px;width: 50px;margin-left: auto;margin-right: auto">
        <img src="../../../../public/picture/student.png" style="height: 50px;width: 50px;">
      </div>
      <div class="hang">
        <el-input placeholder="请输入学生学号" v-model="studentid">
          <template slot="prepend">学号:</template>
        </el-input>
      </div>
      <div class="hang">
        <el-input placeholder="请输入与学生关系" v-model="relation">
          <template slot="prepend">关系:</template>
        </el-input>
      </div>
    </div>
    <div style="text-align: center;margin-top: 50px">
      <el-button :plain="true" type="info" round @click="submit">提交按钮</el-button>
    </div>

  </div>
</div>
</template>

<script>
import {httpGet} from "@/api/api";
import router from "@/router";
import {escape} from "../../../prevention/myxss";

export default {
name: "addstudent",
  data(){
  return{
    studentid:'',
    relation:''
  }
  },
  methods:{
  submit:function (){
    if(this.studentid==''||this.relation==''){
      this.$message('须填信息不可为空')
    }else {
      var that=this
      httpGet({
        url:'parents/addrelation',
        params:{
          studentid:escape(that.studentid),
          relation:escape(that.studentid)
        }
      }).then(res=>{
        if(res.errorCode==0){
          that.$message('添加成功')
          router.push("/parents/relationship")
        }else if(res.errorCode==-10){
          that.$message('查无此学生')
        }else if(res.errorCode==-7){
          that.$message('该学生已有负责人')
        }
      }).catch(err=>{
        console.log(err)
      })
    }
  }
  }
}
</script>

<style scoped>
#body{
  height: 500px;
  width: 900px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 25px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
#card{
  padding-top: 10px;
  margin-top: 26px;
  height: 300px;
  width: 600px;
  margin-left: auto;
  margin-right: auto;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
.hang{

  width: 80%;
  margin-right: auto;
  margin-left: auto;
  margin-top: 50px;
}
</style>
