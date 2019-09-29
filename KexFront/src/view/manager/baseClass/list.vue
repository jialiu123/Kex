<template>
	<div>
		<Card style="margin-bottom: 10px;">
			<Tabs value="data10">
				<TabPane :label="$t('baseClassManager.clsScope10')" name="data10">
					<div class="message-page-con message-category-con">
						<Menu width="auto" ref="one" :active-name="currentType" @on-select="handleSelect">
						  <MenuItem :name="item.index" v-for="item in languageTypeList" :key="item.index">
							<span class="category-title">{{ item.showName }}</span>
						  </MenuItem>
						</Menu>
					</div>
					<div class="message-page-con message-view-con" v-for="item in languageTypeList" :key="item.index" v-show="item.index == currentType">
					  <dataPane clsScope="10" :localeName="item.index"></dataPane>
					</div>
				</TabPane>
				<TabPane :label="$t('baseClassManager.clsScope0')" name="data0">
					<div class="message-page-con message-category-con">
						<Menu width="auto" ref="two" :active-name="currentTypeTwo" @on-select="handleSelectTwo">
						  <MenuItem :name="item.index" v-for="item in languageTypeList" :key="item.index">
							<span class="category-title">{{ item.showName }}</span>
						  </MenuItem>
						</Menu>
					</div>
					<div class="message-page-con message-view-con" v-for="item in languageTypeList" :key="item.index" v-show="item.index == currentTypeTwo">
					  <dataPane clsScope="0" :localeName="item.index"></dataPane>
					</div>
				</TabPane>
			</Tabs>
		</Card>
	</div>
	
</template>

<script>
	import {getLanguageTypeList} from '@/api/emums';
	import dataPane from './components/data-pane'
	export default {
		components: {
		  dataPane
		},
		data() {
			return {
				languageTypeList: [],
				currentType:'',
				currentTypeTwo:''
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
					this.currentTypeTwo = this.languageTypeList[0].index;
					this.$nextTick(()=>{
						this.$refs.one.updateActiveName();
						this.$refs.two.updateActiveName()
					})
				}).catch(error => {
					
				});
			},
			handleSelect (index) {
			  this.currentType = index
			},
			handleSelectTwo(index){
				this.currentTypeTwo = index
			}
		}
	};
</script>
<style lang="less">
.ivu-tabs-tabpane{
	position: relative;
}
</style>

