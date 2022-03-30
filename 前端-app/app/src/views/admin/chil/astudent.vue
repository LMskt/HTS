<template>
  <div>
    <div id="body">
      <el-card class="box-card">
        <div  class="text item">
          <div id="body2">
            <el-table
                style="width: 100%;height: 380px"
                :data="students.slice((currentPage-1)*pagesize,currentPage*pagesize)"
            >
              <el-table-column
                  prop="name"
                  label="学生姓名"
                  sortable
                  width="120">
              </el-table-column>
              <el-table-column
                  prop="studentid"
                  label="学生学号"
                  sortable
                  width="150">
              </el-table-column>
              <el-table-column
                  prop="sex"
                  label="性别"
                  sortable
                  width="100">
              </el-table-column>
              <el-table-column
                  prop="age"
                  label="年龄"
                  sortable
                  width="100">
              </el-table-column>
              <el-table-column
                  prop="grade"
                  label="年级"
                  sortable
                  width="100">
              </el-table-column>
              <el-table-column
                  prop="phoneNumber"
                  label="联系方式"
                  sortable
                  width="170">
              </el-table-column>
              <el-table-column
                  prop="teacherid"
                  label="负责教师工号"
                  sortable
                  width="150">
              </el-table-column>
              <el-table-column label="删除学生">
                <template slot-scope="scope">
                  <el-button type="danger" icon="el-icon-delete" circle @click="open(scope.row.studentid)"></el-button>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[5]"
                :page-size="pagesize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="students.length">
            </el-pagination>
          </div>
        </div>
      </el-card>
      <el-dialog
          :visible.sync="dialogVisible"
          width="50%">
        <div style="height: 200px;width: 200px;margin-left: auto;margin-right: auto">
          <img  v-bind:src=imgurl style="height:100%;width: 100%;border-radius:50% " >
        </div>
        <div style="height: 20px"></div>
        <div style="width: 100%;text-align: center;font-size: 20px">
          家庭地址:{{localurl}}
        </div>
        <span slot="footer" class="dialog-footer">
  </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import {httpGet} from "@/api/api";
import router from "@/router";
import {readimg} from "@/file";

export default {
name: "astudent",
  data(){
    return{
      students:[],
      currentPage:1,
      pagesize:5,
      imgurl:'',
      localurl:'',
      dialogVisible: false
    }
  },
  methods:{
    handleSizeChange: function (size) {
      this.pagesize = size;
      console.log(this.pagesize)  //每页下拉显示数据
    },
    handleCurrentChange: function(currentPage){
      this.currentPage = currentPage;
      console.log(this.currentPage)  //点击第几页
    },
    getdata:function (){
      var that=this
      httpGet({
        url:'/admin/findstu',
        params:{}
      }).then(res=>{
        that.students=res.data.student
        console.log(res)
      }).catch(err=>{
        console.log(err)
      })
    },
    change:function (imgurl,localurl){
      // console.log(imgurl,localurl)
      this.imgurl=readimg(imgurl)
      this.localurl=localurl
      this.dialogVisible=true

    },
    deleteship:function (studentid){

      httpGet({
        url:'/admin/deletestu',
        params:{
          studentid:studentid
        }
      }).then(res=>{
        if(res.errorCode==0){
          this.open1()
          var giao=setTimeout(" window.location.reload();",500)
        }
      }).catch(err=>{
        console.log(err)
      })
    },
    open(id) {
      this.$confirm('此操作将永久删除该学生 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteship(id)
      }).catch(() => {
        this.open3()
      });
    },
    open1() {
      this.$notify({
        title: '成功',
        message: '删除成功',
        type: 'success'
      });},
    open3() {
      this.$notify.info({
        title: '消息',
        message: '您已取消删除'
      });
    },
  },
  created() {
    this.getdata()
  }
}
</script>

<style scoped>
#body{
  width: 1000px;
  height:500px ;
  margin-right: auto;
  margin-left: auto;
  margin-top: 50px;
}
#body2{
  width: 1000px;
  height:500px ;
  overflow-y: auto;
  overflow-x: hidden;

}
.box-card {
  height: 500px;
  width: 1000px;
}
.item {
  padding: 18px 0;
}
</style>
