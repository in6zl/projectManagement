import request from '@/utils/request'

export function add(data) {
  return request({
    url: '/production-plan/insert',
    method: 'post',
    data,
  })
}

export function edit(data) {
  return request({
    url: '/production-plan/update',
    method: 'post',
    data,
  })
}

export function deleteItem(id) {
  return request({
    url: '/production-plan/delete/' + id,
    method: 'get'
  })
}

export function deleteProductionPlanItemById(id) {
  return request({
    url: '/materialRequirePlan/deleteProductionPlanItemById/' + id,
    method: 'delete'
  })
}

export function finishProduct(id) {
  return request({
    url: '/productionPlan/finish/' + id,
    method: 'get'
  })
}

export function findAllProductionPlanData(data) {
  return request({
    url: '/production-plan/findAll',
    method: 'get',
    data,
  })
}

export function findProductionPlanById(id){
  return request({
    url: '/production-plan/find/'+id,
    method: 'get'
  })
}

export function page(data) {
  return request({
    url: '/production-plan/page',
    method: 'post',
    data
  })
}

export function getByName(data) {
  return request({
    url:  '/productionPlan/multipleProductionPlan',
    method: 'post',
    data
  })
}