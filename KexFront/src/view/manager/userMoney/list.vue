<template>
	<div>
		<Card style="margin-bottom: 10px;">
			<Tabs value="apply10">
				<TabPane :label="$t('userMoneyManager.apply10')" name="apply10">
					<dataPane typeValue="10"></dataPane>
				</TabPane>
			</Tabs>
		</Card>
	</div>
</template>

<script>
	import dataPane from './components/data-pane'
	import {formatDate} from '@/libs/tools';
	import {mapGetters} from 'vuex';
	export default {
		components: {
		  dataPane
		},
		data() {
			return {
			};
		},
		mounted: function() {
		},
		computed: {
		},
		methods: {
			handleSelect (index) {
			  this.currentType = index
			},
		}
	};
</script>
