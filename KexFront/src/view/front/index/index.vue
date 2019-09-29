<template>
	<div style="background-image:url('https://oss.stmbuy.com/static/upload/bannerpic/5b3c6aeb7a9db.jpg');background-repeat: no-repeat;">
		<header-bar></header-bar>
		<div class="container">
			<div class="banner w-response">
				<slide-nav></slide-nav>
				<swiper id="banner-slider" :options="swiperOption">
					<swiper-slide class="swiper-wrapper">
						<a href="https://www.stmbuy.com/subject/apex" target="_blank">
							<img src="https://oss.stmbuy.com/static/upload/bannerpic/5c80e3d754688.jpg" alt="">
						</a>
					</swiper-slide>
					<swiper-slide class="swiper-wrapper">
						<a href="https://www.stmbuy.com/subject/apex" target="_blank">
							<img src="https://oss.stmbuy.com/static/upload/bannerpic/5c779a5e97f6b.jpg" alt="">
						</a>
					</swiper-slide>
					<swiper-slide class="swiper-wrapper">
						<a href="https://www.stmbuy.com/subject/apex" target="_blank">
							<img src="https://oss.stmbuy.com/static/upload/bannerpic/5c77a04076445.jpg" alt="">
						</a>
					</swiper-slide>
					<div class="swiper-pagination" slot="pagination"></div>
				</swiper>
				<aside>
					<h2><i class="icon icon-fire"></i>人气推荐</h2>
					<a class="goods-item" href="https://www.stmbuy.com/cdkey/item-1w9000001">
						<div class="goods-img">
							<img src="https://oss.stmbuy.com/static/upload/bannerpic/5bdb281d34684.png" alt="买浴袍送丛林+飞行员">
						</div>
						<p class="tit">买浴袍送丛林+飞行员</p>
					</a>
					<div class="notice-box">
						<h3>公告栏
							<a href="/notice" target="_blank" class="fr">更多</a>
						</h3>
						<ul>
							<li><a href="/notice/system/22o0000us" target="_blank" title="新春福袋活动下线通知">新春福袋活动下线通知</a></li>
							<li><a href="/notice/system/22h0000nc" target="_blank" title="鬼泣5火热预购开启">鬼泣5火热预购开启</a></li>
							<li><a href="/notice/system/22b0000rg" target="_blank" title="微信支付暂停服务公告">微信支付暂停服务公告</a></li>
						</ul>
					</div>
				</aside>
			</div>
			<div class="floors w-response">
				<floor-list-one></floor-list-one>
				<!--每日推荐-->
				<floor-list-two :dataList="dataTwoList" title="awdawd" subTitle="adaw"></floor-list-two>
				<!--最新上架-->
				<floor-list-two :dataList="dataThreeList" title="awdawd" subTitle="adaw" :isShowLeft="false"></floor-list-two>
				<!--成交记录-->
				<div class="floor trade-history" style="padding-bottom: 0;">
					<floor-list-four></floor-list-four>	
					<floor-list-five></floor-list-five>
				</div>
				<!--游戏资讯-->
				<floor-list-six></floor-list-six>
			</div>
		</div>
		<footer-bar></footer-bar>
	</div>
</template>

<script>
	import HeaderBar from '../components/header-bar.vue'
	import FooterBar from '../components/footer-bar.vue'
	import SlideNav from '../components/slide-nav.vue'
	import FloorListOne from '../components/floor-list-one.vue'
	import FloorListTwo from '../components/floor-list-two.vue'
	import FloorListFour from '../components/floor-list-four.vue'
	import FloorListFive from '../components/floor-list-five.vue'
	import FloorListSix from '../components/floor-list-six.vue'
	import 'swiper/dist/css/swiper.css'
	import {
		swiper,
		swiperSlide
	} from 'vue-awesome-swiper'
	export default {
		components: {
			HeaderBar,
			FooterBar,
			SlideNav,
			FloorListOne,
			FloorListTwo,
			FloorListFour,
			FloorListFive,
			FloorListSix,
			swiper,
			swiperSlide
		},
		data() {
			return {
				dataTwoList:["1","2"],
				dataThreeList:["1","2"],
				swiperOption: {
					autoplay: true,
					loop: true,
					pagination: {
						el: '.swiper-pagination'
					}
				}
			};
		},
		mounted: function() {
		},
		methods: {
			getData() {
				
			}
		}
	};
</script>
<style>
	@import '../../../assets/css/common.css';
</style>
