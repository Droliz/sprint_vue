import {
    createRouter,   // 创建路由
    createWebHashHistory,    // 路由模式
    RouteRecordRaw    // route 的类型
} from "vue-router";

const routes: RouteRecordRaw[] = [
    {
        path: "/",
        name: "main",
        component: () => import("@/views/MainView.vue"),
        redirect: "/home",
        children: [
            {
                path: "home",
                name: "home",
                component: () => import("@/views/home/HomeView.vue"),
                children: [
                    {
                        path: "blogList",
                        name: "blogList",
                        component: () => import("@/views/home/components/BlogList.vue")
                    }
                ]
            }
        ]
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router


