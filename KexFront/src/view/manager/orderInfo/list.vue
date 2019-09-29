<template>
	<div>
		<Form :model="searchFrom" :label-width="80">
			<Card style="margin-bottom: 10px;">
				<Input :placeholder="$t('orderInfoManager.tradeIndex')" v-model="searchFrom.tradeIndex" style="width:200px"></Input>
				&nbsp;
				<Select v-model="searchFrom.payType" style="width:150px" :placeholder="$t('orderInfoManager.payType')">
					<Option v-for="item in payTypeList" :value="item.index" :key="item.index">{{ item.showName }}</Option>
				</Select>
				&nbsp;
				<Select v-model="searchFrom.orderState" style="width:150px" :placeholder="$t('orderInfoManager.orderState')">
					<Option v-for="item in orderStateList" :value="item.index" :key="item.index">{{ item.showName }}</Option>
				</Select>
				&nbsp;<Button type="primary" @click="getData(1)" icon="ios-search">{{$t('button.search')}}</Button>
			</Card>
		</Form>
		<Table border :data="tableData" :columns="tableColumns" stripe/>
		<div style="margin:10px 0px;text-align: center;">
			<Page :total="dataCount" :current="searchFrom.page" @on-change="changePage"></Page>
		</div>
		
		<Modal width="1000" v-model="orderDetailModal" :footer-hide="true" title="订单详情">
			<orderDetail :orderId="orderId"></orderDetail>
		</Modal>
		
	</div>
</template>

<script>
	import orderDetail from './components/orderDetail'
	import {list,updateOrder} from '@/api/manager/orderInfo';
	import {formatDate} from '@/libs/tools';
	import {mapGetters} from 'vuex';
	export default {
		components: {
		  orderDetail
		},
		data() {
			return {
				dataCount: 0,
				tableData: [],
				payTypeList: [],
				orderStateList: [],
				orderDetailModal: false,
				orderId:0,
				entity: {
					orderValue: 0
				},
				searchFrom: {
					page: 1,
				},
			};
		},
		mounted: function() {
			this.getData();
		},
		computed: {
			tableColumns() {
				let that = this;
				return [{
					    title:this.$t('orderInfoManager.tableIndex'),
						type: 'index',
						width: 60,
						align: 'center',
					},
					{
						title: this.$t('orderInfoManager.tradeIndex'),
						align: 'center',
						key: 'tradeIndex'
					},
					{
						title: this.$t('orderInfoManager.addUserName'),
						align: 'center',
						key: 'addUserName'
					},
					{
						title: this.$t('orderInfoManager.orderDate'),
						align: 'center',
						key: 'orderDate',
						render: (h, params) => {
							return h('div',
								formatDate(new Date(params.row.orderDate), 'yyyy-MM-dd hh:mm:ss')
							)
						}
					},
					{
						title: this.$t('orderInfoManager.orderMoney'),
						align: 'center',
						key: 'orderMoney'
					},
					{
						title: this.$t('orderInfoManager.payType'),
						align: 'center',
						key: 'payType',
						render: (h, params) => {
							let showName = "";
							for (let i = 0; i < this.payTypeList.length; i++) {
								if (this.payTypeList[i].index == params.row.payType) {
									showName = this.payTypeList[i].showName;
									break;
								}
							}
							return h('div',showName);
						}
					},
					{
						title: this.$t('orderInfoManager.orderState'),
						align: 'center',
						key: 'orderState',
						render: (h, params) => {
							let showName = "";
							for (let i = 0; i < this.orderStateList.length; i++) {
								if (this.orderStateList[i].index == params.row.orderState) {
									showName = this.orderStateList[i].showName;
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
						width: 130,
						render: (h, params) => {
							let orderDetail,orderDeliver,orderCancel;
							orderDetail = h('div',{
							 	style: {
							 	    margin: '5px 0px'
							 	},
							 	on: {
							 		click: () => {
										that.orderId = params.row.id;
							 			that.orderDetailModal = !that.orderDetailModal; 
							 		}
							 	}
							 },[h('a',this.$t('orderInfoManager.orderDetail'))]);
							orderDeliver = h('div',{
							 	style: {
							 	    margin: '5px 0px'
							 	},
							 	on: {
							 		click: () => {
							 			this.$Modal.confirm({
							 				title: this.$t('messageTip.tip'),
							 				content: '<p>' + this.$t('messageTip.confirm') + '</p>',
							 				onOk: () => {
							 					this.updateOrder(params.row.id,20);
							 				}
							 			}); 
							 		}
							 	}
							 },[h('a',this.$t('orderInfoManager.orderDeliver'))]);
							orderCancel = h('div',{
							  	style: {
							  	    margin: '5px 0px'
							  	},
							  	on: {
							  		click: () => {
							  			this.$Modal.confirm({
							  				title: this.$t('messageTip.tip'),
							  				content: '<p>' + this.$t('messageTip.confirm') + '</p>',
							  				onOk: () => {
							  					this.updateOrder(params.row.id,-10);
							  				}
							  			});
							  		}
							  	}
							  },[h('a',this.$t('orderInfoManager.orderCancel'))]);
							let orderCanDomethod = [];
							orderCanDomethod.push(orderDetail);
							if(params.row.orderState == 0){
								orderCanDomethod.push(orderCancel);
							}  
							if(params.row.orderState == 10){
								orderCanDomethod.push(orderDeliver);
							}  
							return h('div', orderCanDomethod);
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
					this.payTypeList = data.data.payTypeList;
					this.orderStateList = data.data.orderStateList;
				}).catch(error => {});
			},
			updateOrder(id,orderState){
				let entity={
					id:id,
					orderState:orderState,
					updateUserId:this.$store.state.user.userId
				};
				updateOrder(entity).then(response => {
					const data = response.data;
					this.getData();
					this.$Message.success(this.$t('messageTip.doSuccess'));
				}).catch(error => {});
			},
			changePage(page) {
				this.searchFrom.page = page;
				this.getData();
			}
		}
	};
</script>
