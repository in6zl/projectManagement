import request from '@/utils/request'

export function add(data) {
  return request({
    url: '/material/insert',
    method: 'post',
    data,
  })
}

export function edit(data) {
  return request({
    url: '/material/update',
    method: 'post',
    data,
  })
}

export function updateAll(data) {
  return request({
    url: '/material/updateAll',
    method: 'post',
    data,
  })
}


export function deleteItem(id) {
  return request({
    url: '/material/delete/' + id,
    method: 'get'
  })
}

export function deleteRequisitionItem(id) {
  return request({
    url: '/materialRequisition/delete/' + id,
    method: 'get'
  })
}

export function getMaterialRequisitionById(id){
  return request({
    url: '/materialRequisition/find/' + id,
    method: 'get'
  })
}

export function findAllMaterialData(data) {
  return request({
    url: '/material/findAll',
    method: 'post',
    data,
  })
}

export function addTags(data) {
  return request({
    url: '/material/addTag',
    method: 'post',
    data,
  })
}

export function findMaterialRequisitionPageData(data) {
  return request({
    url: '/materialRequisition/find',
    method: 'post',
    data,
  })
}

export function updateMaterialRequisition(data){
  return request({
    url: '/materialRequisition/update',
    method: 'post',
    data,
  })
}

export function findMaterialListData() {
  return request({
    url: '/material/find-list',
    method: 'get'
  })
}

export function findListByWaHouse(id) {
  return request({
    url: '/material/findListByWaHouse/'+ id,
    method: 'get'
  })
}


export function insertMaterialRequisition(data){
  return request({
    url:'/materialRequisition/insert',
    method:'post',
    data
  })
}
export function save(data){
  return request({
    url:'/materialRequirePlan/materialRequisition/save',
    method:'post',
    data
  })
}

export function getBaseInfo(data) {
  return request({
      url: '/material/getBaseInfo/' + data,
      method: 'get',
  })
}

export function findBaseInfoList() {
  return request({
      url: '/material/findBaseInfoList/',
      method: 'get',
  })
}


export function batchBindTag(data) {
  return request({
    url:  '/material/batchBindTag',
    method: 'post',
    data
  })
}
export function findByName(data){
  return request({
    url:'/material/multipleMaterial',
    method:'post',
    data
  })
}

export function getByName(data) {
  return request({
    url:  '/materialRequisition/multipleMaterialRequisition',
    method: 'post',
    data
  })
}

export function completeMaterialPlan(data) {
  return request({
    url:  '/materialRequirePlan/completeMaterialPlan',
    method: 'post',
    data
  })
}


export function getByProductPlan(data) {
  return request({
    url:  '/materialRequirePlan/getProductionPlan/'+data,
    method: 'get',
  })
}

export function downloadTemplate() {
  return request({
    url: '/materialRequisition/downloadExcel',
    method: 'get',
    responseType: "blob"
  })
}