<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-row type="flex" justify="center">
        <el-col :span="5">
          <el-form-item label="用户名">
              <el-input v-model="form.username" prefix-icon="el-icon-user" placeholder="请输入用户名" clearable></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :span="5">
          <el-form-item label="密码">
              <el-input v-model="form.password" prefix-icon="el-icon-lock" placeholder="请输入密码" show-password></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
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
        }
      }
    },
    methods: {
      handleChange(value) {
        console.log(value);
      },
      onSubmit() {
        console.log('submit!');
      }
    }
  }
</script>

<style scoped="scoped">
  /* .el-row {
    margin-bottom: 20px;
  } */
</style>
