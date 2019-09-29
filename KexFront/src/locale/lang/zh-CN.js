import role from '@/view/manager/role/local'
import baseData from '@/view/manager/baseData/local'
import newsInfo from '@/view/manager/newsInfo/local'
import userInfo from '@/view/manager/userInfo/local'
import commInfo from '@/view/manager/commInfo/local'
import gameInfo from '@/view/manager/gameInfo/local'
import baseClass from '@/view/manager/baseClass/local'
import entpInfo from '@/view/manager/entpInfo/local'
import orderInfo from '@/view/manager/orderInfo/local'
import userMoney from '@/view/manager/userMoney/local'
import msg from '@/view/manager/msg/local'
import userBiRecord from '@/view/manager/userBiRecord/local'
import helpInfo from '@/view/manager/helpInfo/local'


//front 
import Register from '@/view/front/local/register'
export default {
	home: '首页',
	login: '登录',
	loginout: "退出登录",
	messagePage:'消息中心',
	sysettingPage: '系统设置',
	auditPage:'审核管理',
	userPage: '用户管理',
	rolePage: '角色管理',
	baseDataPage:'基础数据',
	businessPage:'业务管理',
	commInfoPage:'商品管理',
	newsInfoPage:'新闻管理',
	gameInfoPage:'游戏管理',
	baseClassPage:'分类管理',
	orderInfoPage:'订单管理',
	userMoneyPage:'提现管理',
	entpInfoPage:'店铺管理',
	helpInfoPage:'帮助中心',
	fullscreen: '全屏',
	fullscreenout: '退出全屏',
	tagsView: {
		refresh: '刷新',
		close: '关闭',
		closeOthers: '关闭其它',
		closeAll: '关闭所有'
	},
	button: {
		search: "搜索",
		add: "添加",
		addContent: "编辑内容",
		delSelect: "删除所选",
		edit: "编辑",
		del: '删除',
		save: "保存",
		view:'查看',
		audit: "审核",
		operation: "操作",
		viewImg:"查看图片",
		removeRecycle:'移到回收站'
	},
	messageTip: {
		tip: "提示",
		confirm: "确定执行该操作吗？",
		saveSuccess: "保存成功",
		registerSuccess: "注册成功",
		doSuccess: "操作成功",
		delSuccess: "删除成功",
		selectOne: "至少选择一项",
		frontError: "前端出错",
		paramError:"参数有误"
	},
	validateTip: {
		notNull: "不能为空",
		number: "请输入数字格式",
	},
	roleManager: role.CN,
	baseDataManager: baseData.CN,
	userInfoManager: userInfo.CN,
	newsInfoManager: newsInfo.CN,
	commInfoManager: commInfo.CN,
	gameInfoManager:gameInfo.CN,
	baseClassManager:baseClass.CN,
	entpInfoManager:entpInfo.CN,
	orderInfoManager:orderInfo.CN,
	userMoneyManager:userMoney.CN,
	msgManager:msg.CN,
	userBiRecordManager:userBiRecord.CN,
	RegisterFront:Register.CN,
	helpInfoManager:helpInfo.CN
}
