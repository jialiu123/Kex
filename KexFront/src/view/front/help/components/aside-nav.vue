<template>
	<ul class="aside-nav">
		<li v-for="item in dataList" :key="item.index">
			<h3  @click="collapse(item)">{{item.modName}}<i class="icon " :class="item.map.isShow ? 'icon-opened':'icon-closed'"></i></h3>
			<transition 
			enter-active-class="animated bounceIn"
			leave-active-class="animated bounceOut"
			:duration="150">
				<ul v-show="item.map.isShow">
					<li v-for="(item,index) in item.map.helpModuleSonList" :class="index == indexShow ? 'on' : ''" style="cursor: pointer;" :key="item.index" @click="getPageData(item,index)">
						{{item.modName}}
					</li>
				</ul>
			</transition>
		</li>
	</ul>
</template>

<script>
	export default {
		name: 'AsideNav',
		props: {
			dataList: {
				type: Array,
				default: () => []
			}
		},
		data() {
			return {
				indexShow:-1,
			};
		},
		mounted: function() {
		},
		methods: {
			collapse(item) {
				item.map.isShow = !item.map.isShow;
			},
			getPageData(item,index){
				this.indexShow = index;
				this.$emit("getPageData",item);
			}
		},
	};
</script>