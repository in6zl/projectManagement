import request from '@/utils/request'

export function addApplication(data) {
    return request({
      url: '/productDeliveryRequisition/add',
      method: 'post',
      data
    })
  }

export function pageApplication(data) {
    return request({
        url: '/productDeliveryRequisition/page',
        method: 'post',
        data
    })
}

export function getApplication(data) {
    return request({
        url: '/productDeliveryRequisition/'+data,
        method: 'get'
    })
}

export function updateApplication(data) {
    return request({
        url: '/productDeliveryRequisition/update',
        method: 'put',
        data
    })
}

export function deleteApplication(data) {
    return request({
      url: '/productDeliveryRequisition/'+data,
      method: 'delete',
    })
  }

export function getApplicationDetails(applicationId) {
    return request({
        url: '/productDeliveryRequisition/productDeliveryRequisitionDetail/'+applicationId,
        method: 'get',
    })
}