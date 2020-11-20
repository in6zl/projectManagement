import request from '@/utils/request'

export function addInboundOrderProductDetail(data) {
  return request({
    url: '/inboundOrderDetail/product/create',
    method: 'post',
    data
  })
}

export function updateInboundOrderProductDetail(data) {
  return request({
    url: '/inboundOrderDetail/product/update',
    method: 'put',
    data
  })
}

export function getInboundOrderProductDetail(id) {
  return request({
    url: '/inboundOrderDetail/product/' + id,
    method: 'get'
  })
}

export function listInboundOrderProductDetail(data) {
  return request({
    url: '/inboundOrderDetail/product/list',
    method: 'post',
    data
  })
}

export function pageInboundOrderProductDetail(data) {
  return request({
    url: '/inboundOrderDetail/product/page',
    method: 'post',
    data
  })
}

export function deleteInboundOrderProductDetail(id) {
  return request({
    url: '/inboundOrderDetail/product/' + id,
    method: 'delete'
  })
}
