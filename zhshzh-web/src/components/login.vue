<template>
  <div class="div-background">
    <div class="div-form">
      <el-form ref="form" :model="form">
        <el-row justify="center">
          <el-col :span="16" :offset="4">
            <h1>中晟日志系统</h1>
          </el-col>
        </el-row>
        <el-row type="flex">
          <el-col :span="4" :offset="2" class="span-style">
            <span>用户名</span>
          </el-col>
          <el-col :span="16">
            <el-form-item>
              <el-input v-model="form.username" prefix-icon="el-icon-user" placeholder="请输入用户名" clearable />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex">
          <el-col :span="4" :offset="2" class="span-style">
            <span>密码</span>
          </el-col>
          <el-col :span="16">
            <el-form-item>
              <el-input v-model="form.password" prefix-icon="el-icon-lock" placeholder="请输入密码" show-password />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row justify="center" style="margin-top: 3%">
          <el-col :span="2" :offset="6">
            <el-checkbox v-model="autoLogin" @change="isAutoLogin">
              <span class="rememberMe">自动登录</span>
            </el-checkbox>
          </el-col>
          <el-col :span="2" :offset="6">
            <el-checkbox v-model="form.rememberMe" @change="isRememberMe">
              <span class="rememberMe">记住密码</span>
            </el-checkbox>
          </el-col>
        </el-row>
        <el-row justify="center" style="margin-top: 5%;">
          <el-col :span="4" :offset="10">
            <el-button type="primary" @click="login">登&nbsp;&nbsp;录</el-button>
          </el-col>
        </el-row>
      </el-form>
    </div>
  </div>
</template>

<script>
import {login} from '@/http/api'
import {saveUserIdentity, getUserIdentity, removeUserIdentity} from '@/util/authenticationUtil'

export default {
  name: 'Login',
  data () {
    return {
      form: {
        username: '',
        password: '',
        rememberMe: false
      },
      autoLogin: false
    }
  },
  mounted () {
    // 获取localStorage中的username和password
    let userIdentity = getUserIdentity()
    let username = userIdentity.username
    let password = userIdentity.password
    // 如果存在的话，则自动填充（忽略浏览器自带的自动填充功能）
    if (username && password) {
      this.form.username = username
      this.form.password = password
      this.form.rememberMe = true
    }
    // 回到登录页时，将tabs数组清空
    this.$store.state.options = []
  },
  methods: {
    /**
     * 登录
     */
    login: function () {
      login({
        username: this.form.username,
        password: this.form.password,
        rememberMe: this.form.rememberMe
      }).then(response => {
        if (response.code === 10000) {
          if (this.form.rememberMe) {
            // 如果勾选了记住密码，则将加密后的用户名和密码存入localStorage中
            saveUserIdentity(this.form.username, this.form.password)
          } else {
            // 如果没勾选记住密码，则将localStorage中的用户名和密码删除
            removeUserIdentity()
          }
          // 如果登录成功，则跳转到首页
          this.$router.push({
            name: 'home'
          })
        }
      }).catch(error => {
        console.log(error)
      })
    },
    /**
     * 勾选自动登录的前提是记住密码
     */
    isAutoLogin: function () {
      if (this.autoLogin) {
        this.form.rememberMe = true
      }
    },
    /**
     * 不记住密码时，取消自动登录
     */
    isRememberMe: function () {
      if (!this.form.rememberMe) {
        this.autoLogin = false
      }
    }
  }
}
</script>

<style scoped>
  .div-background {
    width: 100%;
    height: 100%;
    background: url('../assets/images/background.jpg') no-repeat center center fixed;
    background-size: cover;
  }
  .div-form {
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    width: 30%;
    height: 50%;
    background-color: rgba(50, 50, 50, 0.7);
    position: absolute;
    top: 25%;
    left: 35%;
  }
  .div-form h1 {
    color: rgba(27, 136, 195, 0.8);
    margin-top: 10%;
    margin-bottom: 10%;
    text-align: center;
  }
  .span-style {
    color: beige;
    font-size: 20px;
    padding-top: 7px;
  }
  .rememberMe {
    font-size: 16px;
    color: azure;
  }
</style>
