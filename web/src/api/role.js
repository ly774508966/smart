import request from '@/utils/request'

export function role_query() {
  return request({
    url: '/role/query',
    method: 'post'
  })
}
