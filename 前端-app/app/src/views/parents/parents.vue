<template>
  <div>
    <div style="height: 600px">
        <div style="height: 100%;width: 100%">
          <div style="height: 100%;width: 40px;position: absolute;" @click="side_to"></div>
          <el-container style="height: 100%;width: 100%">
            <el-aside  style="width: 0;transition: width 0.5s;" id="side">
              <el-menu
                  default-active="this.$route.path" router
                  class="el-menu-vertical-demo"
                  background-color="#545c64"
                  text-color="#fff"
                  style="height: 100%">
                <el-submenu index="1">
                  <template slot="title">
                    <i class="el-icon-location"></i>
                    <span>学生信息</span>
                  </template>
                  <el-menu-item-group>
                    <el-menu-item index="/relationship">负责学生</el-menu-item>
                    <el-menu-item index="/addstudent">添加学生</el-menu-item>
                  </el-menu-item-group>
                </el-submenu>
                <el-submenu index="2">
                  <template slot="title">
                    <i class="el-icon-menu"></i>
                    <span>家庭任务</span>
                  </template>
                  <el-menu-item-group>
                    <el-menu-item index="/mytask">查看已发布</el-menu-item>
                    <el-menu-item index="/addhometask">发布新任务</el-menu-item>
                  </el-menu-item-group>
                </el-submenu>
                <el-menu-item index="/parnotice" >
                  <i class="el-icon-document"></i>
                  <span slot="title">查看公告</span>
                </el-menu-item>
              </el-menu>
            </el-aside>
            <el-main>
              <router-view></router-view>
            </el-main>
          </el-container>
        </div>
      </div>
  </div>

</template>

<script>
import top from '@/views/common/top'
export default {
  name: "parent",
  components:{top},
  methods:{
    side_to(){
      document.getElementById("side").style.width=180+"px"
    },
    side_from(){
      document.getElementById("side").style.width=0+"px"
    }
  }
}
</script>

<style scoped>
</style>
