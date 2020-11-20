import request from '@/utils/request'

export function addRegistration(data) {
    return request({
        url: '/registration',
        method: 'post',
        data
    })
}

export function updateRegistration(data) {
    return request({
        url: '/registration',
        method: 'put',
        data
    })
}

export function deleteRegistration(data) {
    return request({
        url: '/registration/'+data,
        method: 'delete'
    })
}

export function registartionList(data) {
    return request({
        url: '/registration/list',
        method: 'post',
        data
    })
}

export function pageRegistartion(data,pageNum,pageSize) {
    return request({
        url: '/registration/page?pageNum='+pageNum+"&pageSize="+pageSize,
        method: 'post',
        data
    })
}

export function getRegistrationInfo(data) {
    return request({
        url: '/registration/registrationInfo/'+data,
        method: 'get'
    })
}

export function enterPark(data) {
    return request({
      url: '/registration/'+data,
      method: 'put'
    })
  }

  export function exitPark(data) {
    return request({
      url: '/registration/out/'+data,
      method: 'put'
    })
  }

  export function getRegistrationState(data) {
    return request({
        url: '/registration/registrationState',
        method: 'get'
    })
}
  