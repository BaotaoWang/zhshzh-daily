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
          <el-submenu :index="menu.route" :key="menu.route">
            <template slot="title">
              <i :class="menu.icon"></i>
              <span slot="title">{{menu.name}}</span>
            </template>
            <el-menu-item-group>
              <el-menu-item v-for="item in menu.children" :index="item.route" :key="item.route">{{item.name}}</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :index="menu.route" :key="menu.route">
            <i :class="menu.icon"></i>
            <span slot="title">{{menu.name}}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'homeAside',
  data () {
    return {
      menus: [
        {
          route: '/homePage',
          name: '首页',
          icon: 'el-icon-s-home',
          children: []
        },
        {
          route: '/project',
          name: '项目管理',
          icon: 'el-icon-set-up',
          children: [
            {
              route: '/projectList',
              name: '项目列表'
            }
          ]
        },
        {
          route: '/daily',
          name: '日志管理',
          icon: 'el-icon-document',
          children: [
            {
              route: '/addWorkPlan',
              name: '周工作计划'
            },
            {
              route: '/addWorkLog',
              name: '工作日志'
            },
            {
              route: '/workPanList',
              name: '周工作计划查询'
            },
            {
              route: '/workLogList',
              name: '工作日志查询'
            }
          ]
        },
        {
          route: '/application',
          name: '应用管理',
          icon: 'el-icon-menu',
          children: [
            {
              route: '/note',
              name: '便签'
            },
            {
              route: '/blog',
              name: '博客'
            },
            {
              route: '/subject',
              name: '题库'
            }
          ]
        },
        {
          route: '/message',
          name: '消息管理',
          icon: 'el-icon-message',
          children: [
            {
              route: '/comment',
              name: '评论'
            },
            {
              route: '/reply',
              name: '回复'
            },
            {
              route: '/remind',
              name: '提醒'
            }
          ]
        },
        {
          route: '/settings',
          name: '系统设置',
          icon: 'el-icon-setting',
          children: [
            {
              route: '/organization',
              name: '组织管理'
            },
            {
              route: '/role',
              name: '角色管理'
            },
            {
              route: '/authority',
              name: '权限管理'
            },
            {
              route: '/user',
              name: '人员管理'
            },
            {
              route: '/params',
              name: '参数管理'
            },
            {
              route: '/audit',
              name: '审计管理'
            },
            {
              route: '/holidays',
              name: '假期管理'
            }
          ]
        },
        {
          route: '/suggestion',
          name: '反馈建议',
          icon: 'el-icon-chat-line-round',
          children: []
        }
      ]
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
  },
  methods: {
  }
}
</script>

<style scoped>
.el-menu {
  border-right-width: 0px;
}
</style>
