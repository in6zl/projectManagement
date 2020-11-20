import axios from 'axios'
import { MessageBox } from 'element-ui'
import {message} from "@/utils/message";
import store from '@/store'
import { getToken } from '@/utils/auth'
import router from '../router/index'
import { utils } from "@/components/minxs/utils";
// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent
    if (store.getters.token) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      config.headers['X-Token'] = getToken()
    }
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data
    // if the custom code is not 20000, it is judged as an error.
    if (res.code !== '0' && !res instanceof Blob) {
      utils.methods.throttle((() => {
        message({
          message: res.message || 'Error',
          type: 'error',
        })
      })(), 5000)
      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        // to re-login
        MessageBox.confirm('You have been logged out, you can cancel to stay on this page, or log in again', 'Confirm logout', {
          confirmButtonText: 'Re-Login',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }
  },
  error => {
    if (error.response && error.response.data.code === "10006") {
      message({
        message: "用户名或密码输入错误！",
        type: "error"
      })
      return Promise.reject(error);
    }
    if (error.response.status) {
      switch (error.response.status) {
        case 404:
          message({
            message: '网络请求不存在',
            type: "error"
          })
          break;
        case 500:
          message({
            message: '服务器异常',
            type: "error"
          })
          break
        case 401:
          let currentUser = localStorage.getItem("currentUser")
          if (currentUser) {
            message({
              message: '登录超时,请重新登录',
              type: "error"
            })
            localStorage.removeItem('currentUser')
            localStorage.removeItem('router')
            router.push("/login")
          } else {
            message({
              message: '请登录',
              type: "error"
            })
          }
          break;
        default:
          message({
            message: error.response.data.message,
            type: "error"
          })
      }
    }
    return Promise.reject(error)
  }
)
export default service
