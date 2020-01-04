<template>
  <div class="header-style">
    <el-row>
      <el-col :span="6">
        <div style="display: block">
          <img src="@/assets/images/logo.png"/>
        </div>
      </el-col>
      <el-col :span="18">
        <div class="div-right">
          <div class="message-style">
            <el-dropdown trigger="click" @command="handleMessageCommand">
              <el-badge is-dot>
                <i class="el-icon-message-solid" style="font-size: 22px;" />
              </el-badge>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="a">评论</el-dropdown-item>
                <el-dropdown-item command="b">回复</el-dropdown-item>
                <el-dropdown-item command="c">提醒</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
          <div class="header-image">
            <el-dropdown trigger="click" @command="handleUserCommand">
              <el-avatar :size="30">
                <img src="@/assets/images/head-image1.jpg"/>
              </el-avatar>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item disabled>系统管理员</el-dropdown-item>
                <el-dropdown-item icon="el-icon-key" command="password">密码管理</el-dropdown-item>
                <el-dropdown-item icon="el-icon-s-custom" command="userInfo">用户管理</el-dropdown-item>
                <el-dropdown-item icon="el-icon-switch-button" command="logout">注销</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import {removeToken} from '@/util/authenticationUtil'

export default {
  name: 'header1',
  methods: {
    handleMessageCommand: function (command) {
      this.$message('click on item ' + command)
    },
    handleUserCommand: function (command) {
      if (command === 'password') {
        // 密码管理
      } else if (command === 'userInfo') {
        // 用户管理
      } else if (command === 'logout') {
        // 注销操作
        // 清除localStorage中存的token
        removeToken()
        // 跳转到登录页
        this.$router.push({
          name: 'login'
        })
      }
    }
  }
}
</script>

<style scoped>
.header-style {
  background-color: #2e2e2e;
  height: 100%;
  width: 100%;
}
.div-right {
  position: absolute;
  width: 100px;
  height: 100%;
  right: 0px;
}
.message-style {
  float: left;
  margin-top: 15px;
  margin-right: 20px;
  cursor: pointer;
}
.header-image {
  float: left;
  margin-top: 10px;
  cursor: pointer;
}
</style>
