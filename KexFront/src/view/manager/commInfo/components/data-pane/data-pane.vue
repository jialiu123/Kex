<template>
	<div>
		<Form :model="searchFrom" :label-width="80">
			<Card style="margin-bottom: 10px;">
				<Input :placeholder="$t('commInfoManager.commName')" v-model="searchFrom.f_commNameLike" style="width:200px"></Input>
				&nbsp;
				<Select :placeholder="$t('commInfoManager.clsId')" filterable v-model="searchFrom.clsId" style="width:200px">
					<Option value="">请选择</Option>
					<Option v-for="item in baseClassList" :value="item.clsId" :key="item.clsId">{{ item.clsName }}</Option>
				</Select>
				&nbsp;
				<Select :placeholder="$t('commInfoManager.gameInfoId')" filterable v-model="searchFrom.gameInfoId" style="width:200px" @on-change="getGameServerList($event)">
					<Option value="">请选择</Option>
					<Option v-for="item in gameInfoList" :value="item.id" :key="item.id">{{ item.gameName }}</Option>
				</Select>
				
				<Select :placeholder="$t('commInfoManager.gameServersIds')" filterable v-model="searchFrom.f_gameServersIdsLike"  style="width:200px">
					<Option value="">请选择</Option>
					<Option v-for="item in gameServersList" :value="item.id" :key="item.id">{{ item.gameServerName }}</Option>
				</Select>
				
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
	
		<Modal v-model="formModal" width="50%" :title="modalTitle" @on-ok="save" @on-cancel="saveCancel" :loading="loading" :mask-closable="false">
			<Form ref="saveFrom" :model="entity" :label-width="90" :rules="rules">
				<FormItem prop="commName" :label="$t('commInfoManager.commName')">
					<Input v-model="entity.commName" :placeholder="$t('commInfoManager.commName')" style="width:400px;" :maxlength="100"></Input>
				</FormItem>
				
				<FormItem prop="clsId" :label="$t('commInfoManager.clsId')">
					
					<Cascader style="width:300px;" v-model="thisClsId" :data="baseClassesListChoose" trigger="hover" change-on-select></Cascader>
					
					<!-- <Select filterable v-model="entity.clsId" style="width:200px">
						<Option v-for="item in baseClassList" :value="item.clsId" :key="item.clsId">{{ item.clsName }}</Option>
					</Select> -->
				</FormItem>
				
				<FormItem prop="gameInfoId" :label="$t('commInfoManager.gameInfoId')">
					<Select filterable v-model="entity.gameInfoId" style="width:200px" @on-change="getGameServerList($event)">
						<Option v-for="item in gameInfoList" :value="item.id" :key="item.id">{{ item.gameName }}</Option>
					</Select>
				</FormItem>
				
				<!-- multiple -->
				<FormItem prop="gameServersIds" :label="$t('commInfoManager.gameServersIds')">
					<Select multiple v-model="thisServersIds"  style="width:200px">
						<Option v-for="item in gameServersList" :value="item.id" :key="item.id">{{ item.gameServerName }}</Option>
					</Select>
				</FormItem>
				
				<FormItem prop="subTitle" :label="$t('commInfoManager.subTitle')">
					<Input v-model="entity.subTitle" :placeholder="$t('commInfoManager.subTitle')" style="width:400px;" :maxlength="100"></Input>
				</FormItem>
				
				<FormItem prop="keyWord" :label="$t('commInfoManager.keyWord')">
					<Input v-model="entity.keyWord" :placeholder="$t('commInfoManager.keyWord')" style="width:400px;" :maxlength="100"></Input>
				</FormItem>
				
				<FormItem prop="mainPic" :label="$t('commInfoManager.mainPic')">
					<uploadInput ref="uploadInputRef" :uploadList="uploadList"
					 @setUploadList='setMainPic'></uploadInput>
				</FormItem>
				
				<FormItem prop="commInfoImgList" :label="$t('commInfoManager.commInfoImgList')">
					<uploadInput ref="uploadInputRefList" :uploadFileCount="4" :uploadList="commInfoImgList"
					 @setUploadList='setCommInfoImgList'></uploadInput>
				</FormItem>
				
				<FormItem prop="content" :label="$t('commInfoManager.content')">
					<Editor @on-change="getContent" :cache="false"></Editor>
				</FormItem>
				
				<FormItem prop="isSell" :label="$t('commInfoManager.isSell')">
					<Select v-model="entity.isSell" style="width:200px" @on-change="isSellChange($event)">
						<Option  value="1" key="1">是</Option>
						<Option  value="0" key="0">否</Option>
					</Select>
				</FormItem>
				
				<FormItem prop="upDate" v-if="entity.isSell == 1" :label="$t('commInfoManager.upDate')">
					<DatePicker type="date" :value="new Date(entity.upDate) | formatDate('yyyy-MM-dd')" @on-change="entity.upDate=$event" :placeholder="$t('commInfoManager.upDate')" style="width: 200px"></DatePicker>
				</FormItem>
				
				<FormItem prop="downDate" v-if="entity.isSell == 1" :label="$t('commInfoManager.downDate')">
					<DatePicker type="date" :value="new Date(entity.downDate) | formatDate('yyyy-MM-dd')" @on-change="entity.downDate=$event" :placeholder="$t('commInfoManager.downDate')" style="width: 200px"></DatePicker>
				</FormItem>
	
				<FormItem prop="orderValue" :label="$t('commInfoManager.orderValue')">
					<Input v-model="entity.orderValue" :placeholder="$t('commInfoManager.orderValue')" style="width:200px;" :maxlength="3"></Input>
				</FormItem>
			</Form>
		</Modal>
		
		<commTczh ref="commTczhModalRef"></commTczh>
	
	</div>
</template>
<script>
	import {formatDate,log} from '@/libs/tools';
	import {list,save,del,delAll,editTczh,getEntityById} from '@/api/manager/commInfo';
	import {getGameServerList} from '@/api/manager/gameInfo';
	import {mapGetters} from 'vuex';
	import {upload} from '@/api/upload';
	import uploadInput from '@/components/upload/upload';
	import Editor from '@/components/editor';
	import commTczh from './commTczh'
	export default {
		components: {
		  uploadInput,
		  Editor,
		  commTczh
		},
		name: 'dataPane',
		props: {
			localeName: {
				type: String
			}
		},
		data() {
			return {
				commTczhModal:false,
				dataCount: 0,
				tableData: [],
				formModal: false,
				modalTitle:"",
				entity: {
					commType:10,
					orderValue: 0,
					localeName:this.localeName,
				},
				pks: [],
				searchFrom: {
					page: 1,
					localeName:this.localeName,
				},
				loading: true,
				imgUrl: '',
				visible: false,
				uploadList: [],
				commInfoImgList:[],
				auditStateList:[],
				gameInfoList:[],
				baseClassList:[],
				baseClassesListChoose:[],
				gameServersList:[],
				thisServersIds:[],
				thisClsId:[]
			}
		},
		mounted: function() {
			this.getData();
		},
		computed: {
			rules() {
				return {
					commName: [{required: true,message: this.$t('commInfoManager.commName') + this.$t('validateTip.notNull')}],
					thisClsId: [{required: true,message: this.$t('commInfoManager.clsId') + this.$t('validateTip.notNull')}],
					gameInfoId: [{required: true,message: this.$t('commInfoManager.gameInfoId') + this.$t('validateTip.notNull')}],
					// gameServersIds: [{required: true,message: this.$t('commInfoManager.gameServersIds') + this.$t('validateTip.notNull')}],
					gameInfoId: [{required: true,message: this.$t('commInfoManager.gameInfoId') + this.$t('validateTip.notNull')}],
					mainPic: [{required: true,message: this.$t('commInfoManager.mainPic') + this.$t('validateTip.notNull')}],
					commInfoImgList: [{required: true,message: this.$t('commInfoManager.commInfoImgList') + this.$t('validateTip.notNull')}],
					isSell: [{required: true,message: this.$t('commInfoManager.isSell') + this.$t('validateTip.notNull')}],
					
				}
			},
			tableColumns() {
				let that = this;
				return [{type: 'selection',width: 60,align: 'center',},
					{title: this.$t('commInfoManager.commName'),align: 'center',key: 'commName'},
					{
						title: this.$t('commInfoManager.mainPic'),
						align: 'center',
						key: 'mainPic',
						render: (h, params) => {
							return h('div', {
								attrs: {
									style: 'height: 120px;'
								},
							}, [
							h('img', {
								props: {
									type: 'primary',
									size: 'small'
								},
								attrs: {
									src: params.row.mainPic,
									style:'width:100px;height: 100px;'
								},
								style: {
								},
							}),
							]);
						}
					},
					{
						title: this.$t('commInfoManager.clsId'),
						align: 'center',
						key: 'clsId',
						render: (h, params) => {
							let showName = "";
							let list  =  this.baseClassList;
							for (let i = 0; i < list.length; i++) {
								if (list[i].clsId == params.row.clsId) {
									showName = list[i].clsName;
									break;
								}
							}
							return h('div',showName);
						}
					},
					{
						title: this.$t('commInfoManager.gameInfoId'),
						align: 'center',
						key: 'gameInfoId',
						render: (h, params) => {
							let showName = "";
							let list  =  this.gameInfoList;
							for (let i = 0; i < list.length; i++) {
								if (list[i].id == params.row.gameInfoId) {
									showName = list[i].gameName;
									break;
								}
							}
							return h('div',showName);
						}
					},
					{
						title: this.$t('commInfoManager.addUserName'),
						align: 'center',
						key: 'addUserName'
					},
					{
						title: this.$t('commInfoManager.addDate'),
						width: 120,
						align: 'center',
						key: 'addDate',
						render: (h, params) => {
							return h('div',
								formatDate(new Date(params.row.addDate), 'yyyy-MM-dd')
							)
						}
					},
					{
						title: this.$t('commInfoManager.auditState'),
						align: 'center',
						key: 'auditState',
						render: (h, params) => {
							let showName = "";
							for (let i = 0; i < this.auditStateList.length; i++) {
								if (this.auditStateList[i].index == params.row.auditState) {
									showName = this.auditStateList[i].name;
									break;
								}
							}
							return h('div',showName);
						}
					},
					{
						title: this.$t('button.operation'),
						key: 'action',
						fixed: 'right',
						align: 'center',
						width: 180,
						render: (h, params) => {
							let edit, del,addTczh;
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
										that.edit(that.tableData[params.index]);
									}
								}
							}, this.$t('button.edit'));
							del = h('Button', {
								props: {
									type: 'error',
									size: 'small'
								},
								style: {
									marginRight: '5px'
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
							addTczh = h('Button', {
								props: {
									type: 'primary',
									size: 'small'
								},
								style: {
									marginTop: '5px'
								},
								on: {
									click: () => {
										that.modalTitle = this.$t('commInfoManager.addTczh');
										that.commTczhModal = true;
										that.chanageCommTczhModal(this.tableData[params.index].id);
										
									},
								}
							}, this.$t('commInfoManager.addTczh'));
							return h('div', [edit, del,addTczh]);
						}
					}
				]
			},
		},
		methods: {
			edit(thisEntity){
				this.formModal = !this.formModal;
				
				getEntityById(thisEntity.id).then(response => {
					const data = response.data;
					this.modalTitle = this.$t('button.edit');
					this.entity = Object.assign({}, data.data);
					let file = {
						url: this.entity.mainPic,
						name: this.entity.mainPic
					}
					this.uploadList = [file];
					this.parentMethod(this.uploadList);
					this.thisClsId = [thisEntity.clsId];
					if(null != this.entity.gameInfoId){
						this.getGameServerList(this.entity.gameInfoId)
					}
					let newlist = [];
					data.data.commInfoImgList.forEach(function(value, index, array) {
						let thisFile = {
							url: value.path,
							name: value.id
						}
						newlist.push(thisFile);
					})
					this.commInfoImgList = newlist;
					this.parToSonSetImgList(newlist);
					
					
				}).catch(error => {});
			},
			chanageCommTczhModal(id){
				this.$refs.commTczhModalRef.childMethod(this.commTczhModal,id); 
			},
			getGameServerList(id){
				let that = this;
				getGameServerList(id).then(response => {
					const data = response.data;
					that.gameServersList = data.data.tGameServersList;
					if(null != that.entity.gameServersIds){
						setTimeout(function(){
							that.thisServersIds = that.entity.map.gameServersIds;
						},1000)
					}
					
				}).catch(error => {});
			},
			isSellChange(event){
				// console.info(event);
			},
			getContent(html,text){
				this.entity.content = html;
			},
			parentMethod(list){
				this.$refs.uploadInputRef.childMethod(list); 
			},
			parToSonSetImgList(list){
				this.$refs.uploadInputRefList.childMethod(list); 
			},
			setMainPic(list){
				this.uploadList = list;
				this.entity.mainPic = this.uploadList[0].url;
			},
			setCommInfoImgList(list){
				this.commInfoImgList = list;
				this.entity.commInfoImgList = this.uploadList;
			},
			getData(page) {
				if (page) {
					this.searchFrom.page = page;
				}
				list(this.searchFrom).then(response => {
					const data = response.data;
					this.dataCount = data.data.dataCount;
					this.tableData = data.data.entityList;
					this.auditStateList = data.data.auditStateList;
					this.baseClassList = data.data.baseClassList;
					this.gameInfoList = data.data.tGameInfoList;
					this.baseClassesListChoose = data.data.baseClassesListChoose;
					
					// this.gameServersList = data.data.gameServersList;
				}).catch(error => {});
			},
			add() {
				this.modalTitle = this.$t('button.add');
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
					content: '<p>' + this.$t('messageTip.confirm') + '</p>',
					onOk: () => {
						delAll(this.pks).then(response => {
							this.$Message.success(this.$t('messageTip.delSuccess'));
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
				if (entity.id) {
					entity['updateUserId'] = this.$store.state.user.userId;
				} else {
					entity['addUserId'] = this.$store.state.user.userId;
					entity['addUserName'] = this.$store.state.user.userName;
				};
				entity['gameServersIds'] = this.thisServersIds.join(",");
				entity['clsId'] = this.thisClsId[0];
				
				let newsList = []
				this.commInfoImgList.forEach(function(value, index, array) {
					let newFile = {
						path: value.url
					}
					newsList.push(newFile);
				})
				entity['commInfoImgList'] = newsList;
				setTimeout(() => {
					this.loading = false;
					this.$nextTick(() => {
						this.loading = true;
						this.$refs.saveFrom.validate((valid) => {
							if (valid) {
								save(entity).then(response => {
									this.entity = this.$options.data().entity;
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
			saveCancel() {
				this.entity = this.$options.data().entity;
				this.thisServersIds = this.$options.data().thisServersIds;
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
		}
	}
</script>
