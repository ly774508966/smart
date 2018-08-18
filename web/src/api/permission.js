import request from '@/utils/request'

export function permission_query_tree() {
  return request.post('/permission/query/tree')
}
