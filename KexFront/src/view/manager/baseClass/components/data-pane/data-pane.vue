<template>
	<div>
		<Tree :data="data" :render="renderContent"></Tree>
		<Modal width="800" v-model="formModal" :title="modalTitle" @on-ok="save" @on-cancel="saveCancel" :loading="loading" :mask-closable="false">
			<Form ref="saveFrom" :model="entity" :label-width="80" :rules="rules">
				<FormItem prop="clsName" :label="$t('baseClassManager.clsName')">
					<Input v-model="entity.clsName" :placeholder="$t('baseClassManager.clsName')" style="width:320px;" :maxlength="100"></Input>
				</FormItem>
				<FormItem prop="parName" :label="$t('baseClassManager.parName')">
					<Input v-model="entity.parName" :placeholder="$t('baseClassManager.parName')" style="width:320px;" :readonly="true" :maxlength="100"></Input>
				</FormItem>
				<FormItem prop="orderValue" :label="$t('baseDataManager.orderValue')">
					<InputNumber :min="0" v-model="entity.orderValue" :placeholder="$t('baseDataManager.orderValue')"></InputNumber>
				</FormItem>
			</Form>
		</Modal>
	</div>
</template>
<script>
	import {treeList,list,save,del} from '@/api/manager/baseClass';
	import {formatDate} from '@/libs/tools';
	import {mapGetters} from 'vuex';
    export default {
		name: 'dataPane',
		props: {
		  clsScope: {
			type: String
		  },
		  localeName:{
			  type:String
		  }
		},
        data () {
            return {
                data:[],
				formModal: false,
				modalTitle:"",
				entity: {
					orderValue: 0,
					clsScope: this.clsScope,
					localeName: this.localeName
				},
				searchFrom: {
					clsScope: this.clsScope,
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
					clsName: [{required: true,message: this.$t('baseClassManager.clsName') + this.$t('validateTip.notNull')}],
					parName: [{required: true,message: this.$t('baseClassManager.parName') + this.$t('validateTip.notNull')}],
				    orderValue: [{required: true,message:this.$t('baseDataManager.orderValue') + this.$t('validateTip.notNull')}],
				}
			},
		},
        methods: {
			getData() {
				treeList(this.searchFrom).then(response => {
					const data = response.data;
					this.data = data.data.treeData;
					this.languageTypeList = data.data.languageTypeList
					this.currentType = this.languageTypeList[0]
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
										"parName":data.entity.clsName,
										"parId":data.entity.clsId,
										"rootId":data.entity.rootId,
										"clsLevel":data.entity.clsLevel + 1
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
                            on: {
                                click: () => {
									this.$Modal.confirm({
										title: this.$t('messageTip.tip'),
										content: '<p>'+ this.$t('messageTip.confirm') +'</p>',
										onOk: () => {
											this.delete(data.entity.clsId);
										}
									});
								}
                            }
                        }, this.$t('button.del'))
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
									this.entity.clsScope= this.clsScope;
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
				this.entity.clsScope= this.clsScope;
				this.entity.localeName = this.localeName;
			}
        }
    }
</script>