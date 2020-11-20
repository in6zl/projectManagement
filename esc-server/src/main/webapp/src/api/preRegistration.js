import request from '@/utils/request'

export function pagePreRegistration(data) {
    return request({
        url: '/preRegistration/page',
        method: 'post',
        data
    })
}

export function preRegistrationList() {
    return request({
        url: '/preRegistration/list',
        method: 'post',
    })
}