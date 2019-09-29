<template>
	<div class="floor recommend">
		<div class="floor-head cf">
			<h2>{{title}}<span>{{subTitle}}</span></h2>
			<ul class="categories">
				<li :class="index == showIndexItem ? 'on' : ''" v-for="(item,index) in dataList" :key="item.index" @click="change(index)">
					<a><i class="icon icon-dota2-sm"></i><span>DOTA2</span></a>
				</li>
			</ul>
			<a class="more" href="/comm">进入专题</a>
		</div>
		<div class="lists-wrap">
			<floor-list-two-item v-for="(item,index) in dataList" :key="item.index" v-show="index == showIndexItem" :isShowLeft="isShowLeft" :dataList="dataList">
				
			</floor-list-two-item>
		</div>
	</div>
</template>

<script>
	import FloorListTwoItem from './floor-list-two-item.vue'
	export default {
		components:{
			FloorListTwoItem
		},
		props:{
			title:{
				type: String
			},
			subTitle:{
				type: String
			},
			dataList:{
				type: Array,
				default:() => []
			},
			isShowLeft:{
				type:Boolean,
				default:true
			},
			showIndex:{
				type: Number,
				default:0
			}
		},
		name: 'FloorListTwo',
		data() {
			return {
				showIndexItem:this.showIndex
			};
		},
		mounted: function() {
		},
		methods: {
			change(index) {
				this.showIndexItem = index;
			}
		},
	};
</script>
