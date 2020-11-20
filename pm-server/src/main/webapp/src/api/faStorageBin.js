import request from '@/utils/request'

export function addFaStorageBin(data) {
  return request({
    url: '/shelves/add',
    method: 'post',
    data
  })
}

export function updateFaStorageBin(data) {
    return request({
      url: '/shelves/update',
      method: 'put',
      data
    })
  }

export function getFaStorageBinPage(data) {
    return request({
      url: '/shelves/page',
      method: 'post',
      data
    })
}

export function getFaStorageBinList(data) {
  return request({
    url: '/shelves/list',
    method: 'post',
    data
  })
}


export function findBaseInfoOfStorageBinNullByFaWarehouse(data) {
  return request({
    url: '/shelves/findBaseInfoOfStorageBinNullByFaWarehouse/'+data,
    method: 'get'
  })
}

export function findFaStorageBin(data) {
    return request({
      url: '/shelves/'+data,
      method: 'get'
    })
}
export function deleteFaStorageBin(data) {
    return request({
      url: '/shelves/'+data,
      method: 'delete',
    })
  }

export function transformFaStorageBinList(data) {
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
            let faStorageBinTag=v.faStorageBinTag;
            let tagNames="";
            if(faStorageBinTag){
              for(let i=0;i<faStorageBinTag.length;i++){
                tagNames+=faStorageBinTag[i].name;
                if(i+1!=faStorageBinTag.length){
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
    url:  '/shelves/multipleShelves',
    method: 'post',
    data
  })
}