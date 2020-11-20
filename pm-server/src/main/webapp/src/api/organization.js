import request from '@/utils/request'

export function getOrganization(data) {
    return request({
      url: '/organization/list',
      method: 'post',
      data
    })
  }
  export function createOrganization(data) {
    return request({
      url: '/organization/create',
      method: 'post',
      data
    })
  }
  export function deleteOrganization(data) {
    return request({
      url: '/organization/delete/'+data,
      method: 'delete',
    })
  }
  export function updateOrganization(data) {
    return request({
      url: '/organization/update',
      method: 'put',
      data
    })
  }
  export function findOrganization(data) {
    return request({
      url: '/organization/'+data,
      method: 'get',
    })
  }
  export function page(data) {
    return request({
      url: '/organization/page',
      method: 'post',
      data
    })
  }


  export function findByName(data) {
    return request({
      url: '/organization/findByName',
      method: 'post',
      data
    })
  }