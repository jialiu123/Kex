import axios from '@/libs/api.request'
import QS from 'qs'
/**
 * list
 */
const baseUrl = "/tGameInfo/";
const baseSonUrl = "/tGameServers/";
export function list(entity) {
	return axios.request({
		url: baseUrl + 'list?' + QS.stringify(entity),
		method: 'get'
	});
}

export function getGameServerList(gameId) {
	return axios.request({
		url: baseUrl + 'getGameServerList?gameId=' + gameId,
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


export function listSon(entity) {
	return axios.request({
		url: baseSonUrl + 'list?' + QS.stringify(entity),
		method: 'get'
	});
}

export function saveSon(entity) {
	return axios.request({
		url: baseSonUrl + 'save',
		method: 'post',
		data:JSON.parse(JSON.stringify(entity))
	});
}

export function delSon(id) {
	return axios.request({
		url: baseSonUrl +'delete?id='+ id,
		method: 'POST'
	});
}

export function delAllSon(pks) {
	console.info("pks:"+pks)
	return axios.request({
		url: baseSonUrl + 'delete?pks='+pks,
		method: 'POST'
	});
}
