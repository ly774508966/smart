import Vue from 'vue'
import Router from 'vue-router'
/* Layout */
import Layout from '@/views/layout/Layout'

Vue.use(Router)

/** note: submenu only apppear when children.length>=1
 *   detail see  https://panjiachen.github.io/vue-element-admin-site/#/router-and-nav?id=sidebar
 **/

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
export const constantRouterMap = [
  {path: '/login', component: () => import('@/views/login/index'), hidden: true},
  {path: '/404', component: () => import('@/views/errorPage/404'), hidden: true},
  {path: '/401', component: () => import('@/views/errorPage/401'), hidden: true},
  {
    name: 'index',
    path: '',
    component: Layout,
    redirect: '/home/index',
    children: [{
      path: 'home/index',
      component: () => import('@/views/home'),
      name: 'home',
      meta: {title: '首页', icon: 'fa-home'}
    }]
  },
  {
    name: 'order',
    path: '/order',
    component: Layout,
    redirect: 'noredirect',
    meta: {title: '订单管理', icon: 'fa-file-text'},
    alwaysShow: true,
    children: [{
      path: 'good',
      component: () => import('@/views/order/good'),
      name: 'good',
      meta: {title: '商品管理'}
    }, {
      path: 'order_index',
      component: () => import('@/views/order/order'),
      name: 'order_index',
      meta: {title: '订单管理'}
    }, {
      path: 'route',
      component: () => import('@/views/order/route'),
      name: 'route',
      meta: {title: '配送路线管理'}
    }]
  },
  {
    name: 'user',
    path: '/user',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    meta: {title: '人员管理', icon: 'fa-user'},
    children: [{
      path: 'saler',
      component: () => import('@/views/user/saler'),
      name: 'saler',
      meta: {title: '导购管理'}
    }, {
      path: 'driver',
      component: () => import('@/views/user/driver'),
      name: 'driver',
      meta: {title: '司机管理'}
    }, {
      path: 'carrier',
      component: () => import('@/views/user/carrier'),
      name: 'carrier',
      meta: {title: '上楼工管理'}
    }]
  },
  {
    name: 'organization',
    path: '/organization',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    meta: {title: '组织管理', icon: 'fa-users'},
    children: [{
      path: 'distributor',
      component: () => import('@/views/organization/distributor'),
      name: 'distributor',
      meta: {title: '代理商管理'}
    }, {
      path: 'department',
      component: () => import('@/views/organization/department'),
      name: 'department',
      meta: {title: '部门管理'}
    }]
  },
  {
    name: 'statistics',
    path: '/statistics',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    meta: {title: '数据统计', icon: 'fa-file-excel-o'},
    children: [
      {
        path: 'driver_use',
        component: () => import('@/views/statistics/driver_use'),
        name: 'driver_use',
        meta: {title: '司机日活'},
      },
      {
        path: 'carrier_use',
        component: () => import('@/views/statistics/carrier_use'),
        name: 'carrier_use',
        meta: {title: '上楼工日活'},
      }]
  },
  {
    name: 'system',
    path: '/system',
    component: Layout,
    redirect: 'noredirect',
    meta: {title: '系统管理', icon: 'fa-cog'},
    alwaysShow: true,
    children: [{
      path: 'admin',
      component: () => import('@/views/system/admin'),
      name: 'admin',
      meta: {title: '管理员管理'},
    },
      {
        path: 'role',
        component: () => import('@/views/system/role'),
        name: 'role',
        meta: {title: '角色管理'},
      },
      {
        path: 'log',
        component: () => import('@/views/system/log'),
        name: 'log',
        meta: {title: '日志管理'},
      },
      {
        path: 'global',
        component: () => import('@/views/system/global'),
        name: 'global',
        meta: {title: '全局设置'},
      }]
  },
  {
    path: '/programmer',
    component: Layout,
    redirect: 'noredirect',
    name: 'programmer',
    meta: {title: '开发使用', icon: 'fa-bug'},
    alwaysShow: true,
    children: [
      {
        path: 'menu',
        component: () => import('@/views/programmer/menu'),
        name: 'menu',
        meta: {title: '菜单管理'}
      },
      // {
      //   path: 'dictionary',
      //   component: () => import('@/views/programmer/dictionary'),
      //   name: 'dictionary',
      //   meta: {title: '字典管理'}
      // },
      {
        path: 'exception',
        component: () => import('@/views/programmer/exception'),
        name: 'exception',
        meta: {title: '异常管理'}
      }]
  }
]

export default new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})

export const asyncRouterMap = [
  {path: '*', redirect: '/404', hidden: true}
]
