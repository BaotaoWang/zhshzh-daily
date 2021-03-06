import VueRouter from 'vue-router'
import Vue from 'vue'

// Vue全局使用Router
Vue.use(VueRouter)

// 定义 (路由) 组件。
const Login = () => import('@/components/login')
const Home = () => import('@/components/home/home')
const TestHomePage = () => import('@/components/home/testHomePage')
const TestWorkPLan = () => import('@/components/home/testWorkPlan')
const TestWorkLog = () => import('@/components/home/testWorkLog')
const Menus = () => import('@/components/menu/menus')
const Generator = () => import('@/components/developer/generator')
const Suggestion = () => import('@/components/suggestion/suggestion')

// 创建 router 实例
export default new VueRouter({
  routes: [
    {
      path: '/',
      name: 'login',
      redirect: '/login'
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/home',
      name: 'home',
      component: Home,
      redirect: 'homePage',
      children: [
        {
          path: '/homePage',
          name: 'homePage',
          component: TestHomePage,
          meta: {
            title: '首页'
          }
        },
        {
          path: '/addWorkPLan',
          name: 'addWorkPlan',
          component: TestWorkPLan,
          meta: {
            title: '周工作计划'
          }
        },
        {
          path: '/addWorkLog',
          name: 'addWorkLog',
          component: TestWorkLog,
          meta: {
            title: '工作日志'
          }
        },
        {
          path: '/menus',
          name: 'menus',
          component: Menus,
          meta: {
            title: '菜单管理'
          }
        },
        {
          path: '/generator',
          name: 'generator',
          component: Generator,
          meta: {
            title: '代码生成器'
          }
        },
        {
          path: '/suggestion',
          name: 'suggestion',
          component: Suggestion,
          meta: {
            title: '反馈建议'
          }
        }
      ]
    }
  ],
  mode: 'history'
})
