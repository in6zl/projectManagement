import request from '@/utils/request'
export const get = () => request({
  method: 'get',
  url: '/systemSetting/get'
})

export const save = (setting) => request({
  data: setting,
  url: '/systemSetting/save',
  method: 'post'
})