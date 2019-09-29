import axios from '@/libs/api.request'

export const login = ({ userName, password }) => {
  const data={
			username:userName,
			password:password
		}
  return axios.request({
    url: '/auth/login',
    data,
    method: 'post'
  })
}

export const getUserInfo = (token) => {
  return axios.request({
    url: '/auth/getUserByToken',
    params: {
      token
    },
    method: 'get'
  })
}

export const logout = () => {
  return axios.request({
    url: '/auth/remove',
    method: 'get'
  })
}

/**
 * register 用户注册
 */
export function registerUser(entity) {
	console.info(entity)
	return axios.request({
		url: '/auth/register',
		method: 'post',
		data:JSON.parse(JSON.stringify(entity))
	});
}


