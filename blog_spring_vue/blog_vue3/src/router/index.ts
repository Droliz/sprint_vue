import {
    createRouter,   // 创建路由
    createWebHashHistory,    // 路由模式  hash
    createWebHistory,  // history 模式
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
                        component: () => import("@/views/home/views/BlogList.vue")
                    },
                    {
                        path: "blogPage/:id",
                        name: "blogPage",
                        component: () => import("@/views/home/views/BlogContent.vue"),
                    },
                    {
                        path: 'drinkTea',
                        name: 'drinkTea',
                        component: () => import("@/views/home/views/DrinkTea.vue")
                    },
                    {
                        path: 'blogByTime',
                        name: 'blogByTime',
                        component: () => import("@/views/home/views/BlogByTime.vue")
                    },

                ]
            }
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router


