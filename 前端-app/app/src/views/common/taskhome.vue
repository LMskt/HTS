<template>
  <div>
    <div id="body">
      <div style="height: 100px;width: 80%;margin-left: auto;margin-right: auto;line-height: 50px;text-align: center">
        <h1>任务名称:{{task.taskname}}</h1>
      </div>
      <div id="left">
        <p class="hang">学生姓名:{{task.studentname}}</p>
        <el-divider></el-divider>
        <p class="hang">家长姓名:{{task.parentname}}</p>
        <el-divider></el-divider>
        <p class="hang">任务内容:</p>
        <div id="card">
          {{task.taskcontent}}
        </div>
        <p class="hang">学生表现:</p>
        <div id="card2">
          {{task.taskbehave}}
        </div>
        <div id="card3">
          <img  v-bind:src=url style="height:100%;width: 100%; " @click="dialogVisible = true">
        </div>
      </div>
    </div>
    <el-dialog
        :visible.sync="dialogVisible"
        width="70%">
      <div style="height: 200px;width: 200px;margin-left: auto;margin-right: auto">
        <img  v-bind:src=url style="height:100%;width: 100%; " >
      </div>
      <span slot="footer" class="dialog-footer">
  </span>
    </el-dialog>
  </div>
</template>

<script>
import {httpGet} from "@/api/api";
import {readimg} from "@/file";

export default {
  name: "taskhome",
  data(){
    return{
      id:this.$route.query.id,
      task:{},
      url:'',
      dialogVisible: false
    }
  },
  methods:{
    getdata:function (){
      var that=this;
      httpGet({
        url:'/parents/findhome',
        params:{
          id:that.id
        }
      }).then(res=>{
        that.task=res.data.task
        that.url=readimg(res.data.task.img)
      }).catch(err=>{
        console.log(err)
      })
    }
  },
  created() {
    this.getdata()
  }
}
</script>

<style scoped>
#body{
  height: 800px;
  width: 280px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 25px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
#left{
  height: 800px;
  width: 280px;
  /*margin-left: 5%;*/
  /*background-color: red;*/
  /*float: left;*/
  margin-top: 10px;
}

.hang{
  /*height: 50px;*/
  /*width: 100%;*/
  font-family: Arial,Helvetica,sans-serif;
  /*line-height: 50px;*/
  font-size: 20px;
  margin-top: auto;
  margin-bottom: auto;
}
#card{
  height: 80px;
  width: 90%;
  /*background-color: red;*/
  margin-top: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  padding-left: 5%;
  padding-right: 5%;
  padding-top: 5%;
  padding-bottom: 4%;
  font-family: Arial,Helvetica,sans-serif;
  font-size: 15px;
  word-wrap:break-word
}
#card2{
  height: 70px;
  width: 90%;
  margin-top: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  padding-left: 5%;
  padding-right: 5%;
  padding-top: 5%;
  padding-bottom: 4%;
  font-family: Arial,Helvetica,sans-serif;
  font-size: 15px;
  word-wrap:break-word
}
#card3{
  height: 100px;
  width: 100px;
  margin-top: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  padding-left: 5%;
  padding-right: 5%;
  padding-top: 5%;
  padding-bottom: 4%;
  margin-right: auto;
  margin-left: auto;
  /*font-family: Arial,Helvetica,sans-serif;*/
  /*font-size: 15px;*/
  /*word-wrap:break-word*/
}

</style>
