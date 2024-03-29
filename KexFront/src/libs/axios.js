import axios from 'axios'
import {Notice, Message} from 'iview';
import {getToken,localRead} from './util';
import store from '@/store'
// import { Spin } from 'iview'
const addErrorLog = errorInfo => {
  const { statusText, status, request: { responseURL } } = errorInfo
  let info = {
    type: 'ajax',
    code: status,
    mes: statusText,
    url: responseURL
  }
  if (!responseURL.includes('save_error_logger')) store.dispatch('addErrorLog', info)
}

class HttpRequest {
  constructor (baseUrl = baseURL) {
    this.baseUrl = baseUrl
    this.queue = {}
  }
  getInsideConfig () {
    let config = {
      baseURL: this.baseUrl,
      headers: {
        //
      }
    }
		if (getToken()) {
		    // 让每个请求携带自定义token 请根据实际情况自行修改
		    config.headers['Authorization'] = 'Bearer ' + getToken();
		}
// 		if (localRead('local')) {
// 		    // 让每个请求携带自定义token 请根据实际情况自行修改
// 		    config.headers['Authorization'] = 'Bearer ' + getToken();
// 		}
		
    return config
  }
  destroy (url) {
    delete this.queue[url]
    if (!Object.keys(this.queue).length) {
      // Spin.hide()
    }
  }
  interceptors (instance, url) {
    // 请求拦截
    instance.interceptors.request.use(config => {
      // 添加全局的loading...
      if (!Object.keys(this.queue).length) {
        // Spin.show() // 不建议开启，因为界面不友好
      }
      this.queue[url] = true
      return config
    }, error => {
      return Promise.reject(error)
    })
    // 响应拦截
    instance.interceptors.response.use(res => {
      this.destroy(url)
      const { data, status } = res
			if(res.data.code != 200){
				 errorMessage(res.data.msg);
				 return Promise.reject(res.data);
			}
      return { data, status }
    }, error => {
      this.destroy(url)
      let errorInfo = error.response
      if (!errorInfo) {
        const { request: { statusText, status }, config } = JSON.parse(JSON.stringify(error))
        errorInfo = {
          statusText,
          status,
          request: { responseURL: config.url }
        }
      }
      // addErrorLog(errorInfo)
      return Promise.reject(error)
    })
  }
  request (options) {
    const instance = axios.create()
    options = Object.assign(this.getInsideConfig(), options)
    this.interceptors(instance, options.url)
    return instance(options)
  }
}

export function errorMessage (msg) {
    Notice.error({
        title: '错误提示',
        desc: msg,
        duration: 6
    });
}
export function successMessage (msg) {
    Message.success(msg);
}

export default HttpRequest
