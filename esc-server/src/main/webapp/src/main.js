import Vue from 'vue'
import 'normalize.css/normalize.css' // A modern alternative to CSS resets
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import i18n from './lang'
import '@/styles/index.scss' // global css
import App from './App'
import store from './store'
import router from './router'

import '@/icons' // icon
import '@/permission' // permission control
/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online ! ! !
 */
//引入echart
import echarts from 'echarts'
import Print from 'vue-print-nb'
import {message} from "@/utils/message";
import {haveBtn} from "@/permission"
//将echarts引入到vue的原型中
Vue.prototype.$echarts = echarts

Vue.use(Print);
// set ElementUI lang to EN
Vue.use(ElementUI, {
  i18n: (key, value) => i18n.t(key, value)
})
Vue.directive("focus", {
  inserted: function (el) {
    el.focus()
  }
});
Vue.prototype.$message = message;
Vue.prototype.haveBtn = haveBtn
// import Router_ from "./router/router"
// Vue.prototype.ROUTER =Router_
// 如果想要中文版 element-ui，按如下方式声明
// Vue.use(ElementUI)
Vue.config.productionTip = false
new Vue({
  el: '#app',
  router,
  store,
  i18n,
  render: h => h(App)
})
