<template>
  <el-container class="outer-container">
    <el-header class="header-style" style="height: 50px;padding: 0;">
      <home-header />
    </el-header>
    <el-container>
      <el-aside class="aside-style" :style="{width:(isCollapse?'64px':'220px')}">
        <div :class="isCollapse?'folded-div':'unfolded-div'" @click="handleCollapse">
          <div v-if="isCollapse" class="el-icon-s-unfold" style="font-size: 20px;"></div>
          <div v-else class="el-icon-s-fold" style="font-size: 20px;"></div>
        </div>
        <home-aside :isCollapse="isCollapse"></home-aside>
      </el-aside>
      <el-main>
        <home-main />
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
.aside-style {
  display: block;
  position: absolute;
  left: 0;
  top: 50px;
  bottom: 0;
  overflow-y: auto;
  overflow-x: hidden;
  border-right: solid 1px #e6e6e6;
}
.aside-style::-webkit-scrollbar {
  display: none;
}
.folded-div {
  width: 42px;
  padding-left: 22px;
  cursor: pointer;
  background-color: cornflowerblue;
}
.unfolded-div {
  width: 220px;
  margin-right: 0px;
  cursor: pointer;
  text-align: right;
  background-color: cornflowerblue;
}
</style>
