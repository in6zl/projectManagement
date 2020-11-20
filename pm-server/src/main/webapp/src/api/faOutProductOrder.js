import request from '@/utils/request'

export function addFaOutProductOrder(data) {
    return request({
      url: '/outProductOrder/add',
      method: 'post',
      data
    })
  }


  export function updateFaOutProductOrder(data) {
    return request({
      url: '/outProductOrder/update',
      method: 'put',
      data
    })
  }

export function getFaOutProductOrderPage(data) {
    return request({
      url: '/outProductOrder/page',
      method: 'post',
      data
    })
}

export function getFaOutProductOrderList(data) {
  return request({
    url: '/outProductOrder/list',
    method: 'post',
    data
  })
}

export function findFaOutProductOrder(data) {
    return request({
      url: '/outProductOrder/'+data,
      method: 'get'
    })
}
export function deleteFaOutProductOrder(data) {
    return request({
      url: '/outProductOrder/'+data,
      method: 'delete',
    })
  }

export function transformFaOutProductOrderList(data) {
    if(data){
        data.forEach(function(v){
            if(v.type==1){
              v.type='物料仓库';
            }else{
              v.type='成品仓库';
            }
            if(v.isFull==1){
              v.isFull='是';
            }else{
              v.isFull='否';
            }
            if(v.isOpen==1){
              v.isOpen='是';
            }else{
              v.isOpen='否';
            }
          });
    }
    return data;
}

export function getNumber() {
  return request({
    url: '/outProductOrder/getNumber',
    method: 'get',
  })
}

export function getOutProductOrderNo(data) {
    return request({
        url: '/outProductOrder/getOutProductOrderNo/'+ data.carId +'/'+ data.applicationId,
        method: 'get',
    })
}

export function getWarehouseProduct(data) {
    return request({
        url: '/outProductOrder/getWarehouseProduct/'+ data.warehouseId,
        method: 'get',
    })
}

export function getProductStorehouse(data) {
    return request({
        url: '/outProductOrder/getProductStorehouse/'+ data.warehouseId +'/'+ data.productId,
        method: 'get',
    })
}

export function getStorehouseNum(data) {
    return request({
        url: '/outProductOrder/getStorehouseNum/'+ data.warehouseId +'/'+ data.storehouseId +'/'+ data.productId,
        method: 'get',
    })
}

export function outProduct(id) {
    return request({
        url: '/outProductOrder/outProduct/'+ id,
        method: 'get',
    })
}

export function outProductFinished(id) {
    return request({
        url: '/outProductOrder/outProductFinished/'+ id,
        method: 'get',
    })
}





