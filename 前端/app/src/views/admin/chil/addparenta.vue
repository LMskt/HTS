<template>
  <div>
    <div id="body">
      <div id="left">
        <p>家长姓名:</p>
        <el-input v-model="name" placeholder="请输入内容"></el-input>
        <el-divider></el-divider>
        <p>性别:</p>
        <el-radio v-model="sex" label="男">男</el-radio>
        <el-radio v-model="sex" label="女">女</el-radio>
        <el-divider></el-divider>
        <p>年龄:</p>
        <el-input v-model="age" placeholder="请输入内容"></el-input>
        <el-divider></el-divider>
        <p>家长账号:</p>
        <el-input v-model="username" placeholder="请输入内容"></el-input>
      </div>
      <div id="right">
        <div style="height: 250px">
          <div id="minileft">
            <p>联系方式:</p>
            <el-input v-model="phoneNumber" placeholder="请输入内容"></el-input>
            <el-divider></el-divider>
            <p>地址:</p>
            <el-input v-model="address" placeholder="请输入内容"></el-input>
          </div>
          <div id="miniright">
            <div style="width: 100%;text-align: center;margin-top: 50px">
              <el-button type="success" round @click="allsubmit">点击提交</el-button>
            </div>
            <el-divider></el-divider>
            <p>家长密码:</p>
            <el-input v-model="password" placeholder="请输入内容"></el-input>
          </div>
        </div>
        <div id="bottom">
          <el-upload
              class="upload-demo"
              ref="upload"
              action="http://localhost:8281/admin/addparimg"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :file-list="fileList"
              :headers="{'jwt':$store.getters.get_jwt}"
              :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {httpPost} from "@/api/api";

export default {
  name: "addparenta",
  data(){
    return{
      password:'',
      name:'',
      sex:'男',
      age:null,
      phoneNumber:'',
      address:'',
      username:'',
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
      if(this.password==''||this.name==''||this.age==null||this.phoneNumber==''||this.address==''||this.username==''){
        this.$message('内容不可为空')
      }else {
        var that=this
        httpPost({
          url:'admin/addpar',
          params:{
            name:that.name,
            sex:that.sex,
            age:that.age,
            phoneNumber:that.phoneNumber,
            address:that.address,
            username:that.username,
            password:that.password
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
  height: 500px;
  width: 900px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 25px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
#left{
  height: 400px;
  width: 24%;
  margin-left: 3%;
  float: left;
  margin-top: 10px;
}
#right{
  height: 400px;
  width: 67%;
  margin-right: 3%;
  float: right;
  margin-top: 10px;
}
#minileft{
  height: 250px;
  width: 40%;
  /*background-color: red;*/
  float: left;
  margin-left: 7%;
}
#miniright{
  height: 250px;
  width: 40%;
  /*background-color: red;*/
  /*margin-right: 5%;*/
  float: right;
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
