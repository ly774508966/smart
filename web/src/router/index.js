import Vue from 'vue'
import Router from 'vue-router'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import {getToken} from '@/utils/auth' // getToken from cookie
import store from '@/store'
import {user} from '@/api/account'
import {Message} from 'element-ui'
import Layout from '@/views/layout/Layout'

Vue.use(Router)


/**
 * hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
 *                                if not set alwaysShow, only more than one route under the children
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']     will control the page roles (you can set multiple roles)
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
    noCache: true                if true ,the page will no be cached(default is false)
  }
 **/

var constantRouterMap = [
  {path: '/login', component: () => import('@/views/login')},
  {path: '/404', component: () => import('@/views/error/404')},
  {path: '/401', component: () => import('@/views/error/401')}
]

//权限转化为菜单
function convertMenus(perms) {
  var result = []
  perms.subs.forEach((item) => {
    var menu = convertMenu(item);
    result.push(menu)
    if (item.subs.length > 0) {
      menu.children = convertMenus(item);
    }
  })
  return result
}

function convertMenu(perm) {
  var node = perm.node;
  var menu = {}
  menu.name = node.code
  menu.meta = {title: node.name, icon: node.menuIcon}
  if (node.isMenuTitle) {
    menu.alwaysShow = true
    menu.redirect = 'noredirect'
    menu.path = node.url
    menu.component = Layout
  } else {
    menu.path = node.url
    menu.component = () => import('@/views' + node.url);
  }
  return menu
}

NProgress.configure({showSpinner: false})// NProgress Configuration
const whiteList = ['/login']
const router = new Router({
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})
router.beforeEach((to, from, next) => {
  NProgress.start()

  var token = getToken();

  //未登录
  if (!token) {
    if (whiteList.indexOf(to.path) !== -1) { //在免登录白名单，直接进入
      next()
    }
    else {//否则跳转到登录页
      next('/login')
    }
  }

  //已登录
  if (token) {
    if (!store.getters.user) {//内存无权限数据
      user().then(res => {
        var menus = [].concat([{
          meta: {title: '首页', icon: 'fa-file-text'},
          path: '/',
          component: Layout,
          redirect: '/home',
          name: 'home',
          children: [{name: 'home-index', meta: {title: '首页'}, path: 'home', component: () => import('@/views/home')}]
        }]).concat(convertMenus(res.data.perms));
        // console.log(menus)
        store.commit("SET_USER", res.data.user)
        store.commit("SET_MENUS", menus)
        router.addRoutes(menus) // 动态添加可访问路由表
        next({...to, replace: true}) // hack方法 确保addRoutes已完成 ,set the replace: true so the navigation will not leave a history record
      }).catch(err => {
        console.error(err)
        store.dispatch('front_logout').then(() => {
          Message.error('信息过期，请重新登录')
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
})
router.afterEach(() => {
  NProgress.done()
})

export default router;


