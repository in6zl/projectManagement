import request from '@/utils/request'

export function getSupplier(data) {
    return request({
      url: '/invoice/list',
      method: 'post',
      data
    })
  }
  export function createSupplier(data) {
    return request({
      url: '/invoice/create',
      method: 'post',
      data: data
    })
  }
  export function deleteSendOrder(data) {
    return request({
      url: '/invoice/delete/'+data,
      method: 'delete',
    })
  }
  export function updateSupplier(data) {
    return request({
      url: '/invoice/update',
      method: 'put',
      data
    })
  }
  export function findSendOrder(data) {
    return request({
      url: '/invoice/' + data,
      method: 'get',
    })
  }
  export function searchSendOrder(data) {
    return request({
      url: '/invoice/page',
      method: 'post',
      data: data
    })
  }
  export function sendSendOrder(data) {
    return request({
      url: '/invoice/send/'+data,
      method: 'get'
    })
  }


  export function findInboundList(data) {
    return request({
      url: '/invoice/findInboundList',
      method: 'get',
      data
    })
  }
  


  export function getSupplierDelivery(data) {
    return request({
      url: '/invoice/delivery/list',
      method: 'post',
      data
    })
  }
  export function createSupplierDelivery(data) {
    return request({
      url: '/invoice/delivery/create',
      method: 'post',
      data: data
    })
  }
  export function deleteSendOrderDelivery(data) {
    return request({
      url: '/invoice/delivery/delete/'+data,
      method: 'delete',
    })
  }
  export function updateSupplierDelivery(data) {
    return request({
      url: '/invoice/delivery/update',
      method: 'put',
      data
    })
  }
  export function findSendOrderDelivery(data) {
    return request({
      url: '/invoice/delivery/' + data,
      method: 'get',
    })
  }
  export function searchSendOrderDelivery(data) {
    return request({
      url: '/invoice/delivery/page',
      method: 'post',
      data: data
    })
  }
  export function getByName(data) {
    return request({
      url:  '/invoice/multipleSendOrder',
      method: 'post',
      data
    })
  }
