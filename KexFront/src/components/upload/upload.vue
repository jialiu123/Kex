<template>
	<div>
		<div class="demo-upload-list" v-for="item in uploadListThis" :key="item.index">
			<template>
				<img :src="item.url">
				<div class="demo-upload-list-cover">
					<Icon type="ios-eye-outline" @click.native="handleView(item)"></Icon>
					<Icon type="ios-trash-outline" @click.native="handleRemove(item)"></Icon>
				</div>
			</template>
		</div>

		<Upload ref="upload" :show-upload-list="false" :default-file-list="uploadList" :on-success="handleSuccess" :format="['jpg','jpeg','png']"
		 :max-size="2048" :on-format-error="handleFormatError" :on-exceeded-size="handleMaxSize" :before-upload="handleBeforeUpload"
		 :multiple="false" type="drag" :action="baseUrl + uploadUrl" style="display: inline-block;width:58px;">
			<div style="width: 58px;height:58px;line-height: 58px;">
				<Icon type="ios-camera" size="20"></Icon>
			</div>
		</Upload>
		<Modal v-model="visible">
			<img :src="imgUrl" v-if="visible" style="width: 100%" />
		</Modal>
	</div>
</template>

<script>
	import {baseUrl} from '@/libs/util';
	import {uploadUrl} from '@/api/upload';
	export default {
		name: 'uploadInput',
		props: {
			uploadList: {
				type: Array
			},
			uploadFileCount:{
				type: Number,
				default:1
			},
		},
		components: {},
		data() {
			return {
				baseUrl: baseUrl,
				uploadUrl: uploadUrl,
				uploadListThis:this.uploadList,
				imgUrl: '',
				visible: false,
			}
		},
		mounted () {
		},
		watch: {
			uploadList(newValue, oldValue) {
				this.uploadListThis = newValue;
			}
		},
		methods: {
			handleView(item) {
				this.imgUrl = item.url;
				this.visible = true;
			},
			handleRemove(file) {
				const fileList = this.uploadListThis;
				this.uploadListThis.splice(fileList.indexOf(file), 1);
				this.$emit('setUploadList',this.uploadListThis);
			},
			handleSuccess(response, file, fileList) {
				let data = response.data;
				if (this.uploadFileCount == 1) {
					if (this.$refs.upload.fileList.length > 1) {
						this.$refs.upload.fileList.splice(0, 1);
					}
					file.url = data.httpUrl;
					file.name = data.fileName;

					let newsList = []
					this.$refs.upload.fileList.forEach(function(value, index, array) {
						let newFile = {
							name: value.name,
							url: value.url
						}
						newsList.push(newFile);
					})
					this.uploadListThis = newsList
				} else {
					if (this.uploadListThis.length + 1 > this.uploadFileCount) {
						this.$Message.error("上传数量超出限制");
						this.$refs.upload.fileList.splice(this.$refs.upload.fileList.length - 1, 1);
						return;
					} else {
						let newsList = []
						this.$refs.upload.fileList.forEach(function(value, index, array) {
							newsList.push(value.url);
						})
						this.uploadListThis = newsList
					}
				}
				this.returnParMethod();
			},
			handleFormatError(file) {
				this.$Notice.warning({
					title: 'The file format is incorrect',
					desc: 'File format of ' + file.name + ' is incorrect, please select jpg or png.'
				});
			},
			handleMaxSize(file) {
				this.$Notice.warning({
					title: 'Exceeding file size limit',
					desc: 'File  ' + file.name + ' is too large, no more than 2M.'
				});
			},
			handleBeforeUpload() {
				const check = this.uploadList.length < 5;
				if (!check) {
					this.$Notice.warning({
						title: 'Up to five pictures can be uploaded.'
					});
				}
				return check;
			},
			returnParMethod(){
				this.$emit('setUploadList',this.uploadListThis);
			}
		}
	}
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
		box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
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
		background: rgba(0, 0, 0, .6);
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
