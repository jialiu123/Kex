import axios from '@/libs/api.request'
import QS from 'qs'
/**
 * list
 */
const baseUrl = "/userBiRecord/";
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