<template>
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
                prop="parentname"
                label="家长姓名"
                sortable
                width="180">
            </el-table-column>
            <el-table-column
                prop="time"
                label="发布时间"
                sortable
                width="180">
            </el-table-column>
            <el-table-column label="家庭任务">
              <template slot-scope="scope">
                <el-button icon="el-icon-search" circle @click="detail(scope.row.id)"></el-button>
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
</template>

<script>
import {httpGet} from "@/api/api";
import router from "@/router";

export default {
  name: "teadetail",
  data(){
    return{
      studentid:this.$route.query.studentid,
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
        url:'/teachers/findtaskh',
        params:{
          studentid:that.studentid
        }
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
        path:"/teachertaskhome",
        query:{id:id}
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
