<template>
	<div>
		<Modal v-model="userRegArgee" fullscreen :title="$t('RegisterFront.userRegArgee')">
			<div>This is a fullscreen modal</div>
		</Modal>
		<Modal v-model="registerModalFlag" class-name="index-modal" width="400px" @on-cancel="cancel" :mask-closable="false">
			<p slot="header" class="index-header">
				<span>{{$t('RegisterFront.register')}}</span>
			</p>
			<div class="register-content">
				<Form ref="registerFrom" :model="entity" class="comm-form" :rules="rules">
					<FormItem prop="userName">
						<Input v-model="entity.userName" type="email" :placeholder="$t('RegisterFront.userName')"></Input>
					</FormItem>
					<FormItem class="w-eight-two" prop="imgCaptcha">
						<Input v-model="entity.imgCaptcha" class="imgcaptcha pull-left" :placeholder="$t('RegisterFront.imgCaptcha')"></Input>
						<a class="captchaimg pull-right"><img :src="baseUrl + 'captcha/index/getCaptchaCode.jpg'"></a>
					</FormItem>
					<FormItem class="relative" prop="password">
						<Input v-model="entity.password" class="password" type="password" :placeholder="$t('RegisterFront.password')"></Input>
					</FormItem>
					<p class="user-agree unselect">
						<label class="checkbox">
							<input id="register-agree" name="agree" type="checkbox" checked="checked" />
							<i></i>
						</label>
						<label for="register-agree">{{$t('RegisterFront.userRegArgeeTip')}}</label>
						<strong><a @click="userRegArgee = true">《{{$t('RegisterFront.userRegArgee')}}》</a></strong>
					</p>
				</Form>
			</div>
			<div slot="footer" class="index-footer">
				<Button type="primary" size="large" long @click="register">{{$t('RegisterFront.register')}}</Button>
			</div>
		</Modal>
	</div>
</template>

<script>
	import {registerUser} from "@/api/login";
	import { baseUrl } from '@/libs/util'
	export default {
		name: 'Register',
		props: {
			registerModal: {
				type: Boolean,
				default: false
			}
		},
		watch: {
			registerModal(newValue, oldValue) {
				this.registerModalFlag = newValue;
			}
		},
		computed: {
			rules() {
				return {
					userName: [{
						required: true,
						type:"email",
						message:this.$t("RegisterFront.userName") + this.$t("validateTip.notNull")
					}],
					imgCaptcha: [{
						required: true,
						message:this.$t("RegisterFront.imgCaptcha") + this.$t("validateTip.notNull")
					}],
					password: [{
						required: true,
						message:this.$t("RegisterFront.password") + this.$t("validateTip.notNull")
					}],
				};
			},
		},
		data() {
			return {
				baseUrl:baseUrl,
				registerModalFlag:false,
				userRegArgee: false,
				entity: {},
			};
		},
		mounted: function() {
		},
		methods: {
			register(){
				this.$refs.registerFrom.validate(valid => {
					if (valid) {
						registerUser(this.entity).then(response => {
							this.$Message.success(this.$t("messageTip.registerSuccess"));
							this.registerModalFlag = !this.registerModalFlag;
							//TODO 偷懒的写法直接跳转登陆，让用户去登陆
							this.$router.push({
							  name: "login"
							})
						}).catch(error => {
						});
					}
				});
			},
			cancel(){
				this.$emit("cancel");
			}
		},
	};
</script>
<style lang="less">

</style>
