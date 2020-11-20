import Vue from 'vue'
import Router from 'vue-router'
/* Layout */
import Layout from '@/layout'

Vue.use(Router)

export const viewsMap = {}
viewsMap['Layout'] = Layout
viewsMap['/userManage'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/userManage/userManage')
viewsMap['/editUser'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/userManage/editUser')
viewsMap['/addUser'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/userManage/addUser')
viewsMap['/roleManage'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/roleManage/roleManage')
viewsMap['/editRole'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/roleManage/editRole')
viewsMap['/addRole'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/roleManage/addRole')
viewsMap['/organizationManage'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/organizationManage/organizationManage')
viewsMap['/editOrganization'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/organizationManage/editOrganization')
viewsMap['/addOrganization'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/organizationManage/addOrganization')
viewsMap['/resourceManage'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/resourceManage/resourceManage')
viewsMap['/addResource'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/resourceManage/addResource')
viewsMap['/editResource'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/resourceManage/editResource')
viewsMap['/dictType'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/dict/dictType')
viewsMap['/editDictType'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/dict/editDictType')
viewsMap['/addDictType'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/dict/addDictType')
viewsMap['/dict'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/dict/dict')
viewsMap['/addDict'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/dict/addDict')
viewsMap['/editDict'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/dict/editDict')

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'dashboard', icon: 'home' }
    },
    ]
  },
  // {
  //   path: '*',
  //   redirect: '/404'
  // }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
