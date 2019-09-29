<template>
	<div style="background-image:url('https://oss.stmbuy.com/static/upload/bannerpic/5b3c7dddb6278.jpg');background-repeat: no-repeat;">
		<header-bar></header-bar>
		<div class="container w-response">
			<div class="main-content">
				<div class="news-article">
					<div class="news-article-tit">
						<router-link :to="{name:'news', params:{modId: modId}}">
							<em>全部资讯</em>
						</router-link>
						<span>
							<em>资讯正文</em>
						</span>
					</div>
					<div class="news-article-main">
						<strong class="news-article-strong">{{data.title}}</strong>
						<div class="news-article-info">
							<span class="news-tag">{{data.infoSource}}</span>
							<p>{{new Date(data.pubTime) | formatDate('yyyy-MM-dd')}}
								<em>浏览次数:{{data.viewCount}}</em>
							</p>
						</div>
						<div class="news-article-detail" v-html="data.newsContent">

						</div>
					</div>
				</div>
			</div>
			<aside-news :dataList="hotList" :modId="modId"></aside-news>
		</div>
		<footer-bar></footer-bar>
		<aside-tools></aside-tools>
		<go-top></go-top>
	</div>
</template>

<script>
	import HeaderBar from "../components/header-bar";
	import FooterBar from "../components/footer-bar";
	import AsideTools from "../components/aside-tools.vue";
	import AsideNews from "./components/aside-news.vue";
	import GoTop from "../components/go-top.vue";
	import {
		getEntityByUuid,
		getList
	} from "@/api/manager/newsInfo";
	import {
		localRead
	} from "@/libs/util";
	export default {
		components: {
			HeaderBar,
			FooterBar,
			AsideTools,
			AsideNews,
			GoTop
		},
		data() {
			return {
				uuid: "",
				modId: "",
				searchFrom: {
					modId: this.modId,
					localeName: localRead("local")
				},
				data: {},
				hotList: []
			};
		},
		watch: {
			'$route'(to, from) { 
				this.$router.go(0);
			}
		},
		mounted: function() {
			this.$nextTick(function() {
				let uuid = this.$route.params.uuid;
				this.modId = this.$route.params.modId;
				this.uuid = uuid;
				this.getData();
			});
		},
		methods: {
			getData() {
				getEntityByUuid(this.uuid).then(response => {
					const data = response.data;
					this.data = data.data;
				}).catch(error => {});
				getList(this.searchFrom).then(response => {
					const data = response.data;
					this.hotList = data.data;
				}).catch(error => {});
			}

		}
	};
</script>
<style>
	@import "../../../assets/css/common.css";
	@import "../../../assets/css/news.css";
</style>
