<template>
  <div>
    <div id="box">
      <div id="img">
        <img src="../../../../public/picture/boy.png" alt="" style="height: 100px;width: 100px;margin-top: 40px">
      </div>
      <form>
        <div class="input" style="margin-top: 80px;">
          <el-input v-model="username" placeholder="请输入账号"></el-input>
        </div>
        <div class="input" style="margin-top: 12px">
          <el-input placeholder="请输入密码" v-model="password" show-password></el-input>
        </div>
        <div id="select">
          <el-select v-model="value" placeholder="请选择">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </div>
        <div style="height: 20px;width: 94px;margin-left: auto;margin-right: auto;margin-top: 10px">
          <el-button type="primary" @click="login">点击登录</el-button>
<!--          <el-button type="primary" @click="download">asdasd</el-button>-->
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import {host} from "@/api/api"
import axios from "axios";
import store from "@/store"
import router from "@/router";
import {httpfile} from "../../../api/api";
export default {
name: "login",
  data(){
  return{
    username:'',
    password:'',
    options:[
      {
        value: 1,
        label:'学校端'
      },{
        value: 2,
        label: '家庭端'
      },{
        value: 3,
        label: '管理员'
      }
    ],
    value:1
  }
  },
  methods:{
  login:function (){
    var that=this
    var priurl;
    if(this.value==3){
     priurl=host+'admin/login'
    }else if(this.value==1){
      priurl=host+'teachers/login'
    }else if(this.value==2){
      priurl=host+'parents/login'
    }
    axios({
      url:priurl,
      method:"get",
      params:{
        username:this.username,
        password:this.password
      }
    }).then(function (res){
      if(res.data.errorCode==0){
        store.commit('del_all')
        store.commit('set_jwt',res.data.data.jwt)
        store.commit('set_identity',that.value)
        //登录成功,跳转页面
        if (that.value==2){
          router.push('/parents')
        }else if(that.value==1){
          router.push('/teachers')
        }else if(that.value==3){
          router.push('/admin')
        }
      }else{
        that.open('信息错误，请重新输入')
      }
    }).catch(function (err){
      that.open('系统故障，联系管理员')
      console.log(err)
    })
  },
    open(var1) {
      this.$alert(var1, '警告', {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'info',
            message: `action: ${ action }`
          });
        }
      });
    },
    download(){
    httpfile('D:/zhaopian','aaaa.jpg')
    }
  }
}
</script>

<style scoped>
#box{
  height: 450px;
  width: 600px;
  margin-left: auto;
  margin-right: auto;
  background-color: white;
  margin-top: 100px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}

#img{
  width: 100%;
  height: 100px;
  text-align: center;
}

.input{
  height: 60px;
  width: 500px;
  margin-right: auto;
  margin-left: auto;
  /*background-color: red;*/
}
#select{
  height:50px ;
  width: 120px;
  margin-top: 10px;
  margin-left: 50px;

}

form{

}
</style>
