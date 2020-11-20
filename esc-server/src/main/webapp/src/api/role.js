import request from '@/utils/request'

export function getRoleList(data) {
    return request({
      url: '/role/list',
      method: 'post',
      data
    })
  }
  export function createRole(data) {
    return request({
      url: '/role/create',
      method: 'post',
      data
    })
  }
  export function deleteRole(data) {
    return request({
      url: '/role/'+data,
      method: 'delete',
    })
  }
  export function updateRole(data) {
    return request({
      url: '/role/update',
      method: 'put',
      data
    })
  }
  export function findRole(data) {
    return request({
      url: '/role/'+data,
      method: 'get',
    })
  }
  export function page(data) {
    return request({
      url: '/role/page',
      method: 'post',
      data
    })
  }
  export function getResourceTree() {
    return request({
      url:'role/getResourceTree',
      method: 'get',
    })
  }
  export function getRolesExclusionAdmin() {
    return request({
      url:`role/getRolesExclusionAdmin`,
      method: 'get',
    })
  }
  export function getRolesByOrg(organizationId) {
    return request({
      url:'role/getRolesByOrg/'+ organizationId +'',
      method: 'get',
    })
  }