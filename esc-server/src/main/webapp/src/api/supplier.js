import request from '@/utils/request'

export function getSupplier(data) {
    return request({
      url: '/supplier/list',
      method: 'get',
      data
    })
  }

export function listSupplier(query) {
  return request({
    url: '/supplier/list?' + query,
    method: 'get'
  })
}

export function createSupplier(data) {
  return request({
    url: '/supplier/create',
    method: 'post',
    data: data
  })
}
export const codeGen = () => request({
  method: 'get',
  url: '/supplier/codeGen'
})

export function deleteSupplier(data) {
  return request({
    url: '/supplier/delete/'+data,
    method: 'delete',
  })
}
export function updateSupplier(data) {
  return request({
    url: '/supplier/update',
    method: 'put',
    data
  })
}
export function findSupplier(data) {
  return request({
    url: '/supplier/'+data,
    method: 'get',
  })
}
export function searchSupplier(data) {
  return request({
    url: '/supplier/page',
    method: 'post',
    data: data
  })
}

export function getByName(data) {
  return request({
    url:  '/supplier/multipleSupplier',
    method: 'post',
    data
  })
}
