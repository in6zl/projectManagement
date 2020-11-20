import request from '@/utils/request'

export function addOutboundOrderMaterial(data,id) {
  return request({
    url: '/outboundOrder/material/create/'+id,
    method: 'post',
    data
  })
}

export function updateOutboundOrderMaterial(data) {
  return request({
    url: '/outboundOrder/material/update',
    method: 'put',
    data
  })
}

export function getOutboundOrderMaterial(id) {
  return request({
    url: '/outboundOrder/material/' + id,
    method: 'get'
  })
}

export function listOutboundOrderMaterial(data) {
  return request({
    url: '/outboundOrder/material/list',
    method: 'post',
    data
  })
}

export function pageOutboundOrderMaterial(data) {
  return request({
    url: '/outboundOrder/material/page',
    method: 'post',
    data
  })
}

export function deleteOutboundOrderMaterial(id) {
  return request({
    url: '/outboundOrder/material/' + id,
    method: 'delete'
  })
}


export function outboundOrderMaterialSotreHouseSuggest(data) {
  return request({
    url: '/outboundOrder/material/outboundOrderMaterialSotreHouseSuggest',
    method: 'post',
    data
  })
}
