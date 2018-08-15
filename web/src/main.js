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

import './mock' // simulation data

import './permission' // permission control

import 'babel-polyfill'//IE白屏问题

import echarts from 'echarts'

Vue.use(ElementUI)

Vue.config.productionTip = false
Vue.prototype.$echarts = echarts

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
})
