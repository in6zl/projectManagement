import request from '@/utils/request'

export function add(data) {
  return request({
    url: '/productionLine/insert',
    method: 'post',
    data,
  })
}

export function edit(data) {
  return request({
    url: '/productionLine/update',
    method: 'post',
    data,
  })
}

export function deleteItem(id) {
  return request({
    url: '/productionLine/delete/' + id,
    method: 'get'
  })
}

export function findAllProductionLineData(data) {
  return request({
    url: '/productionLine/findAll',
    method: 'post',
    data,
  })
}
export function saveMaterialPlan(data) {
  return request({
    url: `/materialRequirePlan/productionPlanItem/save`,
    method: 'post',
    data,
  })
}
export function createMaterialReq(data) {
  return request({
    url: '/materialRequirePlan/productionPlan/create',
    method: 'post',
    data,
  })
}

export function createMaterialReqByProductionPlanItem(data) {
  return request({
    url: '/materialRequirePlan/productionPlanItem/create',
    method: 'post',
    data,
  })
}


export function queryMaterialDetails(data) {
  return request({
    url: '/materialRequirePlan/productionPlan/queryDetails',
    method: 'post',
    data,
  })
}
export function materialPlanList(data) {
  return request({
    url: '/materialRequirePlan/list',
    method: 'post',
    data
  })
}

export function findProductionLine(data) {
  return request({
    url: '/productionLine/find-list',
    method: 'get',
    data,
  })
}
export function getDic(data) {
  return request({
    url: `/dict/getValue/${data}`,
    method: 'get',
  })
}
export function getMaterialRequire(data) {
  return request({
    url: '/materialRequirePlan/getMaterialRequire/'+data,
    method: 'get',
  })
}
export function getByName(data) {
  return request({
    url: '/productionLine/multipleProductLine',
    method: 'post',
    data
  })
}



