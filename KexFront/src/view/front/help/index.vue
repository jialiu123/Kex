<template>
	<div style="background-image:url('https://oss.stmbuy.com/static/upload/bannerpic/5b3c7dddb6278.jpg');background-repeat: no-repeat;">
		<header-bar></header-bar>
		<div class="container w-response">
			<aside-nav :dataList="dataList" @getPageData="getPageData"></aside-nav>
			<div class="help-content">
				<h2 class="center">{{item.modName}}</h2>
				<div class="message-contents tab-contents" v-show="item.modType == 0">
					<table cellpadding="0" cellspacing="0">
						<tbody>
							<tr v-for="(item,index) in newsList" :key="item.index">
								<td align="left"><a @click="getContent(item,index)">{{item.title}}</a></td>
								<td class="time" align="right">{{new Date(item.addDate) | formatDate('yyyy-MM-dd hh:mm:ss')}}</td>
							</tr>
						</tbody>
					</table>
					<div class="pagination">
						<Page :total="dataCount" @on-change="handleChange(curpage)" />
					</div>
				</div>
				<div class="message-contents tab-contents" v-show="item.modType == 1">
					{{content}}
				</div>
			</div>
		</div>
		<footer-bar></footer-bar>
	</div>
</template>

<script>
	import HeaderBar from "../components/header-bar";
	import FooterBar from "../components/footer-bar";
	import AsideNav from "./components/aside-nav.vue";
	import {indexHelpInfoList} from "@/api/manager/helpModule";
	import {list} from "@/api/manager/helpInfo";
	import {localRead,localSave} from "@/libs/util";
	export default {
		components: {
			HeaderBar,
			FooterBar,
			AsideNav
		},
		data() {
			return {
				searchFrom: {
					localeName: localRead("local")
				},
				dataList: [],
				newsList: [],
				content:'',
				item:{},
				dataCount: 0,
			};
		},
		mounted: function() {
			this.$nextTick(function() {
				this.getData();
			});
		},
		methods: {
			getData(page) {
				indexHelpInfoList(this.searchFrom).then(response => {
					const data = response.data;
					this.dataList = data.data;
				}).catch(error => {});
			},
			getPageData(item){
				this.item = item;
				this.newsList = [];
				this.searchFrom.modId = item.hmodId;
				list(this.searchFrom).then(response => {
					const data = response.data;
					this.dataCount = data.data.dataCount;
					this.newsList = data.data.entityList;
					if(item.modType == 1 && this.newsList.length > 0){
						this.content = this.newsList[0].content;
					}
				}).catch(error => {});
			},
			getContent(item,index){
				this.item.modName = item.title;
				this.item.modType = 1;
				this.content = this.newsList[index].content;
			},
			handleChange(curpage) {
				this.getPageData();
			}
		}
	};
</script>
<style>
	@import "../../../assets/css/common.css";
	@import "../../../assets/css/help.css";
</style>
