import request from '@/utils/request'
import qs from 'Qs'

export function user_query(form) {
  return request.post('/user/query', qs.stringify(form))
}


export function user_add(account, name, role_id, dept_id, status) {
  const data = {
    account,
    name,
    role_id,
    dept_id,
    status
  }
  return request({
    url: '/user/add',
    method: 'post',
    params: data
  })
}

export function user_get(account) {
  const data = {
    account
  }
  return request({
    url: '/user/get',
    method: 'post',
    params: data
  })
}

export function user_delete(account) {
  const data = {
    account
  }
  return request({
    url: '/user/delete',
    method: 'post',
    params: data
  })
}

export function user_modify(account, name, role_id, dept_id, status) {
  const data = {
    account,
    name,
    role_id,
    dept_id,
    status
  }
  return request({
    url: '/user/add',
    method: 'post',
    params: data
  })
}



