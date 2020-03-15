<template>
  <div>
    <div>
      <el-tabs v-model="activeIndex" type="card" @tab-click="tabClick" v-if="options.length" @tab-remove="tabRemove">
        <el-tab-pane :key="item.title" v-for="item in options" :label="item.title" :name="item.name" :closable="item.name !== 'homePage'">
        </el-tab-pane>
      </el-tabs>
    </div>
    <div class="router-view" :style="{height: viewHeight}">
      <keep-alive :include="keepAliveList">
        <router-view />
      </keep-alive>
    </div>
  </div>
</template>

<script>
export default {
  name: 'homeMain',
  data () {
    return {
      viewHeight: '',
      keepAliveList: this.$store.state.keepAliveList
    }
  },
  created () {
    // 根据屏幕分辨率动态修改主页面的高度
    // 头部高度54px，tabs高度41px，所以主页面高度=窗口高度-95
    this.viewHeight = window.innerHeight - 95 + 'px'
  },
  methods: {
    // tab切换时，动态的切换路由
    tabClick (tab) {
      let path = this.activeIndex
      this.$router.push({ path: path })
    },
    tabRemove (targetName) {
      let index = 0
      let options = this.$store.state.options
      // 遍历打开的选项卡的数组，计算要删除的选项卡在数组中的下标
      for (let option of options) {
        if (option.name === targetName) {
          break
        }
        index++
      }
      // 从options数组中移除关闭的选项卡
      options.splice(index, 1)
      // 从keepAliveList数组中移除关闭的选项卡
      // 如果不移除，该组件生命周期不会结束，下次再打开，页面不会刷新
      this.$store.state.keepAliveList.splice(index, 1)
      // 如果删除的是当前激活的选项卡
      if (this.activeIndex === targetName) {
        // 设置当前激活的路由
        if (options.length === index) {
          // 如果删除的是最后一个选项卡
          this.$router.push({ path: options[index - 1].name })
        } else {
          this.$router.push({ name: options[index].name })
        }
      }
    }
  },
  computed: {
    options () {
      return this.$store.state.options
    },
    activeIndex: {
      get () {
        return this.$store.state.activeIndex
      },
      set (val) {
        this.$store.commit('setActiveIndex', val)
      }
    }
  },
  watch: {
    // 监听路由变化，动态修改tabs
    $route (to) {
      let flag = false
      // 当点击左侧导航栏时，遍历已打开的tabs时，如果点击的菜单已打开，则切换到该选项卡
      for (let option of this.options) {
        if (option.name === to.name) {
          flag = true
          this.$store.commit('setActiveIndex', this.$route.name)
          break
        }
      }
      // 如果点击的菜单未打开，则添加到选项卡中，并切换到该选项卡
      if (!flag) {
        this.$store.commit('addTabs', {title: this.$route.meta.title, name: this.$route.name})
        this.$store.commit('setActiveIndex', this.$route.name)
      }
    }
  }
}
</script>

<style scoped>
.router-view {
  margin: 0 20px;
  padding-right: 5px;
  overflow-y: auto;
  overflow-x: hidden;
}

.router-view::-webkit-scrollbar
{
  width: 3px;
}

.router-view::-webkit-scrollbar-thumb
{
  border-radius: 2px;
  -webkit-box-shadow: inset 0 0 6px rgba(150,150,150,0.5);
}
</style>
