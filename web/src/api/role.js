import request from '@/utils/request'
import qs from 'Qs'

export function role_query() {
  return request.post('/role/query')
}

export function role_add(form) {
  return request.post('/role/add', qs.stringify(form))
}

export function role_remove(id) {
  return request.post('/role/remove', qs.stringify({id}))
}

export function role_modify(form) {
  return request.post('/role/modify', qs.stringify(form))
}
