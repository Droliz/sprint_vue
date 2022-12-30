
export default {
    namespaced: true,
    state: {
        isCollapse: false
    },
    mutations: {
        editCollapse(state) {
            state.isCollapse = !state.isCollapse
            // console.log(state.isCollapse)
        }
    }
}

