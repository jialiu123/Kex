<template>
	<div>
		<Card style="margin-bottom: 10px;">
			<Tabs value="data20">
				<TabPane :label="$t('baseDataManager.userLevel')" name="data20">
					<baseDataPane typeValue="20"></baseDataPane>
				</TabPane>
				<TabPane :label="$t('baseDataManager.serviceCharge')" name="data30">
					<baseDataPane typeValue="30"></baseDataPane>
				</TabPane>
				<TabPane :label="$t('baseDataManager.gamePlatform')" name="data40">
					<div class="message-page-con message-category-con">
						<Menu width="auto" ref="one" :active-name="currentType" @on-select="handleSelect">
						  <MenuItem :name="item.index" v-for="item in languageTypeList" :key="item.index">
							<span class="category-title">{{ item.showName }}</span>
						  </MenuItem>
						</Menu>
					</div>
					<div class="message-page-con message-view-con" v-for="item in languageTypeList" :key="item.index" v-show="item.index == currentType">
					  <baseDataPane typeValue="40" :localeName="item.index"></baseDataPane>
					</div>
				</TabPane>
			</Tabs>
		</Card>
	</div>
</template>

<script>
	import {getLanguageTypeList} from '@/api/emums';
	import baseDataPane from './components/base-data-pane'
	import {formatDate} from '@/libs/tools';
	import {mapGetters} from 'vuex';
	export default {
		components: {
		  baseDataPane
		},
		data() {
			return {
				languageTypeList: [],
				currentType:'',
			};
		},
		mounted: function() {
			this.getLanguageTypeList();
		},
		computed: {
		},
		methods: {
			getLanguageTypeList() {
				getLanguageTypeList().then(response => {
					const data = response.data;
					this.languageTypeList = data.data;
					this.currentType = this.languageTypeList[0].index;
					this.$nextTick(()=>{
						this.$refs.one.updateActiveName();
					})
				}).catch(error => {
					
				});
			},
			handleSelect (index) {
			  this.currentType = index
			},
		}
	};
</script>
<style lang="less">
.ivu-tabs-tabpane{
	position: relative;
}
</style>
