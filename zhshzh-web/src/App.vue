<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
  created () {
    // 设置全局守卫
    // 从localStorage中获取token
    let token = localStorage.getItem('daily-token')
    if (!token) {
      // 如果localStorage中无token，则跳转到登录页
      this.$router.push({ name: 'login' })
    }
    this.$router.beforeEach((to, from, next) => {
      // 每次路由发生变化时进行判断，如果是转到登录页，直接跳转
      if (to.path === '/login') {
        next()
      } else {
        // 如果转到非登录页，判断localStorage中是否有token，有的话，直接跳转，没有的话，跳转到登录页
        let token = localStorage.getItem('daily-token')
        if (token) {
          next()
        } else {
          next('/login')
        }
      }
    })
  }
}
</script>
<style>
  #app {
    height: 100%;
  }
</style>
