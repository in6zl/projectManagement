import request from '@/utils/request'

  export function getPr(data) {
    return request({
      url: '/productionRecord/'+ data +'',
      method: 'get',
    })
  }
  export function createPr(data) {
    return request({
      url: '/productionRecord/create',
      method: 'post',
      data: data
    })
  }
  export function deletePr(data) {
    return request({
      url: '/productionRecord/delete/' + data,
      method: 'delete',
    })
  }
  export function updatePr(data) {
    return request({
      url: '/productionRecord/update',
      method: 'put',
      data
    })
  }
  export function findPr(data) {
    return request({
      url: '/productionRecord/'+data,
      method: 'get',
    })
  }
  export function searchPr(data) {
    return request({
      url: '/productionRecord/page',
      method: 'post',
      data: data
    })
  }
