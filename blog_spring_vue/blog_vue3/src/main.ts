import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import VMdEditor from '@/utils/markdown/mdEdit'
import router from '@/router/index'
import registerDirectives from "@/utils/directives";
import './assets/css/markdown/custom_light_01.css'
import VueLazyLoad from 'vue-lazyload';   // 图片懒加载

const app = createApp(App)

// 全局指令
registerDirectives(app)

app.use(router)
    .use(VMdEditor)
    .use(VueLazyLoad)
    .mount('#app')

