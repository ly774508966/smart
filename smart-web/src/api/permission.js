import request from '@/utils/request'

export function permission_query_tree() {
  return request.post('/permission/query/tree')
}

export function permission_query_tree_simple() {
  return request.post('/permission/query/tree/simple')
}
