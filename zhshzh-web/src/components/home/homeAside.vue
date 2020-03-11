<template>
  <div>
    <el-menu
      :default-active="$route.path"
      :default-openeds="['/daily']"
      :unique-opened=true
      :collapse="isCollapse"
      :collapse-transition=false
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      router
    >
      <template v-for="menu in menus">
        <template v-if="menu.children.length > 0">
          <el-submenu :index="menu.menuRoute" :key="menu.menuRoute">
            <template slot="title">
              <i :class="menu.menuIcon"></i>
              <span slot="title">{{menu.menuName}}</span>
            </template>
            <el-menu-item-group>
              <el-menu-item v-for="item in menu.children" :index="item.menuRoute" :key="item.route">{{item.menuName}}</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :index="menu.menuRoute" :key="menu.menuRoute">
            <i :class="menu.menuIcon"></i>
            <span slot="title">{{menu.menuName}}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
import {getMenuInfos} from '@/http/api'

export default {
  name: 'homeAside',
  data () {
    return {
      menus: []
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
  computed: {
    options () {
      return this.$store.state.options
    }
  },
  mounted () {
    // 将tabs数组清空
    this.$store.state.options = []
    // 刷新时以当前路由做为tab加入tabs
    if (this.$route.path === '/homePage') {
      // 如果刷新首页，只有首页一个tab
      this.$store.commit('addTabs', {title: '首页', name: 'homePage'})
      this.$store.commit('setActiveIndex', this.$route.name)
    } else {
      // 如果刷新的是非首页，tabs中第一个是首页，第二个是要刷新的页面，且选中第二个tab
      this.$store.commit('addTabs', {title: '首页', name: 'homePage'})
      this.$store.commit('addTabs', {title: this.$route.meta.title, name: this.$route.name})
      this.$store.commit('setActiveIndex', this.$route.name)
    }
    this.getMenuInfos()
  },
  methods: {
    getMenuInfos: function () {
      getMenuInfos().then(response => {
        if (response.code === 10000) {
          this.menus = response.data
          console.log(response.data)
        }
      })
    }
  }
}
</script>

<style scoped>
.el-menu {
  border-right-width: 0;
}
</style>
