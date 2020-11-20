import request from '@/utils/request'

export function addFaStorehouse(data) {
  return request({
    url: '/storageBin/add',
    method: 'post',
    data
  })
}

export function updateFaStorehouse(data) {
    return request({
      url: '/storageBin/update',
      method: 'put',
      data
    })
  }

export function getFaStorehousePage(data) {
    return request({
      url: '/storageBin/page',
      method: 'post',
      data
    })
}

export function getFaStorehouseList(data) {
  return request({
    url: '/storageBin/list',
    method: 'post',
    data
  })
}

export function initBinTree() {
  return request({
    url: '/storageBin/initBinTree',
    method: 'get'
  })
}

export function findBaseInfoOfFaStorehouseNullByFaStorageBin(data) {
  return request({
    url: '/storageBin/findBaseInfoOfStorageBinNullByFaStorageBin/'+data,
    method: 'get'
  })
}

export function materialToFaStorehouseListMap(inboundOrderMaterialId) {
  return request({
    url: '/storageBin/findProductMaterialAndStorageBinByProductMaterialId?inboundOrderMaterialId='+inboundOrderMaterialId,
    method: 'get'
  })
}

export function findFaStorehouse(data) {
    return request({
      url: '/storageBin/'+data,
      method: 'get'
    })
}
export function deleteFaStorehouse(data) {
    return request({
      url: '/storageBin/'+data,
      method: 'delete',
    })
  }

export function transformFaStorehouseList(data) {
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
            let faStorehouseTag=v.faStorehouseTag;
            let tagNames="";
            if(faStorehouseTag){
              for(let i=0;i<faStorehouseTag.length;i++){
                tagNames+=faStorehouseTag[i].name;
                if(i+1!=faStorehouseTag.length){
                  tagNames+=",";
                }
              }
            }
            v.tagNames=tagNames;
        });
    }
    return data;
}

export function getByName(data) {
  return request({
    url:  '/storageBin/multipleStorageBin',
    method: 'post',
    data
  })
}


export function findByWareHouseIdAndMaterialId(wareHouseId,materialId) {
  return request({
    url:  '/storageBin/findByWareHouseIdAndMaterialId?wareHouseId='+wareHouseId+'&materialId='+materialId,
    method: 'get'
  })
}