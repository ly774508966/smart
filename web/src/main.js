// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import 'normalize.css/normalize.css' // A modern alternative to CSS resets
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/styles/index.scss' // global css
import 'font-awesome/css/font-awesome.css'
import App from './App'
import router from './router'
import store from './store'
import './mock' // 数据Mock
import './permission' //权限控制
import 'babel-polyfill'//IE白屏问题
import echarts from 'echarts'//报表插件

Vue.use(ElementUI)

Vue.config.productionTip = false//阻止vue在启动时生成生产提示
Vue.prototype.$echarts = echarts

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
})
