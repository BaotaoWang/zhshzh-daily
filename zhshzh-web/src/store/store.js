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
    options: [],
    activeIndex: 'homePage'
  },
  mutations: {
    // 添加tabs
    addTabs (state, data) {
      this.state.options.push(data)
    },
    // 设置当前激活的tab
    setActiveIndex (state, index) {
      this.state.activeIndex = index
    }
  }
})

// 导出store
export default store
