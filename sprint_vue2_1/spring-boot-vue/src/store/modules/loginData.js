
export default {
    namespaced: true,
    state: {
        userInfo: {
            id: 0,
            no: "",
            name: "",
            age: -1,
            sex: -1,
            phone: "",
            isvalid: "",
            roleId: -1,
            prefsession: -1
        }
    },
    mutations: {
        // 登录
        login(state, value) {
            Object.keys(state.userInfo)
                .forEach(i => state.userInfo[i] = value[i])
        }
    }
}

