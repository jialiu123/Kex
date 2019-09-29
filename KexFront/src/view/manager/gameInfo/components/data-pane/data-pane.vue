<template>
	<div>
		<Form :model="searchFrom" :label-width="80">
			<Card style="margin-bottom: 10px;">
				<Input :placeholder="$t('gameInfoManager.gameName')" v-model="searchFrom.f_gameNameLike" style="width:200px"></Input>
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

		<Modal v-model="formModal" :title="modalTitle" @on-ok="save" @on-cancel="saveCancel" :loading="loading"
		 :mask-closable="false">
			<Form ref="saveFrom" :model="entity" :label-width="80" :rules="rules">
				<FormItem prop="gameName" :label="$t('gameInfoManager.gameName')">
					<Input v-model="entity.gameName" :placeholder="$t('gameInfoManager.gameName')"></Input>
				</FormItem>

				<FormItem prop="gameDeveloper" :label="$t('gameInfoManager.gameDeveloper')">
					<Input v-model="entity.gameDeveloper" :placeholder="$t('gameInfoManager.gameDeveloper')"></Input>
				</FormItem>

				<FormItem prop="gameClass" :label="$t('gameInfoManager.gameClass')">
					<Select v-model="entity.gameClass" style="width:200px">
						<Option v-for="item in gameClassList" :value="item.clsId" :key="item.clsId">{{ item.clsName }}</Option>
					</Select>
				</FormItem>

				<FormItem prop="gamePlatform" :label="$t('gameInfoManager.gamePlatform')">
					<Select v-model="entity.gamePlatform" style="width:200px">
						<Option v-for="item in gamePlatformList" :value="item.id" :key="item.id">{{ item.typeName }}</Option>
					</Select>
				</FormItem>

				<!-- <FormItem prop="language" :label="$t('gameInfoManager.language')">
					<Select v-model="entity.language" style="width:200px">
						<Option v-for="item in languageTypeList" :value="item.index" :key="item.index">{{ item.showName }}</Option>
					</Select>
				</FormItem> -->

				<FormItem prop="gameTheme" :label="$t('gameInfoManager.gameTheme')">
					<Input v-model="entity.gameTheme" type="textarea" :rows="4" :placeholder="$t('gameInfoManager.gameTheme')" />
				</FormItem>

				<FormItem prop="gameContent" :label="$t('gameInfoManager.gameContent')">
					<Input v-model="entity.gameContent" type="textarea" :rows="4" :placeholder="$t('gameInfoManager.gameContent')" />
				</FormItem>

			</Form>
		</Modal>

		<Modal v-model="sonModal" :title="modalTitle" :loading="loading" :mask-closable="false" :footer-hide="true" width="80%">
			<Form :model="searchFromSon" :label-width="80">
				<Card style="margin-bottom: 10px;">
					<Input :placeholder="$t('gameInfoManager.gameName')" v-model="searchFromSon.f_gameServerNameLike" style="width:200px"></Input>
					&nbsp;<Button type="primary" @click="getDataSon(1)" icon="ios-search">{{$t('button.search')}}</Button>
				</Card>
			</Form>
			<div style="margin-bottom: 10px;">
				<Button type="primary" @click="addSon">{{$t('button.add')}}</Button>
				&nbsp;<Button type="error" @click="delAllSon">{{$t('button.delSelect')}}</Button>
			</div>
			<Table border :data="tableDataSon" :columns="tableColumnsSon" stripe ref="selection" @on-selection-change="handleSelectRow()" />
			<div style="margin:10px 0px;text-align: center;">
				<Page :total="dataCountSon" :current="searchFromSon.page" @on-change="changePageSon"></Page>
			</div>
		</Modal>

		<Modal v-model="addSonModal" :title="modalTitle" @on-ok="saveSon" @on-cancel="saveCancel" :loading="loading"
		 :mask-closable="false">
			<Form ref="saveFromSon" :model="son" :label-width="100" :rules="rulesSon">
				<FormItem prop="gameServerName" :label="$t('gameInfoManager.gameServerName')">
					<Input v-model="son.gameServerName" :placeholder="$t('gameInfoManager.gameServerName')"></Input>
				</FormItem>

				<FormItem prop="gameServerLevel" :label="$t('gameInfoManager.gameServerLevel')">
					<Input v-model="son.gameServerLevel" :placeholder="$t('gameInfoManager.gameServerLevel')"></Input>
				</FormItem>
			</Form>
		</Modal>
	</div>
</template>
<script>
	import {
		list,
		save,
		del,
		delAll,
		listSon,
		saveSon,
		delSon,
		delAllSon
	} from '@/api/manager/gameInfo';
	import {
		formatDate
	} from '@/libs/tools';
	import {
		mapGetters
	} from 'vuex';
	export default {
		name: 'dataPane',
		props: {
			localeName: {
				type: String
			}
		},
		data() {
			return {
				dataCount: 0,
				dataCountSon: 0,
				tableData: [],
				tableDataSon: [],
				formModal: false,
				sonModal: false,
				addSonModal: false,
				modalTitle: "",
				entity: {
					localeName: this.localeName
				},
				son: {
					localeName: this.localeName,
				},
				pks: [],
				searchFrom: {
					page: 1,
					localeName: this.localeName,
				},
				searchFromSon: {
					page: 1,
					localeName: this.localeName
				},
				loading: true,
				languageTypeList: [],
				gameClassList: [],
				gamePlatformList: [],
				parId: ''
			}
		},
		mounted: function() {
			this.getData();
		},
		computed: {
			rules() {
				return {
					ganmeName: [{
						required: true,
						message: this.$t('gameInfoManager.ganmeName') + this.$t('validateTip.notNull')
					}],
					gameClass: [{
						required: true,
						message: this.$t('gameInfoManager.gameClass') + this.$t('validateTip.notNull')
					}],
					gamePlatform: [{
						required: true,
						message: this.$t('gameInfoManager.gamePlatform') + this.$t('validateTip.notNull')
					}],
				}
			},
			rulesSon() {
				return {
					gameServerName: [{
						required: true,
						message: this.$t('gameInfoManager.gameServerName') + this.$t('validateTip.notNull')
					}],

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
						title: this.$t('gameInfoManager.gameName'),
						align: 'center',
						key: 'gameName'
					},
					{
						title: this.$t('gameInfoManager.gameClass'),
						align: 'center',
						key: 'gameClass',
						render: (h, params) => {
							return h('div', params.row.map.clsName)
						}
					},
					{
						title: this.$t('gameInfoManager.gamePlatform'),
						align: 'center',
						key: 'gamePlatform',
						render: (h, params) => {
							return h('div', params.row.map.typeName)
						}
					},
					
					{
						title: this.$t('gameInfoManager.addDate'),
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
						title: this.$t('button.operation'),
						key: 'action',
						fixed: 'right',
						align: 'center',
						width: 230,
						render: (h, params) => {
							let edit, del, addSon;
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
										that.entity = Object.assign({}, this.tableData[params.index]);
										that.formModal = !this.formModal;
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
											content: '<p>' + this.$t('messageTip.confirm') + '</p>',
											onOk: () => {
												this.delete(params.index);
											}
										});
									}
								}
							}, this.$t('button.del'));
							addSon = h('Button', {
								props: {
									type: 'primary',
									size: 'small'
								},
								style: {
									marginRight: '5px'
								},
								on: {
									click: () => {
										that.modalTitle = this.$t('gameInfoManager.addSon');
										that.searchFromSon['parId'] = this.tableData[params.index].id
										that.son['parId'] = this.tableData[params.index].id
										that.parId = this.tableData[params.index].id;
										that.getDataSon(1);
										that.sonModal = !this.sonModal;
									}
								}
							}, this.$t('gameInfoManager.addSon'));
							return h('div', [edit, del, addSon]);
						}
					}
				]
			},
			tableColumnsSon() {
				let that = this;
				return [{
						type: 'selection',
						width: 60,
						align: 'center',
					},
					{
						title: this.$t('gameInfoManager.gameServerName'),
						align: 'center',
						key: 'gameServerName'
					},
					{
						title: this.$t('gameInfoManager.gameServerLevel'),
						align: 'center',
						key: 'gameServerLevel'
					},
					{
						title: this.$t('gameInfoManager.addDate'),
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
						title: this.$t('button.operation'),
						key: 'action',
						fixed: 'right',
						align: 'center',
						width: 200,
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
										that.son = Object.assign({}, this.tableDataSon[params.index]);
										that.addSonModal = !this.addSonModal;
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
											content: '<p>' + this.$t('messageTip.confirm') + '</p>',
											onOk: () => {
												this.deleteSon(params.index);
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
			getData(page) {
				if (page) {
					this.searchFrom.page = page;
				}
				list(this.searchFrom).then(response => {
					const data = response.data;
					this.dataCount = data.data.dataCount;
					this.tableData = data.data.entityList;
					this.languageTypeList = data.data.languageTypeList;
					this.gamePlatformList = data.data.gamePlatformList;
					this.gameClassList = data.data.gameClassList;
				}).catch(error => {});
			},
			getDataSon(page) {
				if (page) {
					this.searchFromSon.page = page;
				}
				listSon(this.searchFromSon).then(response => {
					const data = response.data;
					this.dataCountSon = data.data.dataCount;
					this.tableDataSon = data.data.entityList;
				}).catch(error => {});
			},
			add() {
				this.modalTitle = this.$t('button.add');
				this.formModal = !this.formModal;
			},
			addSon() {
				this.modalTitle = this.$t('button.add');
				this.addSonModal = !this.addSonModal;
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
			deleteSon(index) {
				console.info(this.tableDataSon[index].id);
				delSon(this.tableDataSon[index].id).then(response => {
					this.$Message.success(this.$t('messageTip.delSuccess'));
					//返回到第一页
					this.searchFromSon.page = 1;
					this.getDataSon();
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
							//返回到第一页
							this.searchFrom.page = 1;
							this.getData();
						}).catch(error => {
							this.$Message.error(this.$t('messageTip.frontError'));
						});
					}
				});
			},
			delAllSon() {
				if (this.pks.length == 0) {
					this.$Message.error(this.$t('messageTip.selectOne'));
					return;
				}
				this.$Modal.confirm({
					title: this.$t('messageTip.tip'),
					content: '<p>' + this.$t('messageTip.confirm') + '</p>',
					onOk: () => {
						delAllSon(this.pks).then(response => {
							this.$Message.success(this.$t('messageTip.delSuccess'));
							//返回到第一页
							this.searchFromSon.page = 1;
							this.getDataSon();
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
				}
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
			saveSon() {
				let entity = this.son;
				if (entity.id) {
					entity['updateUserId'] = this.$store.state.user.userId;
				} else {
					entity['addUserId'] = this.$store.state.user.userId;
				}
				console.info(entity)
				setTimeout(() => {
					this.loading = false;
					this.$nextTick(() => {
						this.loading = true;
						this.$refs.saveFromSon.validate((valid) => {
							if (valid) {
								saveSon(entity).then(response => {
									this.searchFromSon = {
										parId: this.parId,
										localeName: this.localeName,
									};
									this.son = {
										parId: this.parId,
										localeName: this.localeName,
									};
									this.getDataSon();

									this.$Message.success(this.$t('messageTip.saveSuccess'));
									this.addSonModal = false;
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
				this.son = this.$options.data().son;
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
			changePageSon(page) {
				this.searchFromSon.page = page;
				this.getDataSon();
			}
		}
	}
</script>
