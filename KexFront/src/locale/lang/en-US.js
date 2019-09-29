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
	home: 'home',
	login: 'Sign in',
	loginout: "Sign out",
	messagePage:'message store',
	sysettingPage: 'System setup',
	auditPage:'audit management',
	userPage: 'user management',
	rolePage: 'Role management',
	baseDataPage:'Basic data',
	businessPage:'Business management',
	commInfoPage:'Commodity management',
	newsInfoPage:'News management',
	gameInfoPage:'game management',
	baseClassPage:'classification management',
	orderInfoPage:'order management',
	userMoneyPage:'Cash withdrawal management',
	entpInfoPage:'entpInfo management',
	helpInfoPage:'helpInfo center',
	fullscreen: 'full screen',
	fullscreenout: 'Exit full screen',
	tagsView: {
		refresh: 'Refresh',
		close: 'Close',
		closeOthers: 'Close Others',
		closeAll: 'Close All'
	},
	button: {
		search: "Search",
		add: "Add",
		addContent: "add Content",
		delSelect: "Delete Choose",
		edit: "Edit",
		del: 'Delete',
		view:'view',
		save: "save",
		audit: "audit",
		operation: "Operation",
		viewImg:"View Image",
		removeRecycle:'remove Recycle'
	},
	messageTip: {
		tip: "tip",
		confirm: "Are you sure you want to do this?",
		saveSuccess: "Save successfully",
		doSuccess: "do successfully",
		registerSuccess: "register successfully",
		delSuccess: "Delete successful",
		selectOne: "Choose at least one",
		frontError: "Front Error",
		paramError:"params Error"
	},
	validateTip: {
		notNull: " not null",
		number: "Please enter a digital format",
	},
	roleManager: role.EN,
	baseDataManager:baseData.EN,
	userInfoManager:userInfo.EN,
	newsInfoManager:newsInfo.EN,
	commInfoManager:commInfo.EN,
	gameInfoManager:gameInfo.EN,
	baseClassManager:baseClass.EN,
	entpInfoManager:entpInfo.EN,
	orderInfoManager:orderInfo.EN,
	userMoneyManager:userMoney.EN,
	msgManager:msg.EN,
	userBiRecordManager:userBiRecord.EN,
	RegisterFront:Register.EN,
	helpInfoManager:helpInfo.EN
}
