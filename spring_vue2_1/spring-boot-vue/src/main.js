import Vue from 'vue'
import App from './App.vue'
// import "./plugins/element"
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/css/index.css';   // 全局样式表

import router from "@/router";
import store from '@/store'

Vue.use(ElementUI, {size: 'small'})
Vue.config.productionTip = false

new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
