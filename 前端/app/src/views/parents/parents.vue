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
                    <i class="el-icon-location"></i>
                    <span>学生信息</span>
                  </template>
                  <el-menu-item-group>
                    <el-menu-item index="/parents/relationship">负责学生</el-menu-item>
                    <el-menu-item index="/parents/addstudent">添加学生</el-menu-item>
                  </el-menu-item-group>
                </el-submenu>
                <el-submenu index="2">
                  <template slot="title">
                    <i class="el-icon-menu"></i>
                    <span>家庭任务</span>
                  </template>
                  <el-menu-item-group>
                    <el-menu-item index="/parents/mytask">查看已发布</el-menu-item>
                    <el-menu-item index="/parents/addhometask">发布新任务</el-menu-item>
                  </el-menu-item-group>
                </el-submenu>
                <el-menu-item index="/parents/parnotice" >
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
  components:{top}
}
</script>

<style scoped>
</style>
