import {account_login, account_logout, account_user} from '@/api/account'
import {department_query_tree} from '@/api/department'
import {role_query} from '@/api/role'
import {getToken, removeToken, setToken} from '@/utils/auth'
import {Message} from 'element-ui'
import Layout from '@/views/layout/Layout'

const user = {
  state: {
    user: undefined,
    token: getToken(),
    menus: [],
    roles: [],
    departments: []
  },

  mutations: {
    SET_USER: (state, user) => {
      console.log("【store】->SET_USER")
      state.user = user;
    },
    SET_TOKEN: (state, token) => {
      console.log("【store】->SET_TOKEN")
      state.token = token
    },
    SET_ROLES: (state, roles) => {
      console.log("【store】->SET_ROLES")
      state.roles = roles
    },
    SET_DEPARTMENTS: (state, departments) => {
      console.log("【store】->SET_DEPARTMENTS")
      state.departments = departments
    },
    SET_MENUS: (state, routers) => {
      console.log("【store】-> SET_MENUS")
      state.menus = routers
    }
  },

  actions: {
    //登录
    login({commit}, form) {
      return new Promise((resolve, reject) => {
        account_login(form).then(response => {
          const data = response.data
          if (data.msg == "成功") {
            commit('SET_TOKEN', data.token)
            setToken(data.token)
            resolve(data)
          } else {
            Message({message: data.msg, type: 'error', duration: 5 * 1000});
            reject('error')
          }
        }).catch(error => {
          reject(error)
        })
      })
    },
    //后端登出
    logout({commit, state}) {
      return new Promise((resolve, reject) => {
        account_logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    //前端登出
    front_logout({commit}) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    },
    //部门
    departments({commit, state}, {fromCache}) {
      function convertDepartments(items, nodes) {
        nodes.forEach(node => {
          var tmp = {
            id: node.node.id,
            value: node.node.id,
            name: node.node.name,
            label: node.node.name,
            description: node.node.description,
          }
          if (node.subs != null && node.subs.length > 0) {
            tmp.children = []
            convertDepartments(tmp.children, node.subs);
          }
          items.push(tmp)
        })
        return items;
      }

      return new Promise((resolve, reject) => {
        if (fromCache && state.departments.length > 0) {
          resolve(state.departments)
        }
        department_query_tree().then(res => {
          commit('SET_DEPARTMENTS', convertDepartments([], res.data.subs))
          resolve(state.departments)
        }).catch(error => {
          reject(error)
        })
      })
    },
    //角色
    roles({commit, state}, {fromCache}) {
      return new Promise((resolve, reject) => {
        if (fromCache && state.roles.length > 0) {
          resolve(state.roles);
        }
        role_query().then(res => {
          commit('SET_ROLES', res.data)
          resolve(state.roles)
        }).catch(error => {
          reject(error)
        })
      })
    },
    //用户信息
    user({commit, state}) {
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
        // console.log(node)
        if (node.type == 2) {
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

      return new Promise((resolve, reject) => {
        account_user().then(res => {
          var menus = [].concat([{
            meta: {title: '首页', icon: 'fa-file-text'},
            path: '/',
            component: Layout,
            redirect: '/home',
            name: 'home',
            children: [{name: 'home-index', meta: {title: '首页'}, path: 'home', component: () => import('@/views/home')}]
          }]).concat(convertMenus(res.data.perms));
          commit("SET_USER", res.data.user)
          commit("SET_MENUS", menus)
          resolve(menus)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}


export default user
