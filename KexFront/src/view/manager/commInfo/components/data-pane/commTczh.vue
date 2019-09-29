<template>
	<div>
		<Modal v-model="thisModal" width="50%" :title="modalTitle" @on-ok="save" @on-cancel="saveCancel" :loading="loading"
		 :mask-closable="false">
			<Form ref="saveFrom" :model="entity" :label-width="90" :rules="rules">

				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableClass">
					<tr>
						<th colspan="4">商品套餐维护
						<Button type="primary" size="small" @click="add" style="margin-left: 10px;">添加</Button></th>
					</tr>
					<tr>
						<th nowrap="nowrap" class="title_item">套餐名称</th>
						<th nowrap="nowrap" class="title_item">价格</th>
						<th nowrap="nowrap" class="title_item">数量</th>
						<th nowrap="nowrap" class="title_item">操作</th>
					<tr v-for="(item, index) in commTczhList" :key="index">
						<td>
							<Input v-model="item.tczhName" name="tczhName" :placeholder="$t('commInfoManager.tczhName')" style="" :maxlength="30"></Input>
						</td>
						<td>
							<Input v-model="item.commPrice" name="commPrice" :placeholder="$t('commInfoManager.commPrice')" style="" :maxlength="10"></Input>
						</td>
						<td>
							<Input v-model="item.inventory" name="inventory" :placeholder="$t('commInfoManager.inventory')" style="" :maxlength="10"></Input>
						</td>
						<td style="text-align: center;">
							<Button type="error" size="small" @click="delIndex(index)">删除</Button>
						</td>
					</tr>
				</table>
			</Form>
		</Modal>

	</div>
</template>
<script>
	import {formatDate,log} from '@/libs/tools';
	import {list,save,del,delAll,saveTczh,editTczh} from '@/api/manager/commInfo';
	import {mapGetters} from 'vuex';
	export default {
		name: 'commTczh',
		props: {
			
		},
		data() {
			return {
				modalTitle: "",
				entity: {
					commId:'',
					commTczhPriceList:[]
				},
				loading: true,
				commTczhList:[],
				thisModal:false
			}
		},
		mounted: function() {
			// changeModal();
		},
		computed: {
			rules() {
				return {
// 					tczhName: [{
// 						required: true,
// 						message: this.$t('commInfoManager.tczhName') + this.$t('validateTip.notNull')
// 					}],
				}
			},
		},
		methods: {
			getTczh(id){
				editTczh(id).then(response => {
					this.commTczhList = this.$options.data().commTczhList;
					const data = response.data;
					if(data.data.length > 0){
						this.commTczhList = data.data;
					}
				}).catch(error => {});
			},
			changeModal(){
				this.thisModal = !this.thisModal;
			},
			childMethod(isCommTczhModal,id){
				this.entity.commId = id;
				this.getTczh(id);
				this.changeModal();
			},
			getData() {
				list(this.searchFrom).then(response => {
					const data = response.data;
				}).catch(error => {});
			},
			add() {
				console.info("====add===");
				let commTczhPrice = {
					commId: "",
					commPrice:"",
					inventory:"",
					tczhName:""
				}
				this.commTczhList.push(commTczhPrice);
			},
			delIndex(index){
				console.info("index:"+index);
				console.info(this.commTczhList)
				this.commTczhList.splice(index,1);
				console.info(this.commTczhList)
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
				console.info(entity);
				entity.commTczhPriceList = this.commTczhList;
				setTimeout(() => {
					this.loading = false;
					this.$nextTick(() => {
						console.info("===$nextTick==");
						this.loading = true;
						// this.$refs.saveFrom.validate((valid) => {
							// console.info(valid);
							// if (valid) {
								saveTczh(entity).then(response => {
									this.entity = this.$options.data().entity;
									// this.entity.localeName = this.localeName;
									// this.getData();
									this.$Message.success(this.$t('messageTip.saveSuccess'));
									// this.formModal = false;
									this.changeModal();
								}).catch(error => {
									this.$Message.error(this.$t('messageTip.frontError'));
								});
							// }
						// })
					});
				}, 500);
			},
			saveCancel() {
				this.entity = this.$options.data().entity;
				this.commTczhList = this.$options.data().commTczhList;
				
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
