<template>
  <div>
    <el-menu
      default-active="1-1"
      @open="handleOpen"
      @close="handleClose"
      @select="handleSelect"
      :unique-opened=true
      :collapse="isCollapse"
      :collapse-transition=false>
      <el-menu-item index="0">
        <i class="el-icon-s-home"></i>
        <span slot="title">首页</span>
      </el-menu-item>
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-document"></i>
          <span slot="title">日志管理</span>
        </template>
        <el-menu-item-group>
          <span slot="title">录入</span>
          <el-menu-item index="1-1">周工作计划</el-menu-item>
          <el-menu-item index="1-2">工作日志</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="查询">
          <el-menu-item index="1-3">周工作计划查询</el-menu-item>
          <el-menu-item index="1-4">工作日志查询</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span slot="title">工作台</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="2-1">便签</el-menu-item>
          <el-menu-item index="2-2">题库</el-menu-item>
          <el-menu-item index="2-2">博客</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-message"></i>
          <span slot="title">消息中心</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="3-1">评论</el-menu-item>
          <el-menu-item index="3-2">回复</el-menu-item>
          <el-menu-item index="3-2">提醒</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="4">
        <template slot="title">
          <i class="el-icon-setting"></i>
          <span slot="title">系统设置</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="4-1">组织管理</el-menu-item>
          <el-menu-item index="4-2">角色管理</el-menu-item>
          <el-menu-item index="4-3">权限管理</el-menu-item>
          <el-menu-item index="4-4">人员管理</el-menu-item>
          <el-menu-item index="4-5">参数管理</el-menu-item>
          <el-menu-item index="4-6">审计管理</el-menu-item>
          <el-menu-item index="4-7">假期管理</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="5">
        <i class="el-icon-chat-line-round"></i>
        <span slot="title">反馈建议</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'homeAside',
  data () {
    return {
    }
  },
  props: {
    isCollapse: {
      type: Boolean,
      default: function () {
        return false
      }
    }
  },
  methods: {
    handleOpen: function () {
      console.log('handleOpen')
    },
    handleClose: function () {
      console.log('handleClose')
    },
    handleSelect: function (index) {
      console.log('handleSelect===' + index)
    }
  }
}
</script>

<style scoped>

</style>
