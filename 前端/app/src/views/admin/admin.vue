<template>
  <div>
    <div id="top">
      <top></top>
    </div>
    <div style="height: 600px">
      <div style="height: 100%;width: 100%">
        <el-container style="height: 100%;width: 100%">
          <el-aside width="200px" style="height: 100%">
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
                  <el-menu-item index="/admin/astudent">已有学生</el-menu-item>
                  <el-menu-item index="/admin/addstudenta">添加学生</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              <el-submenu index="2">
                <template slot="title">
                  <i class="el-icon-menu"></i>
                  <span>教师信息</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/admin/ateacher">已有教师</el-menu-item>
                  <el-menu-item index="/admin/addteachera">添加教师</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              <el-submenu index="3">
                <template slot="title">
                  <i class="el-icon-menu"></i>
                  <span>家长信息</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/admin/aparent">已有家长</el-menu-item>
                  <el-menu-item index="/admin/addparenta">添加家长</el-menu-item>
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
  components:{top}
}
</script>

<style scoped>

</style>
