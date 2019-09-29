<template>
	<div style="background-image:url('https://oss.stmbuy.com/static/upload/bannerpic/5b3c7dddb6278.jpg');background-repeat: no-repeat;">
		<header-bar></header-bar>
		<div class="container w-response">
			<div class="main-content">
				<swiper id="news-slider" :options="swiperOption">
					<swiper-slide class="swiper-wrapper">
						<a href="https://www.stmbuy.com/subject/apex" target="_blank">
							<img src="https://oss.stmbuy.com/static/upload/bannerpic/5bcd9be968cbe.png" alt>
						</a>
					</swiper-slide>
					<swiper-slide class="swiper-wrapper">
						<a href="https://www.stmbuy.com/subject/apex" target="_blank">
							<img src="https://oss.stmbuy.com/static/upload/bannerpic/5bcd9be968cbe.png" alt>
						</a>
					</swiper-slide>
					<swiper-slide class="swiper-wrapper">
						<a href="https://www.stmbuy.com/subject/apex" target="_blank">
							<img src="https://oss.stmbuy.com/static/upload/bannerpic/5bcd9be968cbe.png" alt>
						</a>
					</swiper-slide>
					<div class="swiper-pagination" slot="pagination"></div>
				</swiper>

				<div class="news">
					<div class="new-content" style="margin-top: .2rem;">
						<div class="news-filter">
							<a :class="tabIndex == '0' ? 'on' : ''" @click="orderByGetNews('',0)">默认</a>
							<a :class="tabIndex == '1' ? 'on' : ''" @click="orderByGetNews('pub_time',1)">最新</a>
							<a :class="tabIndex == '2' ? 'on' : ''" @click="orderByGetNews('view_count',2)">浏览最多</a>
						</div>
						<ul class="news-list">
							<li v-for="item in dataList" :key="item.index">
								<router-link :to="{name:'indexNewsDetail', params:{modId:modId,uuid:item.uuid}}">
									<div class="news-img">
										<img :src="item.imagePath">
									</div>
									<div class="news-main">
										<span class="news-tag color-1tm000003">{{item.infoSource}}</span>
										<p class="news-tit">{{item.title}}</p>
										<strong>
											{{new Date(item.pubTime) | formatDate('yyyy-MM-dd')}}
											<span>
												<i class="icon icon-watch"></i>
												<span>{{item.viewCount}}</span>
											</span>
										</strong>
										<p>{{item.summary}}</p>
									</div>
								</router-link>
							</li>
						</ul>
						<div class="pagination">
							<Page :total="dataCount" @on-change="handleChange(curpage)" />
						</div>
					</div>
				</div>
			</div>
			<aside-news :dataList="hotList" :modId="modId"></aside-news>
		</div>
		<footer-bar></footer-bar>
	</div>
</template>

<script>
	import HeaderBar from "../components/header-bar";
	import FooterBar from "../components/footer-bar";
	import AsideNews from "./components/aside-news.vue";
	import {list,getList} from "@/api/manager/newsInfo";
	import "swiper/dist/css/swiper.css";
	import {swiper,swiperSlide} from "vue-awesome-swiper";
	import {localRead,localSave} from "@/libs/util";
	export default {
		components: {
			HeaderBar,
			FooterBar,
			AsideNews,
			swiper,
			swiperSlide
		},
		data() {
			return {
				swiperOption: {
					autoplay: true,
					loop: true,
					pagination: {
						el: ".swiper-pagination"
					}
				},
				searchFrom: {
					page: 1,
					auditState:1,
					localeName: localRead("local")
				},
				modId:'',
				tabIndex: 0,
				dataList: [],
				hotList: [],
				dataCount: 0
			};
		},
		mounted: function() {
			this.$nextTick(function() {
				this.modId = this.$route.params.modId;
				this.searchFrom['modId'] = this.$route.params.modId;
				this.getData();
			});
		},
		methods: {
			getData(page) {
				if (page) {
					this.searchFrom.page = page;
				}
				list(this.searchFrom).then(response => {
					const data = response.data;
					this.dataCount = data.data.dataCount;
					this.dataList = data.data.entityList;
				}).catch(error => {});
				getList(this.searchFrom).then(response => {
					const data = response.data;
					this.hotList = data.data;
				}).catch(error => {});
			},
			orderByGetNews(orderBy, tabIndex) {
				this.searchFrom.orderBy = orderBy;
				this.tabIndex = tabIndex;
				this.getData(1);
			},
			handleChange(curpage) {
				this.getAjaxData(curpage);
			}
		}
	};
</script>
<style>
	@import "../../../assets/css/common.css";
	@import "../../../assets/css/news.css";
</style>
