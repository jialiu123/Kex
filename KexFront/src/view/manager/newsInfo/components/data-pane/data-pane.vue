<template>
	<div>
		<Form :model="searchFrom" :label-width="80">
			<Card style="margin-bottom: 10px;">
				<Input :placeholder="$t('newsInfoManager.title')" v-model="searchFrom.f_titleLike" style="width:200px"></Input>
				&nbsp;<Button type="primary" @click="getData(1)" icon="ios-search">{{$t('button.search')}}</Button>
			</Card>
		</Form>
		<div style="margin-bottom: 10px;">
			<Button type="primary" @click="add">{{$t('button.add')}}</Button>
			&nbsp;<Button type="error" @click="delAll">{{$t('button.delSelect')}}</Button>
		</div>

		<Table border :data="tableData" :columns="tableColumns" stripe ref="selection" @on-selection-change="handleSelectRow()" />
		<div style="margin:10px 0px;text-align: center;">
			<Page :total="dataCount" :current="searchFrom.page" @on-change="changePage"></Page>
		</div>

		<Modal width="800" v-model="formModal" :title="modalTitle" @on-ok="save" @on-cancel="saveCancel" :loading="loading" :mask-closable="false">
			<Form ref="saveFrom" :model="entity" :label-width="80" :rules="rules">
				<FormItem prop="title" :label="$t('newsInfoManager.title')">
					<Input v-model="entity.title" :placeholder="$t('newsInfoManager.title')" style="width:320px;" :maxlength="100"></Input>
				</FormItem>
				<FormItem prop="titleSub" :label="$t('newsInfoManager.titleSub')">
					<Input v-model="entity.titleSub" :placeholder="$t('newsInfoManager.titleSub')" style="width:320px;" :maxlength="100"></Input>
				</FormItem>
				<FormItem prop="infoSource" :label="$t('newsInfoManager.infoSource')">
					<Input v-model="entity.infoSource" :placeholder="$t('newsInfoManager.infoSource')" style="width:200px;" :maxlength="20"></Input>
				</FormItem>
				<FormItem prop="localeName" :label="$t('newsInfoManager.localeName')">
					<Select v-model="entity.localeName" style="width:200px">
						<Option v-for="item in languageTypeList" :value="item.index" :key="item.index">{{ item.showName }}</Option>
					</Select>
				</FormItem>
				<FormItem prop="imagePath" :label="$t('newsInfoManager.imagePath')">
					<uploadInput :uploadList="uploadList" @setUploadList="setUploadList"></uploadInput>
				</FormItem>
				<FormItem prop="summary" :label="$t('newsInfoManager.summary')">
					<Input type="textarea" :autosize="{minRows: 3,maxRows: 5}" v-model="entity.summary" :placeholder="$t('newsInfoManager.summary')"></Input>
				</FormItem>
				<FormItem prop="newsContent" :label="$t('newsInfoManager.newsContent')">
					<Editor @on-change="getContent" :cache="false" :value="entity.newsContent"></Editor>
				</FormItem>
				<FormItem prop="pubTime" :label="$t('newsInfoManager.pubTime')">
					<DatePicker type="date" :value="new Date(entity.pubTime) | formatDate('yyyy-MM-dd')" @on-change="entity.pubTime=$event" :placeholder="$t('newsInfoManager.pubTime')" style="width: 200px"></DatePicker>
				</FormItem>
				<FormItem prop="orderValue" :label="$t('newsInfoManager.orderValue')">
					<InputNumber :min="0" v-model="entity.orderValue" :placeholder="$t('newsInfoManager.orderValue')"></InputNumber>
				</FormItem>
			</Form>
		</Modal>

	</div>
</template>

<script>
	import {list,save,del,delAll,getEntityByUuid} from '@/api/manager/newsInfo';
	import uploadInput from "@/components/upload/upload";
	import {formatDate} from '@/libs/tools';
	import Editor from '@/components/editor'
	import {mapGetters} from 'vuex';
	export default {
		name: 'dataPane',
		props: {
		  modId: {
			type: String
		  },
		  localeName:{
		  	type:String
		  }
		},
		components: {
		  Editor,
		  uploadInput
		},
		data() {
			return {
				dataCount: 0,
				tableData: [],
				uploadList: [],
				formModal: false,
				modalTitle:"",
				entity: {
					orderValue: 0,
					modId: this.modId,
					localeName: this.localeName
				},
				languageTypeList: [],
				pks: [],
				searchFrom: {
					page: 1,
					modId: this.modId,
					localeName: this.localeName
				},
				loading: true,
			};
		},
		mounted: function() {
			this.getData();
		},
		computed: {
			rules() {
				return {
					title: [{required: true,message: this.$t('newsInfoManager.title') + this.$t('validateTip.notNull')}],
					titleSub: [{required: true,message: this.$t('newsInfoManager.titleSub') + this.$t('validateTip.notNull')}],
					localeName: [{required: true,message: this.$t('newsInfoManager.localeName') + this.$t('validateTip.notNull')}],
					infoSource: [{required: true,message: this.$t('newsInfoManager.infoSource') + this.$t('validateTip.notNull')}],
					summary: [{required: true,message: this.$t('newsInfoManager.summary') + this.$t('validateTip.notNull')}],
					pubTime: [{required: true,message: this.$t('newsInfoManager.pubTime') + this.$t('validateTip.notNull')}],
					orderValue: [{required: true,message:this.$t('newsInfoManager.orderValue') + this.$t('validateTip.notNull')}],
					newsContent: [{required: true,message:this.$t('newsInfoManager.newsContent') + this.$t('validateTip.notNull')}],
				}
			},
			tableColumns() {
				let that = this;
				return [{
						type: 'selection',
						width: 60,
						align: 'center',
					},
					{
						title: this.$t('newsInfoManager.title'),
						align: 'center',
						key: 'title'
					},
					{
						title: this.$t('newsInfoManager.infoSource'),
						align: 'center',
						key: 'infoSource'
					},
					{
						title: this.$t('newsInfoManager.localeName'),
						width: 130,
						align: 'center',
						key: 'localeName',
						render: (h, params) => {
							return h('div',
								that.getLangTypeName(params.row.localeName)
							)
						}
					},
					{
						title: this.$t('newsInfoManager.pubTime'), 
						width: 120,
						align: 'center',
						key: 'pubTime',
						render: (h, params) => {
							return h('div',
								formatDate(new Date(params.row.pubTime), 'yyyy-MM-dd')
							)
						}
					},
					{
						title: this.$t('newsInfoManager.orderValue'),
						width: 120,
						align: 'center',
						key: 'orderValue'
					},
					{
						title: this.$t('button.operation'),
						key: 'action',
						fixed: 'right',
						align: 'center',
						width: 130,
						render: (h, params) => {
							let edit, del;
							edit = h('Button', {
								props: {
									type: 'primary',
									size: 'small'
								},
								style: {
									marginRight: '5px'
								},
								on: {
									click: () => {
										that.modalTitle = this.$t('button.edit');
										this.getEntityByUuid(params.row.uuid);
									}
								}
							}, this.$t('button.edit'));
							del = h('Button', {
								props: {
									type: 'error',
									size: 'small'
								},
								on: {
									click: () => {
										this.$Modal.confirm({
											title: this.$t('messageTip.tip'),
											content: '<p>'+ this.$t('messageTip.confirm') +'</p>',
											onOk: () => {
												this.delete(params.index);
											}
										});
									}
								}
							}, this.$t('button.del'));
							return h('div', [edit, del]);
						}
					}
				]
			}
		},
		methods: {
			getContent(html,text){
				this.entity.newsContent = html;
			},
			getEntityByUuid(uuid){
				let that = this;
				getEntityByUuid(uuid).then(response => {
					const data = response.data;
				    that.entity = data.data;
					that.uploadList.push(that.entity.imagePath);
				    that.formModal = !this.formModal;
				}).catch(error => {});
			},
			getLangTypeName(val) {
				let showName = "";
				for (let i = 0; i < this.languageTypeList.length; i++) {
					if (this.languageTypeList[i].index == val) {
						showName = this.languageTypeList[i].showName;
						break;
					}
				}
				return showName;
			},
			getData(page) {
				if(page){
					this.searchFrom.page = page;
				}
				list(this.searchFrom).then(response => {
					const data = response.data;
					this.dataCount = data.data.dataCount;
					this.tableData = data.data.entityList;
					this.languageTypeList = data.data.languageTypeList
				}).catch(error => {});
			},
			add() {
				this.modalTitle = this.$t('button.add');
				this.uploadList = [];
				this.formModal = !this.formModal;
			},
			delete(index) {
				del(this.tableData[index].id).then(response => {
					this.$Message.success(this.$t('messageTip.delSuccess'));
					//返回到第一页
					this.searchFrom.page = 1;
					this.getData();
				}).catch(error => {
					this.$Message.error(this.$t('messageTip.frontError'));
				});
			},
			delAll() {
				if (this.pks.length == 0) {
					this.$Message.error(this.$t('messageTip.selectOne'));
					return;
				}
				this.$Modal.confirm({
					title: this.$t('messageTip.tip'),
					content: '<p>'+ this.$t('messageTip.confirm') +'</p>',
					onOk: () => {
						delAll(this.pks).then(response => {
							this.$Message.success(this.$t('messageTip.delSuccess'));
							//返回到第一页
							this.searchFrom.page = 1;
							this.getData();
						}).catch(error => {
							this.$Message.error(this.$t('messageTip.frontError'));
						});
					}
				});
			},
			save() {
				let entity = this.entity;
				if(entity.id){
					entity['updateUserId'] = this.$store.state.user.userId;
				}else{
					entity['addUserId'] = this.$store.state.user.userId;
				}
				setTimeout(() => {
					this.loading = false;
					this.$nextTick(() => {
						this.loading = true;
						this.$refs.saveFrom.validate((valid) => {
							if (valid) {
								if(null == entity.newsContent || ''== entity.newsContent){
									this.$Message.error(this.$t('newsInfoManager.newsContent') + this.$t('validateTip.notNull'));
									return;
								}
								save(entity).then(response => {
									this.entity=this.$options.data().entity;
									this.entity.modId= this.modId;
									this.entity.localeName = this.localeName;
									this.getData();
									this.$Message.success(this.$t('messageTip.saveSuccess'));
									this.formModal = false;
								}).catch(error => {
									this.$Message.error(this.$t('messageTip.frontError'));
								});
							}
						})
					});
				}, 500);
			},
			saveCancel(){
				this.entity=this.$options.data().entity;
				this.entity.modId= this.modId;
				this.entity.localeName = this.localeName;
			},
			handleSelectRow(selection) {
				this.pks = [];
				let list = this.$refs.selection.getSelection();
				let that = this;
				list.forEach(function(value, index, array) {
					that.pks.push(value.id)
				})
			},
			changePage(page) {
				this.searchFrom.page = page;
				this.getData();
			},
			setUploadList(list) {
				this.entity.imagePath = list[0].url;
			}
		}
	};
</script>
