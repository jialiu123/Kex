<template>
	<div>
		<Form :model="searchFrom" :label-width="80">
			<Card style="margin-bottom: 10px;">
				<Input :placeholder="$t('roleManager.roleName')" v-model="searchFrom.f_roleNameLike" style="width:200px"></Input>
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

		<Modal v-model="formModal" :title="modalTitle" @on-ok="save" @on-cancel="saveCancel" :loading="loading" :mask-closable="false">
			<Form ref="saveFrom" :model="entity" :label-width="80" :rules="rules">
				<FormItem prop="roleName" :label="$t('roleManager.roleName')">
					<Input v-model="entity.roleName" :placeholder="$t('roleManager.roleName')"></Input>
				</FormItem>
				<FormItem prop="orderValue" :label="$t('roleManager.orderValue')">
					<InputNumber :min="0" v-model="entity.orderValue" :placeholder="$t('roleManager.orderValue')"></InputNumber>
				</FormItem>
			</Form>
		</Modal>

	</div>
</template>

<script>
	import {list,save,del,delAll} from '@/api/manager/role';
	import {formatDate} from '@/libs/tools';
	import {mapGetters} from 'vuex';
	export default {

		data() {
			return {
				dataCount: 0,
				tableData: [],
				formModal: false,
				modalTitle:"",
				entity: {
					orderValue: 0
				},
				pks: [],
				searchFrom: {
					page: 1,
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
					roleName: [{required: true,message: this.$t('roleManager.roleName') + this.$t('validateTip.notNull')}],
					orderValue: [{required: true,message:this.$t('roleManager.orderValue') + this.$t('validateTip.notNull')}],
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
						title: this.$t('roleManager.id'),
						width: 90,
						align: 'center',
						key: 'id'
					},
					{
						title: this.$t('roleManager.roleName'),
						align: 'center',
						key: 'roleName'
					},
					{
						title: this.$t('roleManager.addDate'),
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
						title: this.$t('roleManager.orderValue'),
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
			getData(page) {
				if(page){
					this.searchFrom.page = page;
				}
				list(this.searchFrom).then(response => {
					const data = response.data;
					this.dataCount = data.data.dataCount;
					this.tableData = data.data.entityList;
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
								save(entity).then(response => {
									this.entity=this.$options.data().entity;
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
			}
		}
	};
</script>
