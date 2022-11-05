<template>
<div>
  <div id="body">
    <el-card class="box-card">
      <div  class="text item">
        <div id="body2">
          <el-table
              style="width: 100%;height: 380px"
              :data="task.slice((currentPage-1)*pagesize,currentPage*pagesize)"
          >
            <el-table-column
                prop="taskname"
                label="任务名称"
                sortable
                width="180">
            </el-table-column>
            <el-table-column
                prop="studentname"
                label="学生姓名"
                sortable
                width="180">
            </el-table-column>
            <el-table-column
                prop="studentid"
                label="学生学号"
                sortable
                width="180">
            </el-table-column>
            <el-table-column
                prop="time"
                label="发布时间"
                sortable
                width="180">
            </el-table-column>
            <el-table-column label="查看详细">
              <template slot-scope="scope">
                <el-button icon="el-icon-search" circle @click="detail(scope.row.id)"></el-button>
              </template>
            </el-table-column>
            <el-table-column label="删除任务">
              <template slot-scope="scope">
                <el-button type="danger" icon="el-icon-delete" circle @click="open(scope.row.id)"></el-button>
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
              :total="task.length">
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
  name: "mytask",
  data(){
    return{
      task:[],
      currentPage:1,
      pagesize:5
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
        url:'/parents/findpartask',
        params:{}
      }).then(res=>{
        that.task=res.data.task
        // that.relationships=res.data.relationship
        console.log(res)
      }).catch(err=>{
        console.log(err)
      })
    },
    detail:function (id){
      router.push({
        path:"/parents/taskhome",
        query:{id:id}
      })
    },
    deleteship:function (id){

      httpGet({
        url:'/parents/deletetaskbyid',
        params:{
          id:id
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
      this.$confirm('此操作将永久删除该任务 是否继续?', '提示', {
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
    }
  },
  created() {
    this.getdata()
  }
}
</script>

<style scoped>
#body{
  width: 930px;
  height:500px ;
  margin-right: auto;
  margin-left: auto;
  margin-top: 50px;
}
#body2{
  width: 930px;
  height:500px ;
  overflow-y: auto;
  overflow-x: hidden;

}
.box-card {
  height: 500px;
  width: 930px;
}
.item {
  padding: 18px 0;
}
</style>
