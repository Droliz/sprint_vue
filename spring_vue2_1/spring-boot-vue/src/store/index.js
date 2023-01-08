import Vue from 'vue'
import Vuex from 'vuex'
import aside from './modules/asideData'
import login from './modules/loginData'

Vue.use(Vuex)

const store = new Vuex.Store({
    modules: {
        aside,
        login
    }
})

export default store