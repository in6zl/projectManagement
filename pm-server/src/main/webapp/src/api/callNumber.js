import request from '@/utils/request'

export function pageCallNumber(data,pageNum,pageSize,type) {
    return request({
      url: '/callNumber/page?pageNum='+pageNum+'&pageSize='+pageSize+'&type='+type,
      method: 'post',
      data
    })
  }

  export function call(data) {
    return request({
      url: '/callNumber/'+data,
      method: 'put'
    })
  }

  export function enterOrExitStorage(deliveryId,registrationState,orderState) {
    return request({
      url: '/callNumber/inStorage/'+deliveryId+'/'+registrationState+'/'+orderState,
      method: 'put'
    })
  }

  export function goodTaken(data) {
    return request({
      url: '/callNumber/goodTaken',
      method: 'put',
      data
    })
  }

  export function discharge(data) {
    return request({
      url: '/callNumber/discharge',
      method: 'put',
      data
    })
  }

  export function reject(data) {
    return request({
      url: '/callNumber/reject',
      method: 'put',
      data
    })
  }