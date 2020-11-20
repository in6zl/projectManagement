const getters = {
  sidebar: state => state.app.sidebar,
  token: state => state.user.token,
  realName: state => state.user.realName,
  name: state => state.user.name,
  device: state => state.app.device,
  permissions: state => state.user.permissions,
  routers: state => state.app.routers
}
export default getters
