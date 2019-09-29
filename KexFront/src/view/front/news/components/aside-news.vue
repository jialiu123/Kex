<template>
	<aside class="related">
		<div class="hot-news">
			<div class="list-tit">
				<h2>热门新闻</h2>
			</div>
			<ul class="content">
				<li v-for="item in dataList" :key="item.index">
					<router-link :to="{name:'indexNewsDetail', params:{modId:modId,uuid:item.uuid}}">
						<div class="img">
							<img :src="item.imagePath">
						</div>
						<div class="detail">
							<p>{{item.title}}</p>
							<p>
								<span><i class="icon icon-watch"></i>{{item.viewCount}}</span>
							</p>
						</div>
					</router-link>
				</li>
			</ul>
		</div>
	</aside>
</template>

<script>
	export default {
		name: 'AsideNews',
		props: {
			dataList: {
				type: Array,
				default: () => []
			},
			modId:{
				type:String,
				default:''
			}
		},
		data() {
			return {
				
			};
		},
		mounted: function() {
			
		},
	};
</script>
