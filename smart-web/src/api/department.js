import request from '@/utils/request'
import qs from 'Qs'

export function department_query_tree() {
  return request.post('/department/query/tree')
}

export function department_add(form) {
  return request.post('/department/add', qs.stringify(form))
}

export function department_remove(id) {
  return request.post('/department/remove', qs.stringify({id}))
}

export function department_modify(form) {
  return request.post('/department/modify', qs.stringify(form))
}

