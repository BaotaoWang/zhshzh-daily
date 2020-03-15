import Vuex from 'vuex'
import Vue from 'vue'

// Vue全局使用Vuex
Vue.use(Vuex)

/**
 * Vuex全局状态管理
 * @param options {Array} 用于渲染tabs的数组
 */
const store = new Vuex.Store({
  state: {
    // 打开的tab的数组
    options: [],
    // 要缓存的组件的数组(如果不对缓存进行处理，关闭tab后，再打开这个tab，由于<keep-alive>标签，组件没有销毁，下次再打开，页面不会刷新)
    keepAliveList: [],
    activeIndex: 'homePage'
  },
  mutations: {
    // 添加tabs
    addTabs (state, data) {
      // 将打开的tab标题及组件名存到options中
      this.state.options.push(data)
      // 同时将组件名放到缓存数组keepAliveList中
      this.state.keepAliveList.push(data.name)
    },
    // 设置当前激活的tab
    setActiveIndex (state, index) {
      this.state.activeIndex = index
    }
  }
})

// 导出store
export default store
