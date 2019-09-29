import axios from '@/libs/api.request'
import QS from 'qs'
/**
 * list
 */
const baseUrl = "/baseClass/";
export function list(entity) {
	return axios.request({
		url: baseUrl + 'list?' + QS.stringify(entity),
		method: 'get'
	});
}
export function treeList(entity) {
	return axios.request({
		url: baseUrl + 'treeList?' + QS.stringify(entity),
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
export function getEntityById(id) {
	return axios.request({
		url: baseUrl + id,
		method: 'get'
	});
}
/**
 * del
 */
export function del(id) {
	return axios.request({
		url: baseUrl +'delete?clsId='+ id,
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
