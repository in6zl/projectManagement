import request from '@/utils/request'
export function updateWorkshop(data) {
    return request({
        url: '/workshop',
        method: 'put',
        data
    })
}

export function deleteWorkshop(data) {
    return request({
        url: '/workshop/' + data,
        method: 'delete'
    })
}

export function addWorkshop(data) {
    return request({
        url: '/workshop/add',
        method: 'post',
        data
    })
}

export function getWorkshop(data) {
    return request({
        url: '/workshop/getWorkShopInfo/' + data,
        method: 'get'
    })
}

export function listWorkshop(data) {
    return request({
        url: '/workshop/list',
        method: 'post',
        data
    })
}


export function pageWorkshop(data,pageNum,pageSize) {
    return request({
        url: '/workshop/page?pageNum='+pageNum+'&pageSize='+pageSize,
        method: 'post',
        data
    })
} 


export function findByName(data) {
    return request({
        url: '/workshop/multipleWorkShop',
        method: 'post',
        data
    })
} 