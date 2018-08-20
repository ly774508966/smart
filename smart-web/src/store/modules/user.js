import { account_login, account_logout, account_user } from '@/api/account'
import { department_query_tree } from '@/api/department'
import { role_query } from '@/api/role'
import { getToken, removeToken, setToken } from '@/utils/auth'
import { Message } from 'element-ui'

const user = {
  state: {
    user: undefined,
    token: getToken(),
    menus: [],
    roles: [],
    departments: [],
    departmentsMap: {}
  },

  mutations: {
    SET_USER: (state, user) => {
      console.log('【store】->SET_USER')
      state.user = user
    },
    SET_TOKEN: (state, token) => {
      console.log('【store】->SET_TOKEN')
      state.token = token
    },
    SET_ROLES: (state, roles) => {
      console.log('【store】->SET_ROLES')
      state.roles = roles
    },
    SET_DEPARTMENTS: (state, departments) => {
      console.log('【store】->SET_DEPARTMENTS')
      state.departments = departments
    },
    SET_DEPARTMENTS_MAP: (state, departmentsMap) => {
      console.log('【store】->SET_DEPARTMENTS_MAP')
      state.departmentsMap = departmentsMap
    },
    SET_MENUS: (state, routers) => {
      console.log('【store】-> SET_MENUS')
      state.menus = routers
    }
  },

  actions: {
    //登录
    login ({commit}, form) {
      return new Promise((resolve, reject) => {
        account_login(form).then(response => {
          const data = response.data
          if (data.msg == '成功') {
            commit('SET_TOKEN', data.token)
            setToken(data.token)
            resolve(data)
          } else {
            Message({message: data.msg, type: 'error', duration: 5 * 1000})
            reject('error')
          }
        }).catch(error => {
          reject(error)
        })
      })
    },
    //后端登出
    logout ({commit, state}) {
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
    front_logout ({commit}) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    },
    //全部部门
    departments ({commit, state}, {fromCache}) {
      function convertDepartments (items, nodes) {
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
            convertDepartments(tmp.children, node.subs)
          }
          items.push(tmp)
        })
        return items
      }

      function convertDepartmentsMap (map, item, nodes) {
        nodes.forEach(node => {
          map[node.node.id] = item.concat(node.node.id)
          if (node.subs.length > 0) {
            convertDepartmentsMap(map, item.concat(node.node.id), node.subs)
          }
        })
        return map
      }

      return new Promise((resolve, reject) => {
        if (fromCache && state.departments.length > 0) {
          resolve(state.departments)
          return
        }
        department_query_tree().then(res => {
          commit('SET_DEPARTMENTS', convertDepartments([], res.data.subs))
          commit('SET_DEPARTMENTS_MAP', convertDepartmentsMap({}, [], res.data.subs))
          resolve(state.departments)
        }).catch(error => {
          reject(error)
        })
      })
    },
    //全部角色
    roles ({commit, state}, {fromCache}) {
      return new Promise((resolve, reject) => {
        if (fromCache && state.roles.length > 0) {
          resolve(state.roles)
          return
        }
        role_query().then(res => {
          commit('SET_ROLES', res.data)
          resolve(state.roles)
        }).catch(error => {
          reject(error)
        })
      })
    },
    //用户信息（含菜单、用户信息，页面初始化时获取）
    user ({commit, state}) {
      return new Promise((resolve, reject) => {
        account_user().then(res => {
          commit('SET_USER', res.data.user)
          commit('SET_MENUS', res.data.menus.subs)
          resolve(res.data.menus)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default user
