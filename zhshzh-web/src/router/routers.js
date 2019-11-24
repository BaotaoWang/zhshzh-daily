import VueRouter from 'vue-router'
import Vue from 'vue'

// 下面4行代码是为了解决elementui当点击激活的菜单时会在浏览器控制台报红错(NavigationDuplicated)的问题
// 貌似elementui在2.13.0版本会解决该问题
// 2.13.0版本发布后，更新elementui版本，删除以下代码测试不报错的话，就永久性删除
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

// Vue全局使用Router
Vue.use(VueRouter)

// 定义 (路由) 组件。
const Login = () => import('@/components/login')
const Home = () => import('@/components/home/home')
const TestHomePage = () => import('@/components/home/testHomePage')
const TestWorkPLan = () => import('@/components/home/testWorkPlan')
const TestWorkLog = () => import('@/components/home/testWorkLog')
const Suggestion = () => import('@/components/suggestion/suggestion')

// 创建 router 实例
export default new VueRouter({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      component: Login
    },
    { path: '/home',
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
