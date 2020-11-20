import request from '@/utils/request'
export function updateProductBom(data) {
    return request({
        url: '/productBom',
        method: 'put',
        data
    })
}

export function deleteProductBom(data) {
    return request({
        url: '/productBom/'+data,
        method: 'delete'
    })
}

export function addProductBom(data) {
    return request({
        url: '/productBom/add',
        method: 'post',
        data
    })
}

export function getProductBomInfo(data) {
    return request({
        url: '/productBom/getProductBomInfo/'+data,
        method: 'get',
        data
    })
}

export function list(data) {
    return request({
        url: '/productBom/list',
        method: 'post',
        data
    })
}

export function pageProductBom(data) {
    return request({
        url: '/productBom/page',
        method: 'post',
        data
    })
}

export function materialList(){
    return request({
        url:'/material/find-list',
        method:'get'
    })
}