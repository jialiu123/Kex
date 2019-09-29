import axios from '@/libs/api.request'
import QS from 'qs'
/**
 * list
 */
const baseUrl = "/msg/";
export function list(entity) {
	return axios.request({
		url: baseUrl + 'list?' + QS.stringify(entity),
		method: 'get'
	});
}
/**
 * 获取单个信息
 */
export function getEntityById(id) {
	return axios.request({
		url: baseUrl + id,
		method: 'get'
	});
}

/**
 * save
 */
export function hasRead(entity) {
	return axios.request({
		url: baseUrl + 'save',
		method: 'post',
		data:JSON.parse(JSON.stringify(entity))
	});
}

export const getMessageCount = (userId) => {
  return axios.request({
    url: baseUrl + 'getCount?userId=' + userId,
    method: 'get'
  })
}