import axios from '@/libs/api.request'
import QS from 'qs'
/**
 * list
 */
const baseUrl = "/orderInfo/";
export function list(entity) {
	return axios.request({
		url: baseUrl + 'list?' + QS.stringify(entity),
		method: 'get'
	});
}
/**
 * 获取订单详情
 */
export function getOrderDetail(id) {
	return axios.request({
		url: baseUrl + 'getOrderDetail?id=' + id,
		method: 'get'
	});
}

/**
 * 订单操作
 */
export function updateOrder(entity) {
	return axios.request({
		url: baseUrl + 'updateOrder',
		method: 'post',
		data:JSON.parse(JSON.stringify(entity))
	});
}
