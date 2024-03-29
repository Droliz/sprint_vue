import axios from 'axios'
// @ts-ignore
import JSONbig from 'json-bigint'

const api = axios.create({
    baseURL: 'http://localhost:9999',    // 请求的公共地址部分
    timeout: 3000,   // 请求超时时间 当请求时间超过5秒还未取得结果时 提示用户请求超时
    transformResponse: [function (data) {
        return JSONbig.parse(data)
    }]
})

// interceptors axios的拦截器对象
api.interceptors.request.use(config => {
    // config 请求的信息
    return config   // 将配置完成的config对象返回出去 如果不返回 请求则不会进行
}, err => {
    // 请求发生错误时的处理 抛出错误
    return Promise.reject(err)
})

api.interceptors.response.use(res => {
    // 我们一般在这里处理，请求成功后的错误状态码 例如状态码是500，404，403
    // res 是所有相应的信息
    return res.data
}, err => {
    // 服务器响应发生错误时的处理
    return Promise.reject(err)
})

export default api