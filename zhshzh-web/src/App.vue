<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import {Message} from 'element-ui'
import {login} from '@/http/api'
import {getToken, getAutoLoginState, getUserIdentity, saveUserFullName} from '@/util/authenticationUtil'

export default {
  name: 'App',
  created () {
    // 设置全局导航守卫
    this.$router.beforeResolve((to, from, next) => {
      // 每次路由发生变化时进行判断，如果是转到登录页，直接跳转
      if (to.path === '/login') {
        next()
      } else {
        // 如果转到非登录页，判断sessionStorage中是否有token，有的话，直接跳转
        let token = getToken()
        if (token) {
          next()
        } else {
          // sessionStorage中没有token的话，获取localStorage中的自动登录状态，判断是不是自动登录
          let isAutoLogin = getAutoLoginState()
          if (isAutoLogin) {
            // 如果是自动登录，获取localStorage中的用户名和密码，并自动登录
            let userIdentity = getUserIdentity()
            login({
              username: userIdentity.username,
              password: userIdentity.password,
              rememberMe: true
            }).then(response => {
              if (response.code === 10000) {
                // 将用户姓名保存到sessionStorage中
                saveUserFullName(response.data.fullName)
                next()
              } else {
                // 如果自动登录失败，提示失败信息
                Message({
                  showClose: true,
                  message: response.message,
                  type: 'error'
                })
              }
            }).catch(error => {
              console.log(error)
            })
          } else {
            // 如果不是自动登录，则回到登录页
            next('/login')
          }
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
