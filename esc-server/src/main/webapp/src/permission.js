import router, {constantRoutes} from './router'
import store from './store'
import {Message} from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login']

router.beforeEach(async (to, form, next) => {
  NProgress.start()
  document.title = getPageTitle(to.meta.title)
  if (to.path === '/login' || whiteList.indexOf(to.path) !== -1) {
    next()
    return
  }

  if (!store.getters.token) {
    next(`/login?redirect=${to.path}`)
    return
  }

  // 确定用户是否已通过getInfo获得其权限角色
  let permissions = store.getters.permissions

  if (permissions && permissions.length > 0) {
    next()
  } else {
    try {
      // 调用store action 获取用户信息中的permissions
      let accessRouters = await store.dispatch('user/getInfo')

      store.commit('app/SET_ROUTERS', constantRoutes.concat(accessRouters))

      router.addRoutes(accessRouters)

      next({ ...to, replace: true })
    } catch (error) {
      // remove token and go to login page to re-login
      await store.dispatch('user/resetToken')
      Message.error(error || 'Has Error')
      next(`/login?redirect=${to.path}`)
    }
  }
})

router.afterEach(() => {
  NProgress.done()
})

// 用来控制按钮的显示
export function haveBtn(permission) {
  let permissions = store.getters.permissions
  return permissions.indexOf(permission) !== -1
}
