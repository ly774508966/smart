import request from '@/utils/request'
import qs from 'Qs'

export function account_login(form) {
  return request.post('/account/login', qs.stringify(form))
}

export function account_logout() {
  return request.post('/account/logout')
}

export function account_user() {
  return request.post('/account/user')
}

export function account_captcha() {
  return process.env.BASE_API + "/account/captcha.png?i=" + Math.random();
}
