import {
  login,
  logout,
  getUserInfo,
  removeReaded,
  restoreTrash
} from '@/api/login'
import {getMessageCount,hasRead} from '@/api/manager/msg'
import { setToken, getToken,baseUrl } from '@/libs/util'

export default {
  state: {
    userName: '',
    userId: '',
    avatorImgPath: '',
    token: getToken(),
    access: '',
    hasGetInfo: false,
		readCount:0,
    unReadCount: 0,
		recycleCount:0,
    messageContentStore: {}
  },
  mutations: {
    setAvator (state, avatorPath) {
      state.avatorImgPath = avatorPath
    },
    setUserId (state, id) {
      state.userId = id
    },
    setUserName (state, name) {
      state.userName = name
    },
    setAccess (state, access) {
      state.access = access
    },
    setToken (state, token) {
      state.token = token
      setToken(token)
    },
    setHasGetInfo (state, status) {
      state.hasGetInfo = status
    },
    setMessageUnreadCount (state, count) {
      state.unReadCount = count
    },
		setMessageReadCount (state, count) {
		  state.readCount = count
		},
		setMessageRecycleCount (state, count) {
		  state.recycleCount = count
		},
    moveMsg (state, { from, to, msg_id }) {
      const index = state[from].findIndex(_ => _.msg_id === msg_id)
      const msgItem = state[from].splice(index, 1)[0]
      msgItem.loading = false
      state[to].unshift(msgItem)
    }
  },
  getters: {
    readCount: state => state.readCount,
    unReadCount: state => state.unReadCount,
    recycleCount: state => state.recycleCount
  },
  actions: {
    // 登录
    handleLogin ({ commit }, { userName, password }) {
      userName = userName.trim()
      return new Promise((resolve, reject) => {
        login({
          userName,
          password
        }).then(res => {
          const data = res.data
          commit('setToken', data.data.token)
          resolve()
        }).catch(err => {
          reject(err)
        })
      })
    },
    // 退出登录
    handleLogOut ({ state, commit }) {
      return new Promise((resolve, reject) => {
        logout().then(() => {
          commit('setToken', '')
          commit('setAccess', [])
          resolve()
        }).catch(err => {
          reject(err)
        })
      })
    },
    // 获取用户相关信息
    getUserInfo ({ state, commit }) {
      return new Promise((resolve, reject) => {
        try {
          getUserInfo(state.token).then(res => {
            const data = res.data;
						if (data.code == 200) {
							const userInfo = data.data.userInfo;
							commit('setAvator', baseUrl + userInfo.userLogo);
							commit('setUserName', userInfo.userName);
							commit('setUserId', userInfo.id);
							commit('setAccess', data.data.access);
							commit('setHasGetInfo', true);
							resolve(data)
						}
          }).catch(err => {
            reject(err)
          })
        } catch (error) {
          reject(error)
        }
      })
    },
    // 此方法用来获取未读消息条数，接口只返回数值，不返回消息列表
    getMessageCount ({ state, commit }) {
      getMessageCount(state.userId).then(res => {
        const { data } = res
        commit('setMessageUnreadCount', data.data.unReadCount);
				commit('setMessageReadCount', data.data.readCount);
				commit('setMessageRecycleCount', data.data.recycleCount);
      })
    },
    // 把一个未读消息标记为已读
    hasRead ({ state, commit },entity) {
      return new Promise((resolve, reject) => {
        hasRead(entity).then(() => {
					if(entity.infoState == 2){
						 commit('setMessageUnreadCount', state.unReadCount - 1)
						 commit('setMessageReadCount', state.readCount + 1)
					}else{
						if(entity.oldInfoState == 1){
							commit('setMessageUnreadCount', state.unReadCount - 1)
						}
						if(entity.oldInfoState == 2){
							commit('setMessageReadCount', state.readCount - 1)
						}
						commit('setMessageRecycleCount', state.recycleCount + 1)
					}
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
  }
}
