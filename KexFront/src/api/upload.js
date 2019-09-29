import axios from '@/libs/api.request'
import QS from 'qs'

export const uploadUrl = "fastdfs/upload/file";
export const upload = formData => {
 return axios.request({
   url: "/" + uploadUrl,
   data: formData,
   method: 'post'
 })
}