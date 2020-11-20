import request from '@/utils/request'

export function page(data) {
  return request({
    url: '/productionPlan/page',
    method: 'post',
    data,
  })
}

export function deleteItem(id) {
  return request({
    url: '/productionPlan/delete/' + id,
    method: 'get'
  })
}

export function get(id) {
  return request({
    url: '/productionPlan/' + id,
    method: 'get'
  })
}

export function list(data) {
  return request({
    url: '/productionPlan/list',
    method: 'post',
    data,
  })
}
