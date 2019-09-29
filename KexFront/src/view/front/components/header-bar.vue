<template>
	<div>
		<header class="comm-header">
			<div class="w-response">
				<router-link class="logo" to="/index">
					<h1>STMBUY中国电竞饰品交易平台</h1>
				</router-link>
				<button class="leftside-menu-btn fl"><i class="icon icon-leftside-menu"></i></button>
				<div class="tools">
					<div class="unlogin" v-show="!token">
						<button class="to-login" @click="toLogin">登录</button>
						<a class="to-register" @click="registerModal = true">注册</a>
					</div>
					<div class="logined" v-show="token">
						<div class="user dropmenu hover">
							<a class="drop-title" href="/home" title="进入个人中心">
								<img class="user-img" :src="userAvator">
								<input class="user-name" type="text" readonly="readonly" :value="userName">
							</a>
						</div>
					</div>
					<div class="lang">
						<div class="dropmenu" @mouseenter="handleMouseenter" @mouseleave="handleMouseleave">
							<div class="drop-title" title="选择语言">
								<i :class="'icon icon-lang-' + localeName"></i>
								<input type="text" readonly="readonly" unselectable="on">
								<i class="edge"></i>
							</div>
							<dl class="drop-menu" v-show="showItem">
								<dd @click="selectItem('zh-CN')"><a><i class="icon icon-lang-zh-CN"></i><span>简体中文</span></a></dd>
								<dd @click="selectItem('en-US')"><a><i class="icon icon-lang-en-US"></i><span>English</span></a></dd>
							</dl>
						</div>
					</div>
				</div>
			</div>
		</header>
		<div class="comm-nav">
			<nav class="w-response">
				<router-link to="/index" active-class="on">首页</router-link>
				<router-link :to="{name:'indexNews', params:{modId: '10000000'}}" active-class="on">游戏资讯</router-link>
				<router-link to="/indexHelp" active-class="on">帮助中心</router-link>
				<router-link to="/pubg" active-class="on">绝地求生</router-link>
				<router-link to="/csgo" active-class="on">CS:GO</router-link>
				<router-link to="/h1z1" active-class="on">H1Z1</router-link>
				<router-link to="/cdkey" active-class="on">CDK商城 <img src="https://web.stmbuy.com/static/img/hot.gif" alt="hot"></router-link>
				<router-link to="/cdkey/pick" active-class="on">Steam折扣<img src="https://web.stmbuy.com/static/img/new.png" alt="new"></router-link>
				<router-link to="/cdkey/pubg" active-class="on">绝地求生CDK</router-link>
				<router-link to="/subject/apex" active-class="on">Apex专题 </router-link>
			</nav>
		</div>
		<Register :registerModal="registerModal" @cancel="registerCancel"></Register>
	</div>
</template>

<script>
	import Register from "./register";
	import {
		localRead,
		localSave,
		getToken
	} from "@/libs/util";
	export default {
		name: 'HeaderBar',
		components: {
			Register
		},
		data() {
			return {
				token:getToken(),
				showItem: false,
				registerModal: false,
				localeName: localRead("local")
			};
		},
		computed: {
			userAvator () {
			  return this.$store.state.user.avatorImgPath
			},
			userName () {
			  return this.$store.state.user.userName
			},
		},
		mounted: function() {
		},
		watch: {
			localeName(newValue, oldValue) {
				if (newValue) {
					localSave("local", newValue);
					/**
					 * TODO 这个地方可能有问题
					 */
					window.location.reload();
				}
			}
		},
		methods: {
			toLogin() {
				this.$router.push({
					name: "login"
				})
			},
			selectItem(item) {
				this.localeName = item;
				this.showItem = !this.showItem;
			},
			handleMouseenter() {
				if (this.timeout) clearTimeout(this.timeout);
				this.timeout = setTimeout(() => {
					this.showItem = true;
				}, 250);
			},
			handleMouseleave() {
				if (this.timeout) {
					clearTimeout(this.timeout);
					this.timeout = setTimeout(() => {
						this.showItem = false;
					}, 150);
				}
			},
			registerCancel() {
				this.registerModal = false;
			}
		},
	};
</script>
<style lang="less">

</style>
