<template>
  <el-container class="outer-container">
    <el-header class="header-style" style="height: 50px;padding: 0;">
      <home-header />
    </el-header>
    <el-container>
      <el-aside :style="{width:(isCollapse?'64px':'200px')}" class="aside-style">
        <div :class="isCollapse?'folded-div':'unfolded-div'" @click="handleCollapse">
          <div v-if="isCollapse" class="el-icon-s-unfold" style="font-size: 20px;"></div>
          <div v-else class="el-icon-s-fold" style="font-size: 20px;"></div>
        </div>
        <div class="home-aside-style">
          <home-aside :isCollapse="isCollapse" />
        </div>
      </el-aside>
      <el-main>
        <home-main ref="homeMain" />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import HomeHeader from './homeHeader'
import HomeAside from './homeAside'
import HomeMain from './homeMain'

export default {
  name: 'home',
  data () {
    return {
      isCollapse: false
    }
  },
  components: {
    'home-header': HomeHeader,
    'home-aside': HomeAside,
    'home-main': HomeMain
  },
  methods: {
    handleCollapse: function () {
      this.isCollapse = !this.isCollapse
    }
  }
}
</script>

<style scoped>
.outer-container {
  height: 100%;
  width: 100%;
}
.aside-style{
  background-color: #545c64;
}
.home-aside-style {
  display: block;
  position: absolute;
  top: 72px;
  width: 200px;
  bottom: 0;
  overflow-y: auto;
  overflow-x: hidden;
}
.home-aside-style::-webkit-scrollbar {
  display: none;
}
.folded-div {
  padding-left: 22px;
  cursor: pointer;
}
.unfolded-div {
  margin-right: 0px;
  cursor: pointer;
  text-align: right;
}
.el-main {
  padding: 0px;
}
</style>
