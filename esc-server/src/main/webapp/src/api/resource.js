import request from '@/utils/request'


export function getResource(data) {
    return request({
        url: '/resource/' + data,
        method: 'get'
    })
}

export function deleteResource(data) {
    return request({
        url: '/resource/' + data,
        method: 'delete'
    })
}

export function addResource(data) {
    return request({
        url: '/resource/create',
        method: 'post',
        data
    })
}

export function addRoleResource(data) {
    return request({
        url: '/resource/createRoleResource',
        method: 'post',
        data
    })
}

export function deleteRoleResource(data) {
    return request({
        url: '/resource/deleteRoleResource',
        method: 'delete',
        data
    })
}

export function findResource(data) {
    return request({
        url: '/resource/find',
        method: 'post',
        data
    })
}

export function list(data) {
    return request({
        url: '/resource/list',
        method: 'post',
        data
    })
}

export function page(data) {
    return request({
        url: '/resource/page',
        method: 'post',
        data
    })
}

export function pageRoleResource(data) {
    return request({
        url: '/resource/pageRoleResource',
        method: 'post',
        data
    })
}

export function updateResource(data) {
    return request({
        url: '/resource/update',
        method: 'put',
        data
    })
}

export function getResourceByParentId(id) {
    return request({
        url: '/resource/getResourceByParentId/'+ id +'',
        method: 'get'
    })
}

export function getSecondLevelResource() {
    return request({
        url: '/resource/getSecondLevelResource',
        method: 'get'
    })
}
