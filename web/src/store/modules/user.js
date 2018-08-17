import {login, logout} from '@/api/account'
import {getToken, removeToken, setToken} from '@/utils/auth'
import {Message} from 'element-ui'

const user = {
  state: {
    user: undefined,
    token: getToken()
  },

  mutations: {
    SET_USER: (state, user) => {
      state.user = user;
    },
    SET_TOKEN: (state, token) => {
      state.token = token
    }
  },

  actions: {
    // 登录
    login({commit}, form) {
      return new Promise((resolve, reject) => {
        login(form.account, form.password, form.captcha).then(response => {
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
        logout(state.token).then(() => {
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
