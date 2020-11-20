import request from '@/utils/request'
export function addUnit(data) {
    return request({
        url: '/unit/add',
        method: 'post',
        data
    })
}

export function deleteUnit(data) {
    return request({
        url: '/unit/'+data,
        method: 'delete'
    })
}

export function updateUnit(data) {
    return request({
        url: '/unit',
        method: 'put',
        data
    })
}

export function getUnitInfo(data) {
    return request({
        url: '/unit/getUnitInfo/'+data,
        method: 'get'
    })
}

export function list(data) {
    return request({
        url: '/unit/list',
        method: 'post',
        data
    })
}

export function page(data) {
    return request({
        url: '/unit/page',
        method: 'post',
        data
    })
}
export function getByName(data) {
    return request({
      url:  '/unit/multipleUnit',
      method: 'post',
      data
    })
  }