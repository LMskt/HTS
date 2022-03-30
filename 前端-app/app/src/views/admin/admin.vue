<template>
  <div>
    <div style="height: 600px">
      <div style="height: 100%;width: 100%">
        <div style="height: 100%;width: 40px;position: absolute;" @click="side_to"></div>
        <el-container style="height: 100%;width: 100%">
          <el-aside style="width: 0;transition: width 0.5s;" id="side">
            <el-menu
                default-active="this.$route.path" router
                class="el-menu-vertical-demo"
                background-color="#545c64"
                text-color="#fff"
                style="height: 100%">
              <el-submenu index="1">
                <template slot="title">
                  <i class="el-icon-menu"></i>
                  <span>学生信息</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/astudent">已有学生</el-menu-item>
                  <el-menu-item index="/addstudenta">添加学生</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              <el-submenu index="2">
                <template slot="title">
                  <i class="el-icon-menu"></i>
                  <span>教师信息</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/ateacher">已有教师</el-menu-item>
                  <el-menu-item index="/addteachera">添加教师</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              <el-submenu index="3">
                <template slot="title">
                  <i class="el-icon-menu"></i>
                  <span>家长信息</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/aparent">已有家长</el-menu-item>
                  <el-menu-item index="/addparenta">添加家长</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
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
import top from "@/views/common/top";
export default {
  name: "admin",
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
