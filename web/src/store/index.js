import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import tagsView from './modules/tagsView'
import user from './modules/user'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    tagsView,
    user
  },
  getters: {
    status: state => state.user.status,
    user: state => state.user.user,
    token: state => state.user.token,
    roles: state => state.user.roles,
    menus: state => state.user.menus,

    sidebar: state => state.app.sidebar,
    visitedViews: state => state.tagsView.visitedViews,
    cachedViews: state => state.tagsView.cachedViews
  }
})

export default store
