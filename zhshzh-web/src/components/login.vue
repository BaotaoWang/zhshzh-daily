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
              <el-input v-model="form.username" prefix-icon="el-icon-user" placeholder="请输入用户名" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex">
          <el-col :span="4" :offset="2" class="span-style">
            <span>密码</span>
          </el-col>
          <el-col :span="16">
            <el-form-item>
              <el-input v-model="form.password" prefix-icon="el-icon-lock" placeholder="请输入密码" show-password></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row justify="center">
          <el-col :span="4" :offset="10">
            <el-checkbox v-model="form.rememberMe">
              <span class="rememberMe">记住我</span>
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
  export default {
    name: 'Login',
    data() {
      return {
        form: {
          username: '',
          password: '',
          rememberMe: ''
        }
      }
    },
    methods: {
      login: function() {
        this.$router.push({
          name: 'index'
        })
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
