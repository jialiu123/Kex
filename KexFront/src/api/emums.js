import axios from '@/libs/api.request'

const baseUrl = "/emums/"; 
export const getLanguageTypeList = () => {
  return axios.request({
    url: baseUrl +'getLanguageTypeList',
    method: 'get'
  })
}
