import request from '@/utils/request'

export function addInboundOrderProduct(data) {
  return request({
    url: '/inboundOrder/product/create',
    method: 'post',
    data
  })
}

export function updateInboundOrderProduct(data) {
  return request({
    url: '/inboundOrder/product/update',
    method: 'put',
    data
  })
}

export function getInboundOrderProduct(id) {
  return request({
    url: '/inboundOrder/product/' + id,
    method: 'get'
  })
}

export function listInboundOrderProduct(data) {
  return request({
    url: '/inboundOrder/product/list',
    method: 'post',
    data
  })
}

export function pageInboundOrderProduct(data) {
  return request({
    url: '/inboundOrder/product/page',
    method: 'post',
    data
  })
}

export function deleteInboundOrderProduct(id) {
  return request({
    url: '/inboundOrder/product/' + id,
    method: 'delete'
  })
}


export function inboundOrderProductSotreHouseSuggest(data) {
  return request({
    url: '/inboundOrder/product/inboundOrderProductSotreHouseSuggest/',
    method: 'post',
    data
  })
}


export function finishInboundOrderProduct(data) {
  return request({
    url: '/inboundOrder/product/finish/'+data,
    method: 'get'
  })
}

