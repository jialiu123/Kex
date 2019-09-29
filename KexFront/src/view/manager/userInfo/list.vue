<template>
	<div>
		<Form :model="searchFrom" :label-width="80">
			<Card style="margin-bottom: 10px;">
				<Input :placeholder="$t('userInfoManager.userName')" v-model="searchFrom.f_userNameLike" style="width:200px"></Input>
				&nbsp;
				<Input :placeholder="$t('userInfoManager.mobile')" v-model="searchFrom.mobile" style="width:200px"></Input>
				&nbsp;
				<Select v-model="searchFrom.userType" style="width:200px">
					<Option v-for="item in userTypeList" :value="item.index" :key="item.index">{{ item.showName }}</Option>
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

		<Modal v-model="formModal" :title="modalTitle" @on-ok="save" @on-cancel="saveCancel" :loading="loading"
		 :mask-closable="false">
			<Form ref="saveFrom" :model="entity" :label-width="80" :rules="rules">
				<FormItem prop="userName" :label="$t('userInfoManager.userName')">
					<Input v-model="entity.userName" :placeholder="$t('userInfoManager.userName')"></Input>
				</FormItem>
				<FormItem prop="realName" :label="$t('userInfoManager.realName')">
					<Input v-model="entity.realName" :placeholder="$t('userInfoManager.realName')"></Input>
				</FormItem>
				<FormItem prop="mobile" :label="$t('userInfoManager.mobile')">
					<Input v-model="entity.mobile" :placeholder="$t('userInfoManager.mobile')"></Input>
				</FormItem>
				<FormItem prop="userType" :label="$t('userInfoManager.userType')">
					<Select v-model="entity.userType" style="width:200px">
						<Option v-for="item in userTypeList" :value="item.index" :key="item.index">{{ item.showName }}</Option>
					</Select>
				</FormItem>
				<FormItem prop="userLogo" :label="$t('userInfoManager.userLogo')">
					<uploadInput :uploadList="uploadList" @setUploadList="setUploadList"></uploadInput>
				</FormItem>
				<FormItem prop="idCard" :label="$t('userInfoManager.idCard')">
					<Input v-model="entity.idCard" :placeholder="$t('userInfoManager.idCard')" />
				</FormItem>

				<FormItem prop="orderValue" :label="$t('userInfoManager.orderValue')">
					<InputNumber :min="0" v-model="entity.orderValue" :placeholder="$t('userInfoManager.orderValue')"></InputNumber>
				</FormItem>
			</Form>
		</Modal>
		<Modal v-model="initPassModal" :title="modalTitle" @on-ok="saveInitPass" @on-cancel="saveCancel" :loading="loading"
		 :mask-closable="false">
			<Form ref="initPassFrom" :model="entity" :label-width="80" :rules="rules">
				<FormItem prop="password" :label="$t('userInfoManager.password')">
					<Input v-model="entity.password" :placeholder="$t('userInfoManager.defaultPass')"></Input>
				</FormItem>
			</Form>
		</Modal>
		<Modal width="75%" v-model="userBiRecordModal" :footer-hide="true"  :title="$t('userInfoManager.inputOutDetail')">
			<userBiRecord :addUserId="addUserId" :biType="100"></userBiRecord>
		</Modal>
	</div>
</template>

<script>
	import {list,save,del,delAll} from "@/api/manager/userInfo";
	import uploadInput from "@/components/upload/upload";
	import {formatDate} from "@/libs/tools";
	import {mapGetters} from "vuex";
	import userBiRecord from '../userBiRecord/userBiRecord'
	export default {
		components: {
			uploadInput,
			userBiRecord
		},
		data() {
			return {
				dataCount: 0,
				tableData: [],
				formModal: false,
				initPassModal: false,
				modalTitle: "",
				entity: {
					orderValue: 0
				},
				pks: [],
				searchFrom: {
					page: 1
				},
				userBiRecordModal: false,
				addUserId:0,
				loading: true,
				userTypeList: [],
				uploadList: []
			};
		},
		mounted: function() {
			this.getData();
			// this.uploadList = this.$refs.upload.fileList;
		},
		computed: {
			rules() {
				return {
					userName: [{
						required: true,
						message: this.$t("userInfoManager.userName") +
							this.$t("validateTip.notNull")
					}],
					mobile: [{
						required: true,
						message: this.$t("userInfoManager.mobile") + this.$t("validateTip.notNull")
					}],
					userType: [{
						required: true,
						message: this.$t("userInfoManager.userType") +
							this.$t("validateTip.notNull")
					}],
					orderValue: [{
						required: true,
						message: this.$t("userInfoManager.orderValue") +
							this.$t("validateTip.notNull")
					}]
				};
			},
			tableColumns() {
				let that = this;
				return [{
						type: "selection",
						width: 60,
						align: "center"
					},
					{
						title: this.$t("userInfoManager.userName"),
						align: "center",
						key: "userName"
					},
					{
						title: this.$t("userInfoManager.userType"),
						width: 130,
						align: "center",
						key: "userType",
						render: (h, params) => {
							return h("div", that.getUserTypeName(params.row.userType));
						}
					},
					{
						title: this.$t("userInfoManager.mobile"),
						align: "center",
						key: "mobile"
					},
					{
						title: this.$t("userInfoManager.biDianzi"),
						align: "center",
						key: "biDianzi",
						render: (h, params) => {
							let biDianzi = h("a", {
									on: {
										click: () => {
											that.addUserId = params.row.id;
											that.userBiRecordModal = !that.userBiRecordModal; 
										}
									}
								},
								params.row.biDianzi
							);
							return h("div", [biDianzi]);
						}

					},
					{
						title: this.$t("userInfoManager.addDate"),
						width: 120,
						align: "center",
						key: "addDate",
						render: (h, params) => {
							return h(
								"div",
								formatDate(new Date(params.row.addDate), "yyyy-MM-dd")
							);
						}
					},
					{
						title: this.$t("userInfoManager.orderValue"),
						width: 120,
						align: "center",
						key: "orderValue"
					},
					{
						title: this.$t("userInfoManager.initPass"),
						width: 120,
						align: "center",
						key: "password",
						render: (h, params) => {
							let initPass = h(
								"Button", {
									props: {
										type: "primary",
										size: "small"
									},
									style: {
										marginRight: "5px"
									},
									on: {
										click: () => {
											that.modalTitle = this.$t("userInfoManager.initPass");
											that.entity = Object.assign({},
												this.tableData[params.index]
											);
											that.entity.password = "";
											that.initPassModal = !this.initPassModal;
										}
									}
								},
								this.$t("userInfoManager.initPass")
							);
							return h("div", [initPass]);
						}
					},
					{
						title: this.$t("button.operation"),
						width: 200,
						key: "action",
						fixed: "right",
						align: "center",
						render: (h, params) => {
							let edit, del;
							edit = h(
								"Button", {
									props: {
										type: "primary",
										size: "small"
									},
									style: {
										marginRight: "5px"
									},
									on: {
										click: () => {
											that.modalTitle = this.$t("button.edit");
											that.entity = Object.assign({},
												this.tableData[params.index]
											);
											let file = {
												url: that.entity.userLogo,
												name: that.entity.userLogo
											};
											that.uploadList = [file];
											that.formModal = !this.formModal;
										}
									}
								},
								this.$t("button.edit")
							);
							del = h(
								"Button", {
									props: {
										type: "error",
										size: "small"
									},
									style: {
										marginRight: "5px"
									},
									on: {
										click: () => {
											this.$Modal.confirm({
												title: this.$t("messageTip.tip"),
												content: "<p>" + this.$t("messageTip.confirm") + "</p>",
												onOk: () => {
													this.delete(params.index);
												}
											});
										}
									}
								},
								this.$t("button.del")
							);
							return h("div", [edit, del]);
						}
					}
				];
			}
		},
		methods: {
			getUserTypeName(val) {
				let showName = "";
				for (let i = 0; i < this.userTypeList.length; i++) {
					if (this.userTypeList[i].index == val) {
						showName = this.userTypeList[i].showName;
						break;
					}
				}
				return showName;
			},
			getData(page) {
				if (page) {
					this.searchFrom.page = page;
				}
				list(this.searchFrom)
					.then(response => {
						const data = response.data;
						this.dataCount = data.data.dataCount;
						this.tableData = data.data.entityList;
						this.userTypeList = data.data.userTypeList;
					})
					.catch(error => {});
			},
			add() {
				this.modalTitle = this.$t("button.add");
				this.formModal = !this.formModal;
			},
			delete(index) {
				del(this.tableData[index].id)
					.then(response => {
						this.$Message.success(this.$t("messageTip.delSuccess"));
						this.searchFrom.page = 1;
						this.getData();
					})
					.catch(error => {
						this.$Message.error(this.$t("messageTip.frontError"));
					});
			},
			delAll() {
				if (this.pks.length == 0) {
					this.$Message.error(this.$t("messageTip.selectOne"));
					return;
				}
				this.$Modal.confirm({
					title: this.$t("messageTip.tip"),
					content: "<p>" + this.$t("messageTip.confirm") + "</p>",
					onOk: () => {
						delAll(this.pks)
							.then(response => {
								this.$Message.success(this.$t("messageTip.delSuccess"));
								this.searchFrom.page = 1;
								this.getData();
							})
							.catch(error => {
								this.$Message.error(this.$t("messageTip.frontError"));
							});
					}
				});
			},
			save() {
				let entity = this.entity;
				entity["addUserId"] = this.$store.state.user.userId;
				setTimeout(() => {
					this.loading = false;
					this.$nextTick(() => {
						this.loading = true;
						this.$refs.saveFrom.validate(valid => {
							if (valid) {
								save(entity)
									.then(response => {
										this.entity = this.$options.data().entity;
										this.uploadList = [];
										this.$refs.upload.fileList = [];
										this.getData();
										this.$Message.success(this.$t("messageTip.saveSuccess"));
										this.formModal = false;
									}).catch(error => {
										console.info(error);
										this.$Message.error(this.$t("messageTip.frontError"));
									});
							}
						});
					});
				}, 500);
			},
			saveInitPass() {
				let entity = this.entity;
				entity["updateUserId"] = this.$store.state.user.userId;
				if (entity.password == "") {
					entity.password = "123456";
				}
				setTimeout(() => {
					this.loading = false;
					this.$nextTick(() => {
						this.loading = true;
						this.$refs.initPassFrom.validate(valid => {
							if (valid) {
								save(entity)
									.then(response => {
										this.entity = this.$options.data().entity;
										this.$Message.success(this.$t("messageTip.saveSuccess"));
										this.initPassModal = false;
									})
									.catch(error => {
										this.$Message.error(this.$t("messageTip.frontError"));
									});
							}
						});
					});
				}, 500);
			},
			saveCancel() {
				this.entity = this.$options.data().entity;
			},
			handleSelectRow(selection) {
				this.pks = [];
				let list = this.$refs.selection.getSelection();
				let that = this;
				list.forEach(function(value, index, array) {
					that.pks.push(value.id);
				});
			},
			changePage(page) {
				this.searchFrom.page = page;
				this.getData();
			},
			setUploadList(list) {
				this.uploadList = list;
				this.entity.userLogo = this.uploadList[0].url;
			}
		}
	};
</script>

<style>
	.demo-upload-list {
		display: inline-block;
		width: 60px;
		height: 60px;
		text-align: center;
		line-height: 60px;
		border: 1px solid transparent;
		border-radius: 4px;
		overflow: hidden;
		background: #fff;
		position: relative;
		box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
		margin-right: 4px;
	}

	.demo-upload-list img {
		width: 100%;
		height: 100%;
	}

	.demo-upload-list-cover {
		display: none;
		position: absolute;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
		background: rgba(0, 0, 0, 0.6);
	}

	.demo-upload-list:hover .demo-upload-list-cover {
		display: block;
	}

	.demo-upload-list-cover i {
		color: #fff;
		font-size: 20px;
		cursor: pointer;
		margin: 0 2px;
	}
</style>
