import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/system/login',
    method: 'post',
    data,
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
  }
  })
}

export const logout = () => request({
  url: '/system/logout',
  method: 'get'
})

export function getCurrentUser() {
  return request({
    url: '/user/currentUser',
    method: 'get'
  })
}

export function getUserList(data) {
  return request({
    url: '/user/list',
    method: 'post',
    data
  })
}
export function page(data) {
  return request({
    url: '/user/page',
    method: 'post',
    data
  })
}
export function createUser(data) {
  return request({
    url: 'user/create',
    method: 'post',
    data
  })
}
export function deleteUser(data) {
  return request({
    url: 'user/'+data,
    method: 'delete',
  })
}
export function updateUser(data) {
  return request({
    url: 'user/update',
    method: 'put',
    data
  })
}
export function findUser(data) {
  return request({
    url: 'user/'+data,
    method: 'get',
  })
}
export function searchUser(data) {
  return request({
    url: '/user/page',
    method: 'post',
    data
  })
}