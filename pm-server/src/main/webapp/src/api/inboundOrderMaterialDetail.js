import request from '@/utils/request'

export function addInboundOrderMaterialDetail(data) {
  return request({
    url: '/inboundOrderDetail/material/create',
    method: 'post',
    data
  })
}

export function updateInboundOrderMaterialDetail(data) {
  return request({
    url: '/inboundOrderDetail/material/update',
    method: 'put',
    data
  })
}

export function getInboundOrderMaterialDetail(id) {
  return request({
    url: '/inboundOrderDetail/material/' + id,
    method: 'get'
  })
}

export function listInboundOrderMaterialDetail(data) {
  return request({
    url: '/inboundOrderDetail/material/list',
    method: 'post',
    data
  })
}

export function pageInboundOrderMaterialDetail(data) {
  return request({
    url: '/inboundOrderDetail/material/page',
    method: 'post',
    data
  })
}

export function deleteInboundOrderMaterialDetail(id) {
  return request({
    url: '/inboundOrderDetail/material/' + id,
    method: 'delete'
  })
}
