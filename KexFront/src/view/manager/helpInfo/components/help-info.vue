<template>
	<div>
		<div style="margin-bottom: 10px;">
			<Button type="primary" @click="add">{{$t('button.add')}}</Button>
		</div>
		<Table border :data="tableData" :columns="tableColumns" stripe/>
		<div style="margin:10px 0px;text-align: center;">
			<Page :total="dataCount" :current="searchFrom.page" @on-change="changePage"></Page>
		</div>
		<Modal width="800" v-model="formModal" :title="modalTitle" @on-ok="save" @on-cancel="saveCancel" :loading="loading" :mask-closable="false">
			<Form ref="saveFrom" :model="entity" :label-width="80" :rules="rules">
				<FormItem prop="title" :label="$t('helpInfoManager.title')">
					<Input v-model="entity.title" :placeholder="$t('helpInfoManager.title')" style="width:320px;" :maxlength="100"></Input>
				</FormItem>
				<FormItem prop="content" :label="$t('helpInfoManager.content')">
					<Editor @on-change="getContent" :cache="false" :value="entity.content"></Editor>
				</FormItem>
				<FormItem prop="orderValue" :label="$t('helpInfoManager.orderValue')">
					<InputNumber :min="0" v-model="entity.orderValue" :placeholder="$t('helpInfoManager.orderValue')"></InputNumber>
				</FormItem>
			</Form>
		</Modal>
	</div>
</template>
<script>
	import {list,save,del,delAll} from '@/api/manager/helpInfo';
	import {formatDate} from '@/libs/tools';
	import Editor from '@/components/editor'
	import {mapGetters} from 'vuex';
    export default {
		name: 'helpInfo',
		components: {
			 Editor
		},
		props: {
		  helpModuleItem:{
			  type:Object
		  }
		},
        data () {
            return {
                dataCount: 0,
                tableData: [],
				formModal: false,
				modalTitle:"",
				entity: {
					orderValue: 0,
				},
				searchFrom: {
					modId:this.modId
				},
				loading: true, 
            }
        },
		watch: {
		  helpModuleItem: function(value) {
		    if (value) {
		      this.getData();
		    }
		  }
		},
		mounted: function() {
		},
		computed: {
			rules() {
				return {
					title: [{required: true,message: this.$t('helpInfoManager.title') + this.$t('validateTip.notNull')}],
					parName: [{required: true,message: this.$t('helpInfoManager.parName') + this.$t('validateTip.notNull')}],
				    orderValue: [{required: true,message:this.$t('helpInfoManager.orderValue') + this.$t('validateTip.notNull')}],
				}
			},
			tableColumns() {
				let that = this;
				return [{
						type: 'index',
						width: 60,
						align: 'center',
						title:this.$t('helpInfoManager.tableIndex'),
					},
					{
						title: this.$t('helpInfoManager.title'),
						align: 'center',
						key: 'title'
					},
					{
						title: this.$t('helpInfoManager.addDate'), 
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
						title: this.$t('helpInfoManager.orderValue'),
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
										that.entity = Object.assign({},this.tableData[params.index]);
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
			add() {
				this.modalTitle = this.$t('button.add');
				this.formModal = !this.formModal;
			},
			getContent(html,text){
				this.entity.content = html;
			},
			getData(page) {
				if(page){
					this.searchFrom.page = page;
				}
				console.info(this.helpModuleItem);
				this.searchFrom.modId = this.helpModuleItem.modId;
				list(this.searchFrom).then(response => {
					const data = response.data;
					this.dataCount = data.data.dataCount;
					this.tableData = data.data.entityList;
				}).catch(error => {
					
				});
			},
			delete(id) {
				del(id).then(response => {
					this.$Message.success(this.$t('messageTip.delSuccess'));
					this.getData();
				}).catch(error => {
					this.$Message.error(this.$t('messageTip.frontError'));
				});
			},
			save() {
				this.entity.modId = this.modId;
				let entity = this.entity;
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
			changePage(page) {
				this.searchFrom.page = page;
				this.getData();
			},
			saveCancel(){
				this.entity=this.$options.data().entity;
			}
        }
    }
</script>