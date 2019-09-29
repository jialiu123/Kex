<template>
	<div>
		<Modal v-model="formModal" :title="$t('entpInfoManager.modalTitle')" @on-ok="save" @on-cancel="saveCancel" :loading="loading"
		 :mask-closable="false">
			<Form ref="saveFrom" :model="entity" :label-width="80" :rules="rules">
				<FormItem prop="entpName" :label="$t('entpInfoManager.entpName')">
					<Input v-model="entity.entpName" :placeholder="$t('entpInfoManager.entpName')"></Input>
				</FormItem>
				<!-- <FormItem prop="realName" :label="$t('entpInfoManager.realName')">
					<Input v-model="entity.realName" :placeholder="$t('entpInfoManager.realName')"></Input>
				</FormItem> -->
				
				<FormItem prop="idCardZm" :label="$t('entpInfoManager.idCardZm')">
					<uploadInput ref="uploadInputRef" successReturn="setIdCardZm"  :uploadFileCount="1" :uploadList="uploadList"
					 @setIdCardZm='setIdCardZm'></uploadInput>
				</FormItem>
				<FormItem prop="idCardFm" :label="$t('entpInfoManager.idCardFm')">
					<uploadInput ref="uploadInputRef" successReturn="setIdCardFm" :uploadFileCount="1" :uploadList="uploadList"
					 @setIdCardFm='setIdCardFm'></uploadInput>
				</FormItem>
			</Form>
		</Modal>
	</div>
</template>

<script>
	import {save} from '@/api/manager/entpInfo';
	import {formatDate} from '@/libs/tools';
	import {mapGetters} from 'vuex';
	import {baseUrl} from '@/libs/util';
	import {upload} from '@/api/upload';
	import uploadInput from '@/components/upload/upload'
	export default {
		components: {
		  uploadInput
		},
		data() {
			return {
				baseUrl: baseUrl,
				upload: upload,
				formModal: true,
				modalTitle: "",
				entity: {},
				loading: true,
				imgUrl: '',
				visible: false,
				uploadList: [],
			};
		},
		mounted: function() {
			this.getData();
		},
		computed: {
			rules() {
				return {
					entpName: [{
						required: true,
						message: this.$t('entpInfoManager.entpName') + this.$t('validateTip.notNull')
					}],
					
				}
			},
		},
		methods: {
			parentMethod(list){
				this.$refs.uploadInputRef.childMethod(list); 
			},
			setIdCardZm(list){
				this.uploadList = list;
				this.entity.imgIdCardZm = this.uploadList[0].url;
			},
			setIdCardFm(list){
				this.uploadList = list;
				this.entity.imgIdCardFm = this.uploadList[0].url;
			},
			getData() {
				console.info("===getData===")
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
									// this.getData();
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
			},
		}
	};
</script>

