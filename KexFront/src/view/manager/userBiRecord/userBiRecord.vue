<template>
	<div>
		<Table border :data="tableData" :columns="tableColumns" stripe ref="selection"/>
		<div style="margin:10px 0px;text-align: center;">
			<Page :total="dataCount" :current="searchFrom.page" @on-change="changePage"></Page>
		</div>
	</div>
</template>

<script>
	import {list} from "@/api/manager/userBiRecord";
	import {formatDate} from "@/libs/tools";
	export default {
		name: 'userBiRecord',
		props: {
		  addUserId: {
			type: Number,
			default: null
		  },
		  biType:{
			type:Number	
		  }		
		},
		data() {
			return {
				dataCount: 0,
				tableData: [],
				biGetTypeList:[],
				searchFrom: {
					page: 1,
					biType:this.biType
				},
			};
		},
		mounted: function() {
		},
		watch: {
		  addUserId:function(value) {
			if (value) {
			  this.searchFrom["addUserId"] = value; 
			  this.getData();
			}
		  }
		},
		computed: {
			tableColumns() {
				let that = this;
				return [{
					    title:this.$t('userBiRecordManager.tableIndex'),
						type: "index",
						width: 60,
						align: "center"
					},
					{
						title: this.$t("userBiRecordManager.biGetType"),
						align: "center",
						key: "biGetType",
						render: (h, params) => {
							let showName = "";
							for (let i = 0; i < this.biGetTypeList.length; i++) {
								if (this.biGetTypeList[i].index == params.row.biGetType) {
									showName = this.biGetTypeList[i].showName;
									break;
								}
							}
							return h('div',showName);
						}
					},
					{
						title: this.$t("userBiRecordManager.biNoBefore"),
						align: "center",
						key: "biNoBefore"
						
					},
					{
						title: this.$t("userBiRecordManager.biNo"),
						align: "center",
						key: "biNo",
						render: (h, params) => {
							let addOrPlus = "md-arrow-up";
							let color = "success";
							if(params.row.biChuOrRu == -1){
								addOrPlus = "md-arrow-down";
								color="error";
							}
							return h('Tag',{
								 props: {
									color:color
								 },
								},[
									h('Icon',{
										 props: {
											type:addOrPlus
										 },
									}),
									h('span',{
										style: {
										    marginLeft: '3px'
										},
									},params.row.biNo),
								],
							);
						}
					},
					{
						title: this.$t("userBiRecordManager.biNoAfter"),
						align: "center",
						key: "biNoAfter"
					},
					{
						title: this.$t("userBiRecordManager.addDate"),
						width: 120,
						align: "center",
						key: "addDate",
						render: (h, params) => {
							return h(
								"div",
								formatDate(new Date(params.row.addDate), "yyyy-MM-dd")
							);
						}
					},
				];
			}
		},
		methods: {
			getData(page) {
				if (page) {
					this.searchFrom.page = page;
				}
				list(this.searchFrom).then(response => {
						const data = response.data;
						this.dataCount = data.data.dataCount;
						this.tableData = data.data.entityList;
						this.biGetTypeList = data.data.biGetTypeList;
				}).catch(error => {});
			},
			changePage(page) {
				this.searchFrom.page = page;
				this.getData();
			},
		}
	};
</script>

<style>
	
</style>
