<template>
	<div style="background-image:url('https://oss.stmbuy.com/static/upload/bannerpic/5b3c6fe5c590e.jpg');background-repeat: no-repeat;">
		<header-bar></header-bar>
		<div class="container w-response">
			<div class="nav">
				<div class="main-nav">
					<div class="appid dropmenu">
						<dl class="drop-menu" style="display: none;"> 
							<dd><a href="/csgo"><i class="icon icon-csgo-sm"></i> <span>DOTA2</span></a></dd>
							<dd><a href="/csgo"><i class="icon icon-csgo-sm"></i> <span>CS:GO</span></a></dd>
							<dd><a href="/h1z1"><i class="icon icon-h1z1-sm"></i> <span>H1Z1</span></a></dd>
							<dd><a href="/pubg"><i class="icon icon-pubg-sm"></i> <span>绝地求生</span></a></dd>
						</dl>
						<div class="drop-title">
							<i class="icon icon-dota2-sm"></i>
							<input type="text" placeholder="请选游戏" value="DOTA2" readonly="readonly" unselectable="on">
							<i class="edge"></i>
						</div>
					</div>
					<a href="/dota2">全部饰品</a>
					<span class="filter-tags"><a class="filter-keyword" data-key="力量"><span>撼地者</span><i class="icon icon-cha-light"></i></a></span>
				</div>
			</div>
			<!-- 筛选条件 -->
			<ul class="filter-nav unselect cf">
			    <drop-down :menuList="menuList1"></drop-down>
				<drop-down :menuList="menuList1"></drop-down>
				<drop-down :menuList="menuList1"></drop-down>
			</ul>
			<div class="main-content">
				<ul class="filter-nav special cf">
					<drop-down :menuList="menuList"></drop-down>
				</ul>
				<ul class="goods-list">
					<li v-for="item in dataList" :key="item.id">
						<router-link :to="{name:'commDetail', params:{id:item.id}}">
							<div class="goods-item">
								<div class="goods-img">
									<img :src="item.mainPic" />
									<div class="special-icon">
									</div>
								</div>
								<p class="tit" style="color: #4D7455;">{{item.commName}}</p>
								<p class="sec-tit">销售量：<span>{{item.saleCount}}</span></p>
								<i class="icon icon-dota2-sm"></i>
							</div>
							<div class="goods-bottom">
								<p><span>销售价</span><span>¥<strong>{{item.salePrice}}</strong></span></p>
							</div>
						</router-link>
					</li>
				</ul>
				<div class="pagination">
					<Page :total="dataCount" @on-change="handleChange(curpage)" />
				</div>
			</div>
			<!--aside列表-->
			<aside-comm :dataList="hotList"></aside-comm>
		</div>
		<footer-bar></footer-bar>
	</div>
</template>

<script>
	import HeaderBar from '../components/header-bar'
	import FooterBar from '../components/footer-bar'
	import AsideComm from './components/aside-comm.vue'
	import DropDown from '../components/drop-down.vue'
	import {list,getList} from '@/api/manager/commInfo';
	import {localRead} from "@/libs/util";
	export default {
		components: {
			HeaderBar,
			FooterBar,
			AsideComm,
			DropDown
		},
		data() {
			return {
				dataList: [],
				hotList:[],
				dataCount: 0,
				searchFrom: {
					page:1,
					count:12,
					localeName: localRead("local")
				},
				menuList1:[
					{
						"name":"稀有度",
						"value":1
					},
					{
						"name":"至宝",
						"value":2
					},
					{
						"name":"稀有",
						"value":3
					},
					{
						"name":"普通",
						"value":4
					},
				],
				menuList:[
					{
						"name":"默认排序",
						"value":1
					},
					{
						"name":"价格最高",
						"value":2
					},
				]
			};
		},
		mounted: function() {
			this.$nextTick(function() {
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
			handleChange(curpage) {
				this.getAjaxData(curpage);
			}
		}
	};
</script>
<style>
	@import '../../../assets/css/common.css';
	@import '../../../assets/css/comm.css';
</style>
