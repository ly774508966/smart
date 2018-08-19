import request from '@/utils/request'
import qs from 'Qs'

export function department_query_tree() {
  return request.post('/department/query/tree')
}
