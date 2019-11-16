import Vue from 'vue'
// 引入element-ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import App from './App'
import routers from '@/router/routers'

Vue.config.productionTip = false
Vue.use(ElementUI)

const vue = new Vue({
  router: routers,
  el: '#app',
  render: h => h(App)
})
Vue.use({
  vue
})
