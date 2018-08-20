import Vue from 'vue'
import Router from 'vue-router'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import store from '@/store'
import { account_user } from '@/api/account'
import { Message } from 'element-ui'
import Layout from '@/views/layout/Layout'

//菜单转换成路由
function convertMenus (result, menus) {
  menus.subs.forEach((menu) => {
    if (menu.node.type == 1) {
      result.push({
        name: menu.node.code,
        meta: {title: menu.node.name, icon: menu.node.menuIcon},
        path: menu.node.url,
        component: () => import('@/views' + menu.node.url)
      })
    }
    if (menu.subs.length > 0) {
      convertMenus(result, menu)
    }
  })
}

Vue.use(Router)
NProgress.configure({showSpinner: false})// NProgress Configuration

var constantRouterMap = [
  {path: '/login', component: () => import('@/views/login')},
  {path: '/404', component: () => import('@/views/error/404')},
  {path: '/401', component: () => import('@/views/error/401')}
]
const whiteList = ['/login']

const router = new Router({
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})
router.beforeEach((to, from, next) => {
    NProgress.start()

    var token = store.getters.token

    //无令牌
    if (!token) {
      if (whiteList.indexOf(to.path) !== -1) { //免登录页面直接进入
        next()
      } else {//否则跳转到登录页
        next('/login')
      }
    }

    //有令牌
    if (token) {
      if (!store.getters.user) {//第一次请求
        store.dispatch('user').then(menus => {
          var routers = []
          convertMenus(routers, menus)
          //console.log(routers)
          router.addRoutes([{path: '/', component: Layout, children: routers}])
          next({...to, replace: true})
        }).catch(err => {
          console.error(err)
          store.dispatch('front_logout').then(() => {
            Message.error('获取用户基本信息异常，请重新登录')
            next({path: '/'})
          })
        })
      }
      else if (to.path === '/login') {//打开登录页，跳转到首页
        next({path: '/'})
      }
      else {//有权限，继续
        next()
      }
    }
  }
)
router.afterEach(() => {
  NProgress.done()
})

export default router


