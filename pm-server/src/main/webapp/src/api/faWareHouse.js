import request from '@/utils/request'

export function addFaWarehouse(data) {
  return request({
    url: '/warehouse/add',
    method: 'post',
    data
  })
}

export function updateFaWarehouse(data) {
    return request({
      url: '/warehouse/update',
      method: 'put',
      data
    })
  }

export function getFaWarehousePage(data) {
    return request({
      url: '/warehouse/page',
      method: 'post',
      data
    })
}

export function getFaWarehouseList(data) {
  return request({
    url: '/warehouse/list',
    method: 'post',
    data
  })
}

export function findFaWarehouse(data) {
    return request({
      url: '/warehouse/'+data,
      method: 'get'
    })
}
export function deleteWareHouse(data) {
    return request({
      url: '/warehouse/'+data,
      method: 'delete',
    })
  }

export function findByLnglat(lng,lat) {
  return request({
    url: 'warehouse/findByLnglat?lng='+lng+"&lat="+lat,
    method: 'get',
  })
}

export function findByRole() {
    return request({
        url: 'warehouse/findByRole',
        method: 'get',
    })
}

export function transformFaWarehouseList(data) {
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
            let faWarehouseTags=v.faWarehouseTag;
            let tagNames="";
            if(faWarehouseTags){
              for(let i=0;i<faWarehouseTags.length;i++){
                tagNames+=faWarehouseTags[i].name;
                if(i+1!=faWarehouseTags.length){
                  tagNames+=",";
                }
              }
            }
            v.tagNames=tagNames;

        });
    }
    console.log('data',data);
    return data;
}


export function getByName(data) {
  return request({
    url:  '/warehouse/multipleWareHouse',
    method: 'post',
    data
  })
}

