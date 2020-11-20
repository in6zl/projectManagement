import request from '@/utils/request'

export function addDeliver(data) {
  return request({
    url: '/deliveryPlan/create',
    method: 'post',
    data
  })
}

export function updatDeliver(data) {
  return request({
    url: '/deliveryPlan/update',
    method: 'post',
    data
  })
}

export function getDeliver(id) {
  return request({
    url: '/deliveryPlan/' + id,
    method: 'get'
  })
}

export function deliveryList(data) {
  return request({
    url: '/deliveryPlan/list',
    method: 'post',
    data
  })
}

export function deliveryPlanList(data) {
  return request({
    url: '/deliveryPlan/deliveryPlanList?',
    method: 'post',
    data
  })
}

export function pageDeliver(data) {
  return request({
    url: '/car/page',
    method: 'post',
    params: data
  })
}

export function deleteDeliver(id) {
  return request({
    url: '/deliveryPlan/delete/' + id,
    method: 'delete'
  })
}
export function deleteDetailDeliver(id) {
  return request({
    url: '/deliveryPlan/deleteDeliveryPlanDetails/' + id,
    method: 'delete'
  })
}
export function atuoUnit(id) {
  return request({
    url: 'product/getUnit?id=' + id,
    method: 'get'
  })
}
export function supplyList(data) {
  return request({
    url: '/deliveryPlan/batchSave',
    method: 'post',
    data
  })
}

export function findBySale(data) {
  return request({
    url: '/deliveryPlan/sale/'+data,
    method: 'get',
  })
}