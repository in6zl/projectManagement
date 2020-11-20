import request from '@/utils/request'

export function addFaWarehouseTag(data) {
  return request({
    url: '/tag/add',
    method: 'post',
    data
  })
}

export function updateFaWarehouseTag(data) {
    return request({
      url: '/tag/update',
      method: 'put',
      data
    })
  }

export function getFaWarehouseTagPage(data) {
    return request({
      url: '/tag/page',
      method: 'post',
      data
    })
}

export function findFaWarehouseTag(data) {
    return request({
      url: '/tag/'+data,
      method: 'get'
    })
}
export function deleteWareHouseTag(data) {
    return request({
      url: '/tag/'+data,
      method: 'delete',
    })
  }

export function getWareHouseTagList(data) {
  return request({
    url: '/tag/list',
    method: 'post',
    data
  })
}

export function transformFaWarehouseTagList(data) {
    if(data){
        data.forEach(function(v){
            if(v.businessType==0){
              v.businessType='仓库';
            }else if(v.businessType==1){
              v.type='货架';
            }else if(v.businessType==2){
              v.type='库位';
            }
            
          });
    }
    return data;
}

export function getByName(data) {
  return request({
    url:  '/tag/multipleTag',
    method: 'post',
    data
  })
}