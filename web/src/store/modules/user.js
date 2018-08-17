import {account_login, account_logout} from '@/api/account'
import {getToken, removeToken, setToken} from '@/utils/auth'
import {Message} from 'element-ui'

const user = {
  state: {
    user: undefined,
    token: getToken(),
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
      state.departments = departments
    }
  },

  actions: {
    // 登录
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
    // 后端登出
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
    // 前端登出
    front_logout({commit}) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    }
  }
}

export default user
