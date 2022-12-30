import VueRouter from "vue-router";
import Vue from "vue";

Vue.use(VueRouter)

const routes = [
    {
        path: "/",
        name: "index",
        redirect: "/login"
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/Login/LoginView')
    },
    // matches /p/books
    {
        path: '/home',
        name: 'home',
        component: () => import('@/views/IndexView'),
        redirect: "/home/main",
        children: [
            {
                path: 'main',
                name: 'main',
                component: () => import('@/views/Home/HomeView')
            },
            {
                path: 'admin',
                name: 'admin',
                component: () => import('@/views/Admin/AdminView')
            },
            {
                path: 'user',
                name: 'user',
                component: () => import('@/views/UserInfo/UserInfoView')
            },
            {
                path: 'warehouse',
                name: 'warehouse',
                component: () => import('@/views/Warehouse/WarehouseView')
            }
        ]
    },
    {
        path: '*',
        name: 'NotFound',
        component: () => import('@/views/NotFount/IndexView')
    }
]


const router = new VueRouter({
    // mode: 'history',
    routes,
    strict: true, // applies to all routes
})

router.beforeEach((to, from, next) => {
    // 处理访问权限
    const token = localStorage.getItem('token')
    if (to.path === '/login') {
        if (token) {
            next("/home")
        }
        next()
    }
    else {
        if (token) {
            next()
        }
        else {
            next('/login')
        }
    }
})

export default router