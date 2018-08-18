import request from '@/utils/request'
import qs from 'Qs'

export function log_login(form) {
  return request.post('/log/login', qs.stringify(form))
}

export function log_operation(form) {
  return request.post('/log/operation', qs.stringify(form))
}

