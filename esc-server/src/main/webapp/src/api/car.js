import request from '@/utils/request'

export function addCar(data) {
  return request({
    url: '/car/create',
    method: 'post',
    data
  })
}

export function updateCar(data) {
  return request({
    url: '/car/update',
    method: 'put',
    data
  })
}

export function getCar(id) {
  return request({
    url: '/car/' + id,
    method: 'get'
  })
}

export function listCar(data) {
  return request({
    url: '/car/list',
    method: 'post',
    data
  })
}

export function pageCar(data) {
  return request({
    url: '/car/page',
    method: 'post',
    data
  })
}

export function deleteCar(id) {
  return request({
    url: '/car/' + id,
    method: 'delete'
  })
}
