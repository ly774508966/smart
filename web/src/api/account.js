import request from '@/utils/request'

export function login(account, password, captcha) {
  const data = {
    account,
    password,
    captcha
  }
  return request({
    url: '/account/login',
    method: 'post',
    params: data
  })
}

export function logout() {
  return request({
    url: '/account/logout',
    method: 'post'
  })
}

export function user() {
  return request({
    url: '/account/user',
    method: 'post'
  })
}

export function captcha() {
  return process.env.BASE_API + "/account/captcha.png?i=" + Math.random();
}
