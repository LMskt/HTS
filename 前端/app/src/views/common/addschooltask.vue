<template>
  <div>
    <div id="body">
      <div id="left">
        <p>任务名称:</p>
        <el-input v-model="taskname" placeholder="请输入内容"></el-input>
        <el-divider></el-divider>
        <p>任务内容:</p>
        <el-input type="textarea"
                  :rows="3"
                  resize="none"
                  v-model="taskcontent"
                  placeholder="请输入内容" >
        </el-input>
        <el-divider></el-divider>
        <p>学生表现:</p>
        <el-input type="textarea"
                  :rows="4"
                  resize="none"
                  v-model="behave"
                  placeholder="请输入内容" >
        </el-input>
      </div>
      <div id="right">
        <p>选择学生:</p>
        <div style="height: 50px;width: 200px;float: right;margin-right: 200px">
          <el-select v-model="studentid" placeholder="请选择">
            <el-option
                v-for="item in student"
                :key="item.studentid"
                :label="item.name"
                :value="item.studentid">
            </el-option>
          </el-select>
        </div>
        <div style="height: 73px"></div>
        <p>选择图片:</p>
        <div style="height: 200px">
          <el-upload
              class="upload-demo"
              ref="upload"
              action="http://localhost:8281/teachers/addtaskimg"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :file-list="fileList"
              :headers="{'jwt':$store.getters.get_jwt}"
              :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </div>
        <div style="width: 100%;height: 100px;text-align: center">
          <el-button type="primary" @click="allsubmit">点击添加</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {httpGet, httpPost} from "@/api/api";
import router from "@/router";

export default {
name: "addschooltask",
  data(){
    return{
      taskcontent:'',
      taskname:'',
      behave:'',
      student:[],
      studentid:'',
      fileList:[],
    }
  },
  methods:{
    getstudent:function (){
      var that=this
      httpGet({
        url:'/teachers/findstu',
        params:{}
      }).then(res=>{
        that.student=res.data.student
      }).catch(err=>{
        console.log(err)
      })
    },
    allsubmit:function (){
      if(this.taskname==''||this.taskcontent==''||this.studentid==''||this.behave==''){
        this.$message('消息不可为空')
      }else{
        var that=this
        httpPost({
          url:'/teachers/addtask',
          params:{
            studentid:this.studentid,
            taskname:this.taskname,
            taskcontent:this.taskcontent,
            taskbehave:this.behave,
          }
        }).then(res=>{
          if(res.errorCode==0){
            this.submitUpload()
            that.$message('添加成功')
            router.push("/schtask")
          }else {
            that.$message('添加失败')
          }
        }).catch(err=>{
          console.log(err)
        })
      }
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    submitUpload() {
      this.$refs.upload.submit();
    },

  },
  created() {
    this.getstudent()
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
  width: 40%;
  margin-left: 3%;
  /*background-color: red;*/
  float: left;
  margin-top: 10px;
}
#right{
  height: 400px;
  width: 50%;
  margin-right: 3%;
  /*background-color: red;*/
  float: right;
  margin-top: 10px;
}

</style>
