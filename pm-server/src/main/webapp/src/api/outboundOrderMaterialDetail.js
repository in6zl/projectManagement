import request from '@/utils/request'

export function addOutboundOrderMaterialDetail(data) {
  return request({
    url: '/outboundOrderDetail/material/create',
    method: 'post',
    data
  })
}

export function updateOutboundOrderMaterialDetail(data) {
  return request({
    url: '/outboundOrderDetail/material/update',
    method: 'put',
    data
  })
}

export function getOutboundOrderMaterialDetail(id) {
  return request({
    url: '/outboundOrderDetail/material/' + id,
    method: 'get'
  })
}

export function listOutboundOrderMaterialDetail(data) {
  return request({
    url: '/outboundOrderDetail/material/list',
    method: 'post',
    data
  })
}

export function pageOutboundOrderMaterialDetail(data) {
  return request({
    url: '/outboundOrderDetail/material/page',
    method: 'post',
    data
  })
}

export function deleteOutboundOrderMaterialDetail(id) {
  return request({
    url: '/outboundOrderDetail/material/' + id,
    method: 'delete'
  })
}
