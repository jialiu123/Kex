<template>
  <div>
	    <p :style="pStyle">{{entity.msgTitle}}</p>
	    <div class="demo-drawer-profile">
	        <Row>
	            <Col span="12">
	                {{$t('userMoneyManager.cashCountBefore')}}: {{entity.cashCountBefore}}
	            </Col>
	            <Col span="12">
	                {{$t('userMoneyManager.cashCount')}}: {{entity.cashCount}}
	            </Col>
				<Col span="12">
				    {{$t('userMoneyManager.cashCountAfter')}}: {{entity.cashCountAfter}}
				</Col>
				 <Col span="12">
				    {{$t('userMoneyManager.cashPay')}}: {{entity.cashPay}}
				</Col>
	        </Row>
			<Divider />
	    </div>
		<Form :model="entity" :rules="rules" ref="saveFrom">
			<Row>
				<FormItem prop="infoState" :label="$t('userMoneyManager.infoState')" label-position="top">
					<Select v-model="entity.infoState" :placeholder="$t('userMoneyManager.infoState')">
						<Option :value="item.index" v-for="item in auditStateList" :key="item.index">{{item.showName}}</Option>
					</Select>
				</FormItem>
			</Row>
			<FormItem prop="auditMemo" :label="$t('userMoneyManager.auditMemo')" label-position="top">
				<Input type="textarea" v-model="entity.auditMemo" :rows="4" :placeholder="$t('userMoneyManager.auditMemo')" />
			</FormItem>
        </Form>
		<div class="demo-drawer-footer">
			<Button type="primary" @click="audit">{{$t('button.save')}}</Button>
        </div>
  </div>
</template>

<script>
import {getEntityById,audit} from '@/api/manager/userMoney';
import {formatDate} from '@/libs/tools';
export default {
  name: 'audit',
	props: {
	  id: {
	    type: Number
	  },
	},
	watch: {
	  id:function(value) {
		if (value) {
		  this.getData();
		}
	  }
	},
	data() {
		return {
			pStyle: {
			    fontSize: '16px',
			    color: 'rgba(0,0,0,0.85)',
			    lineHeight: '24px',
			    display: 'block',
			    marginBottom: '16px'
			},
			entity:{},
			auditStateList:[]
		};
	},
  mounted: function() {
  },
  computed: {
	  rules() {
	  	return {
	  		infoState: [{
	  			required: true,
	  			message: this.$t("userMoneyManager.infoState") + this.$t("validateTip.notNull")
	  		}],
	  		auditMemo: [{
	  			required: true,
	  			message: this.$t("userMoneyManager.auditMemo") + this.$t("validateTip.notNull")
	  		}]
	  	};
	  },
  },
  methods: {
  	getData() {
  		getEntityById(this.id).then(response => {
  			const data = response.data;
  			this.entity = data.data.entity;
  			this.auditStateList = data.data.auditStateList;
  		}).catch(error => {});
  	},
  	audit() {
  		let entity = this.entity;
		this.$nextTick(() => {
			this.$refs.saveFrom.validate((valid) => {
				if (valid) {
					audit(entity).then(response => {
						this.$emit("parReload");
						this.$Message.success(this.$t('messageTip.saveSuccess'));
					}).catch(error => {
						this.$Message.error(this.$t('messageTip.frontError'));
					});
				}
			})
		});
  	},
  }
}
</script>

<style>
    .demo-drawer-footer{
        width: 100%;
        position: absolute;
        bottom: 0;
        left: 0;
        border-top: 1px solid #e8e8e8;
        padding: 10px 16px;
        text-align: right;
        background: #fff;
    }
</style>