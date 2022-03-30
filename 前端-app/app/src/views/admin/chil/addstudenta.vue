<template>
<div>
  <div id="body">
    <div id="left">
      <p>学生姓名:</p>
      <el-input v-model="name" placeholder="请输入内容"></el-input>
      <p>性别:</p>
      <el-radio v-model="sex" label="男">男</el-radio>
      <el-radio v-model="sex" label="女">女</el-radio>
      <p>年级:</p>
      <el-select v-model="grade" placeholder="请选择">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <p>年龄:</p>
      <el-input v-model="age" placeholder="请输入内容"></el-input>
      <p>学生学号:</p>
      <el-input v-model="studentid" placeholder="请输入内容"></el-input>
      <p>联系方式:</p>
      <el-input v-model="phoneNumber" placeholder="请输入内容"></el-input>
      <el-divider></el-divider>
      <p>地址:</p>
      <el-input v-model="address" placeholder="请输入内容"></el-input>
      <el-divider></el-divider>
      <p>负责老师工号:</p>
      <el-input v-model="teacherid" placeholder="请输入内容"></el-input>
      <div id="bottom">
        <el-upload
            class="upload-demo"
            ref="upload"
            action="http://192.168.43.167:8281/admin/addstuimg"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :file-list="fileList"
            :headers="{'jwt':$store.getters.get_jwt}"
            :auto-upload="false">
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
        <div style="width: 100%;text-align: center;margin-top: 50px">
          <el-button type="success" round @click="allsubmit">点击提交</el-button>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import {httpPost} from "@/api/api";
import router from "@/router";

export default {
  name: "addstudenta",
  data(){
    return{
      studentid:'',
      name:'',
      sex:'男',
      age:null,
      grade:'',
      phoneNumber:'',
      address:'',
      teacherid:'',
      options:[
        {
          value:'初一',
          label:'初一'
        },
        {
          value:'初二',
          label:'初二'
        },
        {
          value:'初三',
          label:'初三'
        },
        {
          value:'高一',
          label:'高一'
        },
        {
          value:'高二',
          label:'高二'
        },
        {
          value:'高三',
          label:'高三'
        },
      ],
      fileList:[]
    }
  },
  methods:{
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    submitUpload() {
      this.$refs.upload.submit();
    },
    allsubmit:function (){
      if(this.studentid==''||this.name==''||this.age==null||this.grade==''||this.phoneNumber==''||this.address==''||this.teacherid==''){
        this.$message('内容不可为空')
      }else {
        var that=this
        httpPost({
          url:'admin/addstudent',
          params:{
            studentid:that.studentid,
            name:that.name,
            sex:that.sex,
            age:that.age,
            grade:that.grade,
            phoneNumber:that.phoneNumber,
            address:that.address,
            teacherid:that.teacherid
          }
        }).then(res=>{
          if(res.errorCode==0){
            that.submitUpload()
            that.$message("添加成功")
           var giao=setTimeout(" window.location.reload();",500)
          }else {
            that.$message('添加失败')
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
  height: 1000px;
  width: 280px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 25px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
#left{
  height: 1000px;
  width: 250px;
  margin-left: 10px;
  /*float: left;*/
  margin-top: 10px;
}

#bottom{
  height: 200px;
  width: 80%;
  /*background-color: red;*/
  /*padding-left: 20px;*/
  /*float: bottom;*/
  margin-left: 7%;
}
</style>
