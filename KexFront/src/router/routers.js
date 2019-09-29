import Main from '@/components/main'
import parentView from '@/components/parent-view'

/**
 * iview-admin中meta除了原生参数外可配置的参数:
 * meta: {
 *  title: { String|Number|Function }
 *         显示在侧边栏、面包屑和标签栏的文字
 *         使用'{{ 多语言字段 }}'形式结合多语言使用，例子看多语言的路由配置;
 *         可以传入一个回调函数，参数是当前路由对象，例子看动态路由和带参路由
 *  hideInBread: (false) 设为true后此级路由将不会出现在面包屑中，示例看QQ群路由配置
 *  hideInMenu: (false) 设为true后在左侧菜单不会显示该页面选项
 *  notCache: (false) 设为true后页面在切换标签后不会缓存，如果需要缓存，无需设置这个字段，而且需要设置页面组件name属性和路由配置的name一致
 *  access: (null) 可访问该页面的权限数组，当前路由设置的权限会影响子路由
 *  icon: (-) 该页面在左侧菜单、面包屑和标签导航处显示的图标，如果是自定义图标，需要在图标名称前加下划线'_'
 *  beforeCloseName: (-) 设置该字段，则在关闭当前tab页时会去'@/router/before-close.js'里寻找该字段名对应的方法，作为关闭前的钩子函数
 * }
 */

export default [
  {
    path: '/login',
    name: 'login',
    meta: {
      title: 'Login - 登录',
      hideInMenu: true
    },
    component: () => import('@/view/login/login.vue')
  },
	{
	  path: '/index',
	  name: 'index',
	  meta: {
	    title: '首页',
			hideInMenu: true,
	  },
		component: () => import('@/view/front/index/index.vue')
	},
	{
	  path: '/indexNews/:modId',
	  name: 'indexNews',
	  meta: {
	    title: '新闻',
			hideInMenu: true,
	  },
		component: () => import('@/view/front/news/index.vue')
	},
	{
	   path: '/indexNews/:modId/:uuid',
	   name: 'indexNewsDetail',
	   meta: {
	    title: '新闻详情',
	   	hideInMenu: true,
	   },
	   component: () => import('@/view/front/news/detail.vue')
	},
	{
	  path: '/indexComm',
	  name: 'indexComm',
	  meta: {
	    title: '商品',
			hideInMenu: true,
	  },
		component: () => import('@/view/front/comm/list.vue')
	},
	{
	   path: '/indexComm/:id',
	   name: 'indexCommDetail',
	   meta: {
	    title: '商品详情',
	   	hideInMenu: true,
	   },
	   component: () => import('@/view/front/comm/detail.vue')
	},
	{
	  path: '/indexHelp',
	  name: 'indexHelp',
	  meta: {
	    title: '帮助中心',
			hideInMenu: true,
	  },
		component: () => import('@/view/front/help/index.vue')
	},
	{
	  path: '/indexHelp/:modId/:uuid',
	  name: 'indexHelp',
	  meta: {
	    title: '帮助中心',
			hideInMenu: true,
	  },
		component: () => import('@/view/front/help/index.vue')
	},
  {
    path: '/',
    name: '_home',
    redirect: '/home',
    component: Main,
    meta: {
      hideInMenu: true,
      notCache: true
    },
    children: [
      {
        path: '/home',
        name: 'home',
        meta: {
          hideInMenu: true,
          title: '首页',
          notCache: true,
          icon: 'md-home'
        },
        component: () => import('@/view/single-page/home')
      }
    ]
  },
  {
    path: '/sysettingPage',
    name: 'sysettingPage',
    meta: {
      icon: 'md-settings',
      title: '系统管理',
			access:[1]
    },
    component: Main,
    children: [
      {
        path: 'userPage',
        name: 'userPage',
        meta: {
          icon: 'md-person',
          title: '用户管理',
					access:[1]
        },
        component: () => import('@/view/manager/userInfo/list.vue')
      },
      {
        path: 'rolePage',
        name: 'rolePage',
        meta: {
          icon: 'logo-buffer',
          title: '角色管理',
					access:[1]
        },
        component: () => import('@/view/manager/role/list.vue')
      },
			{
			  path: 'baseDataPage',
			  name: 'baseDataPage',
			  meta: {
			    icon: 'ios-cog',
			    title: '基础数据',
					access:[1]
			  },
			  component: () => import('@/view/manager/baseData/list.vue')
			},
			{
			  path: 'gameInfoPage',
			  name: 'gameInfoPage',
			  meta: {
			    icon: 'md-game-controller-b',
			    title: '游戏管理',
					access:[1]
			  },
			  component: () => import('@/view/manager/gameInfo/list.vue')
			},
			{
			  path: 'baseClassPage',
			  name: 'baseClassPage',
			  meta: {
			    icon: 'md-list-box',
			    title: '分类管理',
					access:[1]
			  },
			  component: () => import('@/view/manager/baseClass/list.vue')
			},
			{
			  path: 'helpInfoPage',
			  name: 'helpInfoPage',
			  meta: {
			    icon: 'md-list-box',
			    title: '帮助中心',
					access:[1]
			  },
			  component: () => import('@/view/manager/helpInfo/list.vue')
			}
    ]
  },
	{
	  path: '/business',
	  name: 'businessPage',
	  meta: {
	    icon: 'md-apps',
	    title: '业务管理',
			access:[1]
	  },
	  component: Main,
	  children: [
	    {
	      path: 'commInfoPage',
	      name: 'commInfoPage',
	      meta: {
	        icon: 'ios-basket',
	        title: '商品管理',
					access:[1]
	      },
	      component: () => import('@/view/manager/commInfo/list.vue')
	    },
			{
			  path: 'newsInfoPage',
			  name: 'newsInfoPage',
			  meta: {
			    icon: 'ios-book',
			    title: '新闻管理',
					access:[1]
			  },
			  component: () => import('@/view/manager/newsInfo/list.vue')
			},
			{
			  path: 'orderInfoPage',
			  name: 'orderInfoPage',
			  meta: {
			    icon: 'md-reorder',
			    title: '订单管理',
					access:[1,2,3]
			  },
			  component: () => import('@/view/manager/orderInfo/list.vue')
			}
	  ]
	},
	{
	  path: '/auditPage',
	  name: 'auditPage',
	  meta: {
	    icon: 'md-apps',
	    title: '审核管理',
			access:[1]
	  },
	  component: Main,
	  children: [
			{
			  path: 'userMoneyPage',
			  name: 'userMoneyPage',
			  meta: {
			    icon: 'md-reorder',
			    title: '提现管理',
					access:[1,2,3]
			  },
			  component: () => import('@/view/manager/userMoney/list.vue')
			},
			{
			  path: 'entpInfoPage',
			  name: 'entpInfoPage',
			  meta: {
			    icon: 'ios-archive',
			    title: '店铺管理',
					access:[1,3]
			  },
			  component: () => import('@/view/manager/entpInfo/list.vue')
			},
	  ]
	},
  {
    path: '/error_store',
    name: 'error_store',
    meta: {
      hideInBread: true,
			hideInMenu: true
    },
    component: Main,
    children: [
      {
        path: 'error_store_page',
        name: 'error_store_page',
        meta: {
          icon: 'ios-bug',
          title: '错误收集'
        },
        component: () => import('@/view/error-store/error-store.vue')
      }
    ]
  },
  {
    path: '/error_logger',
    name: 'error_logger',
    meta: {
      hideInBread: true,
      hideInMenu: true
    },
    component: Main,
    children: [
      {
        path: 'error_logger_page',
        name: 'error_logger_page',
        meta: {
          icon: 'ios-bug',
          title: '错误收集'
        },
        component: () => import('@/view/single-page/error-logger.vue')
      }
    ]
  },
  {
    path: '/401',
    name: 'error_401',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/401.vue')
  },
  {
    path: '/500',
    name: 'error_500',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/500.vue')
  },
  {
    path: '*',
    name: 'error_404',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/404.vue')
  },
	
	{
	  path: '/message',
	  name: 'message',
	  component: Main,
	  meta: {
	    hideInBread: true,
	    hideInMenu: true
	  },
	  children: [
	    {
	      path: 'messagePage',
	      name: 'messagePage',
	      meta: {
	        icon: 'md-notifications',
	        title: '消息中心'
	      },
	      component: () => import('@/view/manager/msg/list.vue')
	    }
	  ]
	},
	
	{
	  path: '/apply_entp',
	  name: 'apply_entp',
	  component: Main,
	  meta: {
	    hideInBread: true,
	    hideInMenu: true
	  },
	  children: [
	    {
	      path: 'apply_entp_page',
	      name: 'apply_entp_page',
	      meta: {
	        icon: 'md-notifications',
	        title: '商家入驻申请'
	      },
	      component: () => import('@/view/manager/entpInfo/applyEntp.vue')
	    }
	  ]
	},
]
