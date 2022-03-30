<template>
<div style="width: 100%;height: 80px;background-color: #EBEEF5">
  <div style="width: 80px;height: 80px;margin-left: 100px;float: left">
    <img src="../../../public/picture/logo.png" alt="" style="width: 80px;height: 80px">
  </div>
<!--    <el-button type="primary" style="float: left;height: 40px;margin-top: 20px;margin-left: 150px"  @click="dialogVisible = true">图片转文字<i class="el-icon-upload el-icon&#45;&#45;right"></i></el-button>-->

  <div style="width: 250px;height: 80px;margin-left:280px;float: left;color: #202326">
    <h2 >学生家庭学校关联系统</h2>
  </div>
  <div id="touxiang" style="width: 80px;height: 80px;margin-right: 200px;float: right;">
    <img  v-bind:src="imgpath"  style="width: 75px;height: 75px;border-radius:50%">
<!--    <img src="../../../public/picture/manager.png" alt="">-->
  </div>
  <el-dialog
      title="上传需要转文字的图片"
      :visible.sync="dialogVisible"
      width="50%">
    <div style="height: 300px">
      <orc></orc>
    </div>
    <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
  </el-dialog>
</div>
</template>

<script>
import orc from '@/views/common/orc'
import store from "@/store"
import {httpGet} from "@/api/api";
import {readimg} from "@/file";
export default {
name: "top",
  components:{orc},
  data(){
  return{
    imgpath:'',
    dialogVisible: false,
  }
  },
  methods:{
  getimg(){
    var that=this;
    var path='';
    if(store.getters.get_identity==2){
      path='parents/findimg'
    }else if(store.getters.get_identity==1){
      path='teachers/findimg'
    }else if(store.getters.get_identity==3){
      // this.imgpath='../../../public/picture/manager.png'
      console.log('管理员')
      document.getElementById('touxiang').style.display="none"
      return 0
    }
    httpGet({
      url:path,
      params:{
      }
    }).then(res=>{
      console.log(res)
      that.imgpath=readimg(res.data)
    }).catch(err=>{
      console.log(err)
    })
  }
  },
  created() {
  this.getimg()
  },
  mounted() {
  if (store.getters.get_identity==3){
    document.getElementById('touxiang').style.display="none"
  }
  }
}
</script>

<style scoped>

</style>
