import request from '@/utils/request'
export function getSellList(data) {
    return request({
      url: '/sale/list?'+data,
      method: 'post',
    })
  }

  export function searchSell(data) {
    return request({
      url: '/sale/list',
      method: 'post',
      data
    })
  }
  export function createSellOrder(data) {
    return request({
      url: '/sale/create',
      method: 'post',
      data
    })
  }
  export function deleteSellOrder(data) {
    return request({
      url: '/sale/delete/'+data,
      method: 'delete',
    })
  }
  export function updateSellOrder(data) {
    return request({
      url: '/sale/update',
      method: 'post',
      data
    })
  }
  export function find(data) {
    return request({
      url: '/sale/'+data,
      method: 'get',
    })
  }
  export function selectSellParams() {
    return request({
      url: '/sale/getSaleItems',
      method: 'get',
    })
  }
  export function getSaleNo() {
    return request({
      url: "/sale/getNo",
      method: 'get',
    })
  }
  export function getDeliverData(data) {
    return request({
      url: "deliveryPlan/deliveryPlanList",
      method: 'post',
      data
    })
  }

  
  export function getByName(data) {
    return request({
      url:  '/sale/multipleSale',
      method: 'post',
      data
    })
  }

  export function isUpdate(data) {
    return request({
      url: "/sale/isUpdate/"+data,
      method: 'get',
    })
  }

  
  export function saleList(data) {
    return request({
      url: '/sale/salelist',
      method: 'post',
      data
    })
  }