import request from '@/utils/request'

export function addInboundOrderMaterial(data) {
  return request({
    url: '/inboundOrder/material/create',
    method: 'post',
    data
  })
}

export function updateInboundOrderMaterial(data) {
  return request({
    url: '/inboundOrder/material/update',
    method: 'put',
    data
  })
}

export function getInboundOrderMaterial(id) {
  return request({
    url: '/inboundOrder/material/' + id,
    method: 'get'
  })
}

export function listInboundOrderMaterial(data) {
  return request({
    url: '/inboundOrder/material/list',
    method: 'post',
    data
  })
}

export function pageInboundOrderMaterial(data) {
  return request({
    url: '/inboundOrder/material/page',
    method: 'post',
    data
  })
}

export function deleteInboundOrderMaterial(id) {
  return request({
    url: '/inboundOrder/material/' + id,
    method: 'delete'
  })
}

export function findSurplusNumGroupByMaterial(sendOrderId) {
  return request({
    url: '/inboundOrder/material/findSurplusNumGroupByMaterial/' + sendOrderId,
    method: 'get'
  })
}


export function finishInboundOrderMaterial(inboundOrderMaterialId) {
  return request({
    url: '/inboundOrder/material/finish/' + inboundOrderMaterialId,
    method: 'get'
  })
}

export function inboundOrderMaterialSotreHouseSuggest(data) {
  return request({
    url: '/inboundOrder/material/inboundOrderMaterialSotreHouseSuggest/' ,
    method: 'post',
    data
  })
}
