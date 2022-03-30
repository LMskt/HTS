<template>
  <div>
    <div id="body">
      <el-card class="box-card">
        <div  class="text item">
          <div id="body2">
            <el-table
                style="width: 100%;height: 380px"
                :data="notices.slice((currentPage-1)*pagesize,currentPage*pagesize)"
            >
              <el-table-column
                  prop="noticeTitle"
                  label="公告名称"
                  sortable
                  width="180">
              </el-table-column>
              <el-table-column
                  prop="teachername"
                  label="教师姓名"
                  sortable
                  width="180">
              </el-table-column>
              <el-table-column
                  prop="time"
                  label="发布时间"
                  sortable
                  width="180">
              </el-table-column>
              <el-table-column label="点击查看">
                <template slot-scope="scope">
                  <el-button icon="el-icon-search" circle @click=giao(scope.row.noticeTitle,scope.row.noticeContent)></el-button>
                </template>
              </el-table-column>
              <el-table-column label="删除公告">
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
                :total="notices.length">
            </el-pagination>
          </div>
          <el-dialog
              :visible.sync="dialogVisible"
              width="280px">
            <div style="height: 300px;width: 250px;margin-left: auto;margin-right: auto">
              <div style="height: 20px;width: 100%;text-align: center">
                <h1>{{title}}</h1>
              </div>
              <div>
                <div id="card2">
                  {{content}}
                </div>
              </div>
            </div>
            <span slot="footer" class="dialog-footer"></span>
          </el-dialog>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import {httpGet} from "@/api/api";

export default {
name: "teanotice",
  data(){
    return{
      notices:[],
      currentPage:1,
      pagesize:5,
      dialogVisible: false,
      title:'',
      content:''
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
        url:'/teachers/findnoticebytea',
        params:{}
      }).then(res=>{
        that.notices=res.data.notice
        console.log(res)
      }).catch(err=>{
        console.log(err)
      })
    },
    giao:function (title,content){
      this.title=title;
      this.content=content;
      this.dialogVisible=true;
    },
    deleteship:function (id){

      httpGet({
        url:'/teachers/deletenoticebyid',
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
      this.$confirm('此操作将永久删除该公告 是否继续?', '提示', {
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
  width: 740px;
  height:500px ;
  margin-right: auto;
  margin-left: auto;
  margin-top: 50px;
}
#body2{
  width: 740px;
  height:500px ;
  overflow-y: auto;
  overflow-x: hidden;

}
#card2{
  height: 200px;
  width: 90%;
  margin-top: 50px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  padding-left: 5%;
  padding-right: 5%;
  padding-top: 5%;
  padding-bottom: 4%;
  font-family: Arial,Helvetica,sans-serif;
  font-size: 15px;
  word-wrap:break-word
}
.box-card {
  height: 500px;
  width: 740px;
}
.item {
  padding: 18px 0;
}
</style>
