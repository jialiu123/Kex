<template>
	<div>
		<Card style="margin-bottom: 10px;">
			<Tabs :value="languageTypeList[0].index" v-if="languageTypeList.length >0" >
				
				<TabPane :label="item.showName" :name="item.index" v-for="item in languageTypeList" :key="item.index">
					<dataPane :localeName="item.index"></dataPane>
				</TabPane>
				
				<!-- <TabPane :label="$t('gameInfoManager.modName')" name="data10">
					<div class="message-page-con message-category-con">
						<Menu width="auto" ref="one" :active-name="currentType" @on-select="handleSelect">
						  <MenuItem :name="item.index" v-for="item in languageTypeList" :key="item.index">
							<span class="category-title">{{ item.showName }}</span>
						  </MenuItem>
						</Menu>
					</div>
					<div class="message-page-con message-view-con" >
					  
					</div>
				</TabPane> -->
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
		computed: {},
		methods: {
			getLanguageTypeList() {
				getLanguageTypeList().then(response => {
					const data = response.data;
					this.languageTypeList = data.data;
					// this.currentType = this.languageTypeList[0].index;
					// this.currentTypeTwo = this.languageTypeList[0].index;
					this.$nextTick(()=>{
						// this.$refs.one.updateActiveName();
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
