import request from '@/utils/request'

export function listMaterialOrder(data) {
  return request({
    url: '/materialOrder/list',
    method: 'post',
    data
  })
}
export function addMaterialOrder(data) {
  return request({
    url: '/materialOrder/add',
    method: 'post',
    data
  })
}
export function deleteMaterialOrder(data) {
  return request({
    url: '/materialOrder/'+data,
    method: 'delete',
  })
}
export function updateMaterialOrder(data) {
  return request({
    url: '/materialOrder/update',
    method: 'put',
    data
  })
}
export function getMaterialOrder(data) {
  return request({
    url: '/materialOrder/'+data,
    method: 'get',
  })
}
export function listMaterialOrderPage(data) {
  return request({
    url: '/materialOrder/page',
    method: 'post',
    data
  })
}

export function uploadExcel(data) {
  return request({
    url: 'materialOrder/readExcel',
    method: 'post',
    data,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}
export function downloadTemplate() {
  return request({
    url: 'materialOrder/downloadExcel',
    method: 'get',
    responseType: "blob"
  })
}