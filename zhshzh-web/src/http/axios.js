import axios from 'axios'
import {Message} from 'element-ui'
import {saveToken, getToken} from '@/util/authenticationUtil'

// 环境切换时，配置请求根地址
// 代理配置，请参考config/index.js
if (process.env.NODE_ENV === 'development') {
  axios.defaults.baseURL = '/development'
} else if (process.env.NODE_ENV === 'debug') {
  axios.defaults.baseURL = '/debug'
} else if (process.env.NODE_ENV === 'production') {
  axios.defaults.baseURL = '/production'
}
// 设置请求超时时间(ms)
axios.defaults.timeout = 10000
// post请求的时候，加上请求头
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'

// 请求拦截器
axios.interceptors.request.use(
  config => {
    // 每次发送请求之前判断sessionStorage中是否存在token
    // 如果存在，则统一在http请求的header都加上token，这样后台根据token判断你的登录情况
    let token = getToken()
    if (token) {
      config.headers.Authorization = token
    }
    return config
  },
  error => {
    return Promise.error(error)
  })

// 响应拦截器
axios.interceptors.response.use(
  response => {
    // 如果返回的状态码为200，说明接口请求成功，可以正常拿到数据
    // 否则的话抛出错误
    if (response.status === 200) {
      // 请求成功后，如果header中有token，则将其保存在sessionStorage中
      let token = response.headers.token
      if (token) {
        saveToken(token)
      }
      return Promise.resolve(response)
    } else {
      return Promise.reject(response)
    }
  },
  // 服务器状态码不是2开头的的情况
  error => {
    if (error.response.status) {
      switch (error.response.status) {
        // 401: 未登录
        // 未登录则跳转登录页面，并携带当前页面的路径
        // 在登录成功后返回当前页面，这一步需要在登录页操作
        case 401:
          this.$route.replace({
            path: '/login',
            query: {
              redirect: this.$route.currentRoute.fullPath
            }
          })
          break

        // 403 token过期
        // 登录过期对用户进行提示
        // 清除本地token和清空vuex中token对象
        // 跳转登录页面
        case 403:
          /* Toast({
            message: '登录过期，请重新登录',
            duration: 1000,
            forbidClick: true
          }) */
          this.$store.commit('loginSuccess', null)
          // 跳转登录页面，并将要浏览的页面fullPath传过去，登录成功后跳转需要访问的页面
          setTimeout(() => {
            this.$route.replace({
              path: '/login',
              query: {
                redirect: this.$route.currentRoute.fullPath
              }
            })
          }, 1000)
          break

        // 404请求不存在
        case 404:
          /* Toast({
            message: '网络请求不存在',
            duration: 1500,
            forbidClick: true
          }) */
          break
        // 网关超时
        case 504:
          Message({
            showClose: true,
            message: '服务器异常，请求超时',
            type: 'error'
          })
          break
        // 其他错误，直接抛出错误提示
        default:
      }
      return Promise.reject(error.response)
    }
  }
)

/**
 * get方法，对应get请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export const get = (url, params) => {
  return new Promise((resolve, reject) => {
    axios.get(url, {
      params: params
    }).then(response => {
      resolve(response.data)
    }).catch(error => {
      reject(error.data)
    })
  })
}

/**
 * post方法，对应post请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export const post = (url, params) => {
  return new Promise((resolve, reject) => {
    // axios.post(url, QS.stringify(params))
    axios.post(url, params)
      .then(response => {
        resolve(response.data)
      })
      .catch(error => {
        reject(error.data)
      })
  })
}
