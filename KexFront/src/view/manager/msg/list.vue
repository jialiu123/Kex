<template>
	<div>
		<Card style="margin-bottom: 10px;">
			<Tabs value="data1">
				<TabPane :label="label1" name="data1">
					<dataPane infoState="1"></dataPane>
				</TabPane>
				<TabPane :label="label2" name="data2">
					<dataPane infoState="2"></dataPane>
				</TabPane>
				<TabPane :label="label3" name="data3">
					<dataPane infoState="3"></dataPane>
				</TabPane>
			</Tabs>
		</Card>
	</div>
	
</template>

<script>
	import {getCount} from '@/api/manager/msg';
	import {mapGetters} from 'vuex';
	import dataPane from './components/data-pane'
	export default {
		components: {
		  dataPane
		},
		data() {
			return {
				label1: (h) => {
                    return h('div', [
                        h('span', this.$t('msgManager.data1')),
                        h('Badge', {
                            props: {
                                count: this.unReadCount
                            },
							style:{
								marginLeft: "10px"
							}
                        })
                    ])
                },
				label2: (h) => {
				    return h('div', [
				        h('span', this.$t('msgManager.data2')),
				        h('Badge', {
							class:'gray-dadge',
				            props: {
				                count: this.readCount
				            },
							style:{
								marginLeft: "10px"
							}
				        })
				    ])
				},
				label3: (h) => {
				    return h('div', [
				        h('span', this.$t('msgManager.data3')),
				        h('Badge', {
							class:'gray-dadge',
				            props: {
				                count: this.recycleCount
				            },
							style:{
								marginLeft: "10px"
							}
				        })
				    ])
				}
			};
		},
		mounted: function() {
		},
		computed: {
			...mapGetters([
			  'readCount',
			  'unReadCount',
			  'recycleCount'
			])
		},
		methods: {
		}
	};
</script>
<style lang="less">
.ivu-tabs-tabpane{
	position: relative;
}
.gray-dadge .ivu-badge-count{
    background: gainsboro;
}
</style>
