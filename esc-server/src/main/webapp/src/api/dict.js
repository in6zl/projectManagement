import request from '@/utils/request'

export function getDictType(data) {
  return request({
    url: '/dicttype/list',
    method: 'post',
    data
  })
}
export function addDictType(data) {
  return request({
    url: '/dicttype/add',
    method: 'post',
    data
  })
}
export function deleteDictType(data) {
  return request({
    url: '/dicttype/'+data,
    method: 'delete',
  })
}
export function updateDictType(data) {
  return request({
    url: '/dicttype/update',
    method: 'put',
    data
  })
}
export function findDictType(data) {
  return request({
    url: '/dicttype/'+data,
    method: 'get',
  })
}
export function searchDictType(data) {
  return request({
    url: '/dicttype/page',
    method: 'post',
    data
  })
}



export function getDict(data) {
  return request({
    url: '/dict/list',
    method: 'post',
    data
  })
}
export function deleteDict(data) {
  return request({
    url: '/dict/'+data,
    method: 'delete',
  })
}
export function searchDict(data) {
  return request({
    url: '/dict/page',
    method: 'post',
    data
  })
}
export function addDict(data) {
  return request({
    url: '/dict/add',
    method: 'post',
    data
  })
}
export function findDict(data) {
  return request({
    url: '/dict/'+data,
    method: 'get',
  })
}
export function updateDict(data) {
  return request({
    url: '/dict/update',
    method: 'put',
    data
  })
}
export function getValue(data) {
  return request({
    url: '/dict/DictTypeName/'+data,
    method: 'get',
  })
}


export function getByName(data) {
  return request({
    url:  '/dicttype/multipleDictType',
    method: 'post',
    data
  })
}