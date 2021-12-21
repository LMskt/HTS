<template>
<div>
  <div id="body">
    <div style="height: 20px"></div>
    <div style="width: 80%;height: 70%;margin-right: auto;margin-left: auto">
      <p>公告名称:</p>
      <el-input v-model="noticename" placeholder="请输入内容"></el-input>
      <el-divider></el-divider>
      <p>公告内容:</p>
      <el-input type="textarea"
                :rows="4"
                resize="none"
                v-model="noticecontent"
                placeholder="请输入内容" >
      </el-input>
      <el-divider></el-divider>
    </div>
    <div style="width: 100%;text-align: center">  <el-button type="info" @click="submit">点击发布</el-button></div>
  </div>
</div>
</template>

<script>
import {httpGet, httpPost} from "@/api/api";
import router from "@/router";

export default {
  name: "addnotice",
  data(){
    return{
      noticename:'',
      noticecontent:''
    }
  },
  methods:{
    submit:function (){
      if(this.noticename==''||this.noticecontent==''){
        this.$message('不可为空')
      }else {
        var that=this
        httpPost({
          url:'/teachers/addnotice',
          params:{
           noticetitle:that.noticename,
            noticecontent:that.noticecontent
          }
        }).then(res=>{
          if(res.errorCode==0){
            that.$message('发布成功')
            router.push("/teanotice")
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
  width: 500px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 25px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
</style>
