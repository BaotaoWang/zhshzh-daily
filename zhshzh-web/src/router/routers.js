import VueRouter from 'vue-router'
import Vue from 'vue'

// Vue全局使用Router
Vue.use(VueRouter)

// 定义 (路由) 组件。
const Login = () => import('@/components/login')
const Home = () => import('@/components/home/home')

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
      component: Home
    }
  ],
  mode: 'history'
})
