import { getCurrentUser, login, logout } from '@/api/user'
import {getToken, removeToken, setToken} from '@/utils/auth'
import { viewsMap, resetRouter } from "@/router";

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    realName: '',
    permissions: []
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_REALNAME: (state, realName) => {
    state.realName = realName
  },
  SET_PERMISSIONS: (state, permissions) => {
    state.permissions = permissions
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      let fd = new FormData();
      fd.append("username", username.trim());
      fd.append("password", password);
      login(fd).then(response => {
        const { data } = response
        commit('SET_TOKEN', data)
        setToken(data)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getCurrentUser().then(response => {
        const { data } = response
        if (!data) {
          reject('Verification failed, please Login again.')
        }

        const { username, realName, resources} = data

        let nres = convert(resources, true)
        commit('SET_NAME', username)
        commit('SET_REALNAME', realName)
        commit('SET_PERMISSIONS', nres.permissions)
        resolve(nres.accessRouters)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      removeToken()
      resetRouter()
      commit('RESET_STATE')

      logout().then(() => {
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}


function convert(resources, isTop = false) {  
  let permissions = []
  let accessRouters = []
  resources.forEach(item => {
    permissions.push(item.permission)
    
    let childrenRouter = []
    if(item.children && item.children.length > 0) {
      let nres = convert(item.children)
      permissions = permissions.concat(nres.permissions)
      childrenRouter = nres.accessRouters
    }

    let router = {
      path: item.url,
      name: item.name,
      hidden: item.type === 1,
      component: isTop? viewsMap['Layout'] : viewsMap[item.url],
      meta: {
        title: item.name,
        icon: item.icon
      }
    }

    accessRouters.push(router)
    if(isTop && childrenRouter.length > 0) {
      router.children = childrenRouter
      router.redirect = 'noRedirect'
    } else if (childrenRouter.length > 0){
      accessRouters = accessRouters.concat(childrenRouter)
    }
    
  });
  return { permissions, accessRouters }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

