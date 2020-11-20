import request from '@/utils/request'

export function addProduct(data) {
    return request({
        url: '/product/add',
        method: 'post',
        data
    })
}
export function deleteProduct(data) {
    return request({
        url: '/product/' + data,
        method: 'delete',
    })
}
export function updateProduct(data) {
    return request({
        url: '/product',
        method: 'put',
        data
    })
}

export function addTags(data) {
    return request({
      url: '/product/addTag',
      method: 'post',
      data,
    })
  }

export function getProductInfo(data) {
    return request({
        url: '/product/getProductInfo/' + data,
        method: 'get',
    })
}
export function pageProduct(data,pageNum,pageSize) {
    return request({
        url: '/product/page?pageNum='+pageNum+"&pageSize="+pageSize,
        method: 'post',
        data
    })
}

export function prolist(data) {
    return request({
        url: '/product/list',
        method: 'post',
        data
    })
}


export function findListByWaHouse(id) {
    return request({
      url:  '/product/findListByWaHouse/' + id,
      method: 'get'
    })
  }

  export function batchBindTag(data) {
    return request({
      url:  '/product/batchBindTag',
      method: 'post',
      data
    })
  }
  
 
  export function getByName(data) {
    return request({
      url:  '/product/multipleProduct',
      method: 'post',
      data
    })
  }

  export function isUpdate(data) {
    return request({
      url:  '/product/isUpdate/'+data,
      method: 'get',
    })
  }      