import VueRouter from "vue-router";
import Vue from 'vue'

Vue.use(VueRouter)  // Vue全局使用Router

// 定义 (路由) 组件。
const Login = () => import('@/components/login')
const Index = () => import('@/components/index')

// 创建 router 实例
export default new VueRouter({
  routes: [
    {
      path: "/",
      component: Login
    },
    {
      path: "/login",
      component: Login
    },
    { path: '/index',
      name: 'index',
      component: Index
    }
  ],
  mode:"history"
})
