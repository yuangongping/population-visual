import axios from 'axios'
import { Message } from 'element-ui'
// 白云
// const baseURL = process.env.NODE_ENV == 'development' ? 'http://172.16.13.47:8088' : 'http://172.16.6.23:30120'
// 单位
const baseURL = process.env.NODE_ENV == 'development' ? 'http://127.0.0.1:7000' : 'http://10.1.119.34:30120'
// 单位外网
// const baseURL = process.env.NODE_ENV == 'development' ? 'http://117.187.239.196:80' : 'http://117.187.239.196:80'

// 创建axios实例
const http = axios.create({
    baseURL: baseURL,
    timeout: 15000, // 请求超时时间
    headers: { 'Content-Type': 'application/json' },
    withCredentials: true // 跨域session
})

// respone拦截器
http.interceptors.response.use(
    response => {
        /**
         * code为非20000是抛错 可结合自己业务进行修改
         */
        if (response.data.code !== 200) {
            Message.error({
                message: response.message
            })
            console.log('ERROR ' + response) // for debug
        } else {
            return response.data
        }
    },
    error => {
        Message.error({
            message: error.response.data.message
        })
        return Promise.reject(error)
    }
)

export default http;
