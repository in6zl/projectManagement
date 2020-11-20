import request from '@/utils/request'

export function addOutboundOrderProduct(data) {
  return request({
    url: '/outboundOrder/product/create',
    method: 'post',
    data
  })
}

export function updateOutboundOrderProduct(data) {
  return request({
    url: '/outboundOrder/product/update',
    method: 'put',
    data
  })
}

export function getOutboundOrderProduct(id) {
  return request({
    url: '/outboundOrder/product/' + id,
    method: 'get'
  })
}

export function listOutboundOrderProduct(data) {
  return request({
    url: '/outboundOrder/product/list',
    method: 'post',
    data
  })
}

export function pageOutboundOrderProduct(data) {
  return request({
    url: '/outboundOrder/product/page',
    method: 'post',
    data
  })
}

export function deleteOutboundOrderProduct(id) {
  return request({
    url: '/outboundOrder/product/' + id,
    method: 'delete'
  })
}

export function outbound(id) {
  return request({
    url: '/outboundOrder/product/outbound/' + id,
    method: 'get'
  })
}

export function getDetailAndStorehouseNum(id) {
  return request({
    url: '/outboundOrder/product/getDetailAndStorehouseNum/' + id,
    method: 'get'
  })
}



export function outboundOrderProductSotreHouseSuggest(data) {
  return request({
    url: '/outboundOrder/product/outboundOrderProductSotreHouseSuggest',
    method: 'post',
    data
  })
}




