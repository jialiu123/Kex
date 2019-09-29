import axios from '@/libs/api.request'
import QS from 'qs'
/**
 * list
 */
const baseUrl = "/newsInfo/";
export function list(entity) {
	return axios.request({
		url: baseUrl + 'index/list?' + QS.stringify(entity),
		method: 'get'
	});
}
/**
 * getList
 */
export function getList(entity) {
	return axios.request({
		url: baseUrl + 'index/getList?' + QS.stringify(entity),
		method: 'get'
	});
}
/**
 * save
 */
export function save(entity) {
	return axios.request({
		url: baseUrl + 'save',
		method: 'post',
		data:JSON.parse(JSON.stringify(entity))
	});
}
/**
 * 获取单个信息
 */
export function getEntityByUuid(uuid) {
	return axios.request({
		url: baseUrl +"index/" + uuid,
		method: 'get'
	});
}


/**
 * del
 */
export function del(id) {
	return axios.request({
		url: baseUrl +'delete?id='+ id,
		method: 'POST'
	});
}

export function delAll(pks) {
	console.info("pks:"+pks)
	return axios.request({
		url: baseUrl + 'delete?pks='+pks,
		method: 'POST'
	});
}
