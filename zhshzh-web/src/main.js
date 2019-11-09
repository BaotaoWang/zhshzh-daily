// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VueRouter from 'vue-router'

// 引入element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';


import App from './App'
import Home from './components/Home'
import Login from './components/Login'

Vue.config.productionTip = false
Vue.use(VueRouter);
Vue.use(ElementUI);

// 配置路由
const router = new VueRouter({
	routes:[
		{path:"/",component:Login},
		{path:"/login",component:Login},
		{path:"/home",component:Home},
	],
	mode:"history"
})

// 全局注册组件
// Vue.component("users",Users);

/* eslint-disable no-new */
new Vue({
  router,
  el: '#app',
  template: '<App/>',
  components: { App }
})

// index.html -> main.js -> App.vue
