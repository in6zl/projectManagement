import request from '@/utils/request'

export function addCarSchedule(data) {
  return request({
    url: '/carSchedule/create',
    method: 'post',
    data
  })
}

export function updateCarSchedule(data) {
  return request({
    url: '/carSchedule/update',
    method: 'put',
    data
  })
}

export function getCarSchedule(id) {
  return request({
    url: '/carSchedule/' + id,
    method: 'get'
  })
}

export function listCarSchedule(data) {
  return request({
    url: '/carSchedule/list',
    method: 'post',
    data
  })
}

export function pageCarSchedule(data) {
  return request({
    url: '/carSchedule/page',
    method: 'post',
    data
  })
}

export function deleteCarSchedule(id) {
  return request({
    url: '/carSchedule/' + id,
    method: 'delete'
  })
}
