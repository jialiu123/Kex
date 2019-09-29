<template>
  <div>
    <Form :model="searchFrom" :label-width="80">
    	<!-- <Card style="margin-bottom: 10px;">
    		<Input :placeholder="$t('userMoneyManager.typeName')" v-model="searchFrom.f_typeNameLike" style="width:200px"></Input>
    		&nbsp;<Button type="primary" @click="getData(1)" icon="ios-search">{{$t('button.search')}}</Button>
    	</Card> -->
    </Form>
  <!--  <div style="margin-bottom: 10px;">
    	<Button type="primary" @click="add">{{$t('button.add')}}</Button>
    </div> -->
    <Table border :data="tableData" :columns="tableColumns" stripe ref="selection" @on-selection-change="handleSelectRow()" />
    <div style="margin:10px 0px;text-align: center;">
    	<Page :total="dataCount" :current="searchFrom.page" @on-change="changePage"></Page>
    </div>
	<Drawer :title="$t('userMoneyManager.detail')" width="720" v-model="DrawerContent">
	   <audit :id="id" @parReload="parReload"></audit>
	</Drawer>
  </div>
</template>

<script>
import {list,save,add} from '@/api/manager/userMoney';
import audit from './audit';
import {formatDate} from '@/libs/tools';
export default {
  name: 'dataPane',
	props: {
	  typeValue: {
	    type: String
	  },
	  localeName:{
	  	type:String
	  }
	},
	components: {
	  audit
	},
	data() {
		return {
			dataCount: 0,
			id:0,
			tableData: [],
			DrawerContent: false,
			auditStateList: [],
			formModal: false,
			modalTitle:"",
			entity: {
				type: this.typeValue,
			},
			entityView: [],
			pks: [],
			searchFrom: {
				page: 1,
				type: this.typeValue,
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
  			typeName: [{required: true,message: this.$t('userMoneyManager.typeName') + this.$t('validateTip.notNull')}],
			remark: [{required: true,message:this.$t('userMoneyManager.remark') + this.$t('validateTip.notNull')}],
  			orderValue: [{required: true,message:this.$t('userMoneyManager.orderValue') + this.$t('validateTip.notNull')}],
  		}
  	},
  	tableColumns() {
  		let that = this;
  		return [{
				title:this.$t('userMoneyManager.tableIndex'),
  				type: 'index',
  				width: 80,
  				align: 'center',
  			}, 
  			{
  				title: this.$t('userMoneyManager.cashCountBefore'),
  				align: 'center',
  				key: 'cashCountBefore'
  			},
			{
				title: this.$t('userMoneyManager.cashCount'),
				align: 'center',
				key: 'cashCount'
			},
			{
				title: this.$t('userMoneyManager.cashCountAfter'),
				align: 'center',
				key: 'cashCountAfter'
			},
			{
				title: this.$t('userMoneyManager.cashPay'),
				align: 'center',
				key: 'cashPay'
			},
			{
				title: this.$t('userMoneyManager.addMemo'),
				align: 'center',
				key: 'addMemo'
			},
  			{
  				title: this.$t('userMoneyManager.addDate'),
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
				title: this.$t('userMoneyManager.infoState'),
				width: 120,
				align: 'center',
				key: 'infoState',
				render: (h, params) => {
					let showName = "";
					for (let i = 0; i < this.auditStateList.length; i++) {
						if (this.auditStateList[i].index == params.row.infoState) {
							showName = this.auditStateList[i].showName;
							break;
						}
					}
					return h('div',showName);
				}
			},
  			{
  				title: this.$t('userMoneyManager.auditDate'),
  				width: 120,
  				align: 'center',
  				key: 'auditDate',
  				render: (h, params) => {
  					return h('div',
  						formatDate(new Date(params.row.auditDate), 'yyyy-MM-dd')
  					)
  				}
  			},
  			{
  				title: this.$t('button.operation'),
  				key: 'action',
  				fixed: 'right',
  				align: 'center',
  				width: 130,
  				render: (h, params) => {
  					let audit;
  					audit = h('Button', {
  						props: {
  							type: 'error',
  							size: 'small'
  						},
  						on: {
  							click: () => {
								that.id = params.row.id;
  								that.DrawerContent = !that.DrawerContent;
  							}
  						}
  					}, this.$t('button.audit'));
  					return h('div', [audit]);
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
  			this.auditStateList = data.data.auditStateList;
  		}).catch(error => {});
  	},
	parReload(){
		this.DrawerContent = !this.DrawerContent;
		this.getData();
	},
  	add() {
  		this.modalTitle = this.$t('button.add');
  		this.formModal = !this.formModal;
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
							this.entity.type= this.typeValue;
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
  	}
  }
}
</script>