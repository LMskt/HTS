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
              <el-menu-item index="/teachers/teastudent" >
                <i class="el-icon-document"></i>
                <span slot="title">负责学生</span>
              </el-menu-item>
              <el-submenu index="1">
                <template slot="title">
                  <i class="el-icon-location"></i>
                  <span>学校任务</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/teachers/schtask">查看已发布</el-menu-item>
                  <el-menu-item index="/teachers/addschooltask">发布新任务</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              <el-submenu index="2">
                <template slot="title">
                  <i class="el-icon-menu"></i>
                  <span>公告</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/teachers/teanotice">查看已发布</el-menu-item>
                  <el-menu-item index="/teachers/addnotice">发布新公告</el-menu-item>
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
import top from '@/views/common/top'
export default {
  name: "teachers",
  components:{top}
}
</script>

<style scoped>

</style>
