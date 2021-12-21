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
                width="180">
            </el-table-column>
            <el-table-column
                prop="sex"
                label="学生性别"
                sortable
                width="180">
            </el-table-column>
            <el-table-column
                prop="age"
                label="学生年龄"
                sortable
                width="180">
            </el-table-column>
            <el-table-column
                prop="phoneNumber"
                label="联系方式"
                sortable
                width="180">
            </el-table-column>
            <el-table-column label="家庭任务">
              <template slot-scope="scope">
                <el-button icon="el-icon-search" circle @click="detail(scope.row.studentid)"></el-button>
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
  </div>
</div>
</template>

<script>
import {httpGet} from "@/api/api";
import router from "@/router";

export default {
name: "teastudent",
  data(){
    return{
      students:[],
      currentPage:1,
      pagesize:5,
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
        url:'/teachers/findstu',
        params:{}
      }).then(res=>{
        that.students=res.data.student
        console.log(res)
      }).catch(err=>{
        console.log(err)
      })
    },
    detail:function (studentid){
      router.push({
        path:"/teadetail",
        query:{studentid:studentid}
      })
    }},
  created() {
    this.getdata()
  }
}
</script>

<style scoped>
#body{
  width: 850px;
  height:500px ;
  margin-right: auto;
  margin-left: auto;
  margin-top: 50px;
}
#body2{
  width: 850px;
  height:500px ;
  overflow-y: auto;
  overflow-x: hidden;

}
.box-card {
  height: 500px;
  width: 850px;
}
.item {
  padding: 18px 0;
}
</style>
