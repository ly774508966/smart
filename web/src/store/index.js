import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import tagsView from './modules/tagsView'
import user from './modules/user'
import menu from './modules/menu'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    tagsView,
    user,
    menu
  },
  getters: {
    status: state => state.user.status,
    user: state => state.user.user,
    menus: state => state.menu.menus,
    roles: state => state.user.roles,
    sidebar: state => state.app.sidebar,


    visitedViews: state => state.tagsView.visitedViews,
    cachedViews: state => state.tagsView.cachedViews
  }
})

export default store
