import request from '@/utils/request'
import qs from 'Qs'

export function user_query(form) {
  return request.post('/user/query', qs.stringify(form))
}

export function user_add(form) {
  return request.post('/user/add', qs.stringify(form))
}

export function user_get(account) {
  return request.post('/user/get', qs.stringify({account}))
}

export function user_delete(account) {
  return request.post('/user/remove', qs.stringify({account}))
}

export function user_modify(form) {
  return request.post('/user/modify', qs.stringify(form))
}



