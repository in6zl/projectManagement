import request from '@/utils/request'

export function getInspectionRecord(data) {
  return request({
    url: '/inspectionRecord/list',
    method: 'post',
    data
  })
}
export function addInspectionRecord(data) {
  return request({
    url: '/inspectionRecord/add',
    method: 'post',
    data
  })
}
export function deleteInspectionRecord(data) {
  return request({
    url: '/inspectionRecord/'+data,
    method: 'delete',
  })
}
export function updateInspectionRecord(data) {
  return request({
    url: '/inspectionRecord/update',
    method: 'put',
    data
  })
}
export function findInspectionRecord(data) {
  return request({
    url: '/inspectionRecord/'+data,
    method: 'get',
  })
}
export function searchInspectionRecord(data) {
  return request({
    url: '/inspectionRecord/page',
    method: 'post',
    data
  })
}
