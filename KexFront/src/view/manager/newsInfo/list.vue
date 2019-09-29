<template>
	<div>
		<Card style="margin-bottom: 10px;">
			<Tabs value="data10000000">
				<TabPane :label="$t('newsInfoManager.data10000000')" name="data10000000">
					<div class="message-page-con message-category-con">
						<Menu width="auto" ref="one" :active-name="currentType" @on-select="handleSelect">
						  <MenuItem :name="item.index" v-for="item in languageTypeList" :key="item.index">
							<span class="category-title">{{ item.showName }}</span>
						  </MenuItem>
						</Menu>
					</div>
					<div class="message-page-con message-view-con" v-for="item in languageTypeList" :key="item.index" v-show="item.index == currentType">
					  <dataPane modId="10000000" :localeName="item.index"></dataPane>
					</div>
				</TabPane>
				<TabPane :label="$t('newsInfoManager.data20000000')" name="data20000000">
					<div class="message-page-con message-category-con">
						<Menu width="auto" ref="two" :active-name="currentTypeTwo" @on-select="handleSelectTwo">
						  <MenuItem :name="item.index" v-for="item in languageTypeList" :key="item.index">
							<span class="category-title">{{ item.showName }}</span>
						  </MenuItem>
						</Menu>
					</div>
					<div class="message-page-con message-view-con" v-for="item in languageTypeList" :key="item.index" v-show="item.index == currentTypeTwo">
					  <dataPane modId="20000000" :localeName="item.index"></dataPane>
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
				// access:this.$store.state.user.access,
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
						this.$refs.two.updateActiveName();
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
