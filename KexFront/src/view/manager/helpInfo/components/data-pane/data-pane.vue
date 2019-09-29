<template>
	<div>
		<Tree :data="data" :render="renderContent"></Tree>
		<Modal width="800" v-model="formModal" :title="modalTitle" @on-ok="save" @on-cancel="saveCancel" :loading="loading" :mask-closable="false">
			<Form ref="saveFrom" :model="entity" :label-width="80" :rules="rules">
				<FormItem prop="modName" :label="$t('helpInfoManager.modName')">
					<Input v-model="entity.modName" :placeholder="$t('helpInfoManager.modName')" style="width:320px;" :maxlength="100"></Input>
				</FormItem>
				<FormItem prop="parName" :label="$t('helpInfoManager.parName')">
					<Input v-model="entity.parName" :placeholder="$t('helpInfoManager.parName')" style="width:320px;" :readonly="true" :maxlength="100"></Input>
				</FormItem>
				<FormItem prop="orderValue" :label="$t('helpInfoManager.orderValue')">
					<InputNumber :min="0" v-model="entity.orderValue" :placeholder="$t('helpInfoManager.orderValue')"></InputNumber>
				</FormItem>
				<FormItem prop="modType" :label="$t('helpInfoManager.modType')">
					<Select v-model="entity.modType" style="width:200px">
						<Option v-for="item in modTypeList" :value="item.index" :key="item.index">{{ item.showName }}</Option>
					</Select>
				</FormItem>
			</Form>
		</Modal>
		<Modal width="1000" v-model="helpInfoModal" :footer-hide="true" title="帮助中心">
		  <helpInfo :helpModuleItem="helpModuleItem"></helpInfo>
		</Modal>
	</div>
</template>
<script>
	import {treeList,save,del} from '@/api/manager/helpModule';
	import helpInfo from '../help-info'
	import {formatDate} from '@/libs/tools';
	import {mapGetters} from 'vuex';
    export default {
		components: {
			helpInfo
		},
		name: 'dataPane',
		props: {
		  localeName:{
			  type:String
		  }
		},
        data () {
            return {
                data:[],
				formModal: false,
				helpModuleItem:{},
				modalTitle:"",
				modTypeList:[],
				helpInfoModal:false,
				entity: {
					orderValue: 0,
					localeName: this.localeName
				},
				searchFrom: {
					localeName:this.localeName
				},
				loading: true, 
            }
        },
		mounted: function() {
			this.getData();
		}, 
		computed: {
			rules() {
				return {
					modName: [{required: true,message: this.$t('helpInfoManager.modName') + this.$t('validateTip.notNull')}],
					parName: [{required: true,message: this.$t('helpInfoManager.parName') + this.$t('validateTip.notNull')}],
				    orderValue: [{required: true,message:this.$t('helpInfoManager.orderValue') + this.$t('validateTip.notNull')}],
					modType: [{required: true,message:this.$t('helpInfoManager.modType') + this.$t('validateTip.notNull')}],
				}
			},
		},
        methods: {
			getData() {
				treeList(this.searchFrom).then(response => {
					const data = response.data;
					this.data = data.data.treeData;
					this.modTypeList = data.data.modTypeList;
				}).catch(error => {
					
				});
			},
            renderContent (h, { root, node, data }) {
				let that = this;
                return h('span', {
                    style: {
                        display: 'inline-block',
                        width: '100%'
                    }
                }, [
                    h('span', [
                        h('Icon', {
                            props: {
                                type: 'ios-paper-outline'
                            },
                            style: {
                                marginRight: '8px'
                            }
                        }),
                        h('span', data.title)
                    ]),
                    h('span', {
                        style: {
                            display: 'inline-block',
                            float: 'right',
                            marginRight: '32px'
                        }
                    }, [
                        h('Button', {
                            props:{
									type: 'default',
									size: 'small'
								},
                            style: {
                                marginRight: '8px'
                            },
                            on: {
                                click: () => {
									that.modalTitle = this.$t('button.add');
									let params = {
										"parName":data.entity.modName,
										"parId":data.entity.hmodId,
									}
									that.entity = Object.assign(that.entity, params);
									that.formModal = !that.formModal; 
								}
                            }
                        }, this.$t('button.add')),
						h('Button', {
						    props:{
									type: 'primary',
									size: 'small'
								},
						    style: {
						        marginRight: '8px'
						    },
						    on: {
						        click: () => { 
									that.modalTitle = this.$t('button.edit');
									that.entity = data.entity;
									that.formModal = !this.formModal;
								}
						    }
						}, this.$t('button.edit')),
                        h('Button', {
                            props: {
									type: 'error',
									size: 'small'
								},
							style: {
							    marginRight: '8px'
							},	
                            on: {
                                click: () => {
									this.$Modal.confirm({
										title: this.$t('messageTip.tip'),
										content: '<p>'+ this.$t('messageTip.confirm') +'</p>',
										onOk: () => {
											this.delete(data.entity.id);
										}
									});
								}
                            }
                        }, this.$t('button.del')),
						h('Button', {
						    props: {
									type: 'primary',
									size: 'small'
								},
						    on: {
						        click: () => {
									this.helpInfoModal = !this.helpInfoModal;
									this.helpModuleItem = data.entity;
								}
						    }
						}, this.$t('button.addContent'))
                    ])
                ]);
            },
			delete(id) {
				del(id).then(response => {
					this.$Message.success(this.$t('messageTip.delSuccess'));
					this.getData();
				}).catch(error => {
					this.$Message.error(this.$t('messageTip.frontError'));
				});
			},
            remove (root, node, data) {
				this.$Modal.confirm({
					title: this.$t('messageTip.tip'),
					content: '<p>'+ this.$t('messageTip.confirm') +'</p>',
					onOk: () => {
						del(this.tableData[index].id).then(response => {
							this.getData();
						}).catch(error => {
							this.$Message.error(this.$t('messageTip.frontError'));
						});
					}
				});
            },
			save() {
				let entity = this.entity;
				setTimeout(() => {
					this.loading = false;
					this.$nextTick(() => {
						this.loading = true;
						this.$refs.saveFrom.validate((valid) => {
							if (valid) {
								save(entity).then(response => {
									this.entity=this.$options.data().entity;
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
				this.entity.localeName = this.localeName;
			}
        }
    }
</script>