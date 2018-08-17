import axios from 'axios'
import {Message} from 'element-ui'
import store from '@/store'

// create an axios instance
const service = axios.create({
  baseURL: process.env.BASE_API, // api的base_url
  timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(config => {
  // Do something before request is sent
  config.withCredentials = true;
  var token = store.getters.token;
  if (token) {
    config.headers['token'] = token // 让每个请求携带token-- ['X-Token']为自定义key 请根据实际情况自行修改
  }
  console.log("【request】->" + config.url)
  return config
}, error => {
  // Do something with request error
  // console.log(error) // for debug
  Promise.reject(error)
})

// respone interceptor
service.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    // console.error(error)// for debug
    if (error.response.status == 401) {
      MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
        confirmButtonText: '重新登录',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        store.dispatch('front_logout').then(() => {
          location.reload();// 为了重新实例化vue-router对象 避免bug
        });
      })
    }
    else if (error.response.status == 400) {
      Message({
        message: error.response.data,
        type: 'error',
        duration: 5 * 1000
      })
    }
    else {
      Message({
        message: error.message,
        type: 'error',
        duration: 5 * 1000
      })
    }
    return Promise.reject(error)
  }
)

export default service
