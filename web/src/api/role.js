import request from '@/utils/request'

export function role_query() {
  return request.post('/role/query')
}
