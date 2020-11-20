import request from '@/utils/request'

export function addDischarge(data) {
    return request({
        url: '/discharge',
        method: 'post',
        data
    })
}

export function updateDischarge(data) {
    return request({
        url: '/discharge',
        method: 'put',
        data
    })
}

export function deleteDischarge(data) {
    return request({
        url: '/discharge/'+data,
        method: 'delete'
    })
}

export function getDischarge(data) {
    return request({
        url: '/discharge/dischargeInfo/'+data,
        method: 'get'
    })
}

export function listDischarge(data) {
    return request({
        url: '/discharge/list',
        method: 'POST',
        data
    })
}

export function pageDischarge(data) {
    return request({
        url: '/discharge/page',
        method: 'POST',
        data
    })
}