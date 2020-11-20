import request from '@/utils/request'

export function addOutboundOrderProductDetail(data) {
  return request({
    url: '/outboundOrderDetail/product/create',
    method: 'post',
    data
  })
}

export function updateOutboundOrderProductDetail(data) {
  return request({
    url: '/outboundOrderDetail/product/update',
    method: 'put',
    data
  })
}

export function getOutboundOrderProductDetail(id) {
  return request({
    url: '/outboundOrderDetail/product/' + id,
    method: 'get'
  })
}

export function listOutboundOrderProductDetail(data) {
  return request({
    url: '/outboundOrderDetail/product/list',
    method: 'post',
    data
  })
}

export function pageOutboundOrderProductDetail(data) {
  return request({
    url: '/outboundOrderDetail/product/page',
    method: 'post',
    data
  })
}

export function deleteOutboundOrderProductDetail(id) {
  return request({
    url: '/outboundOrderDetail/product/' + id,
    method: 'delete'
  })
}
