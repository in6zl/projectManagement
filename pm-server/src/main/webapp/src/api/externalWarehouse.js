import request from '@/utils/request'

export function getWarehouse(data) {
    return request({
      url: '/externalWarehouse/list',
      method: 'get',
      data
    })
  }
  export function createWarehouse(data) {
    return request({
      url: '/externalWarehouse/create',
      method: 'post',
      data: data
    })
  }
  export function deleteWarehouse(data) {
    return request({
      url: '/externalWarehouse/delete/'+data,
      method: 'delete',
    })
  }
  export function updateWarehouse(data) {
    return request({
      url: '/externalWarehouse/update',
      method: 'put',
      data
    })
  }
  export function findWarehouse(data) {
    return request({
      url: '/externalWarehouse/'+data,
      method: 'get',
    })
  }
  export function searchWarehouse(data) {
    return request({
      url: '/externalWarehouse/page',
      method: 'post',
      data: data
    })
  }
  export function getByName(data) {
    return request({
      url:  '/externalWarehouse/multipleWareHouse',
      method: 'post',
      data
    })
  }
