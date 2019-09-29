<template>
	<ul class="slide-nav" id="home-cate">
		<li class="slide-nav-item" @mouseover="hoverIn(0)" @mouseout="hoverOut(0)">
			<a href="/dota2">
				<i class="icon icon-dota2-sm"></i>
				<span>DOTA2</span>
				<i class="icon icon-menu-arrow"></i>
			</a>
			<div class="slide-nav-box" v-show="navIndex == 0">
				<ul>
					<li><a href="/dota2?filter={&quot;类型&quot;:&quot;信使&quot;}">信使</a></li>
					<li><a href="/dota2?filter={&quot;类型&quot;:&quot;捆绑包&quot;}">捆绑包</a></li>
					<li><a href="/dota2?filter={&quot;类型&quot;:&quot;可佩带&quot;}">可佩带</a></li>
					<li><a href="/dota2?filter={&quot;类型&quot;:&quot;守卫&quot;}">守卫</a></li>
					<li><a href="/dota2?filter={&quot;类型&quot;:&quot;珍藏&quot;}">珍藏</a></li>
					<li><a href="/dota2?filter={&quot;类型&quot;:&quot;宝石 / 符文&quot;}">宝石 / 符文</a></li>
					<li><a href="/dota2?filter={&quot;类型&quot;:&quot;载入画面&quot;}">载入画面</a></li>
					<li><a href="/dota2?filter={&quot;类型&quot;:&quot;游戏界面皮肤&quot;}">游戏界面皮肤</a></li>
				</ul>
			</div>
		</li>
		<li class="slide-nav-item" @mouseover="hoverIn(1)" @mouseout="hoverOut(1)">
			<a href="/csgo">
				<i class="icon icon-csgo-sm"></i>
				<span>CS:GO</span>
				<i class="icon icon-menu-arrow"></i>
			</a>
			<div class="slide-nav-box" v-show="navIndex == 1">
				<ul>
					<li><a href="/csgo/buqiang">步枪</a></li>
					<li><a href="/csgo/weixingchongfengqiang">微型冲锋枪</a></li>
					<li><a href="/csgo/shouqiang">手枪</a></li>
					<li><a href="/csgo/jiqiang">机枪</a></li>
					<li><a href="/csgo/bishou">匕首</a></li>
					<li><a href="/csgo/sandanqiang">霰弹枪</a></li>
					<li><a href="/csgo/shoutao">手套</a></li>
					<li><a href="/csgo/xiangzi">武器箱</a></li>
					<li><a href="/csgo/yinhua">印花</a></li>
					<li><a href="/csgo/tuya">涂鸦</a></li>
					<li><a href="/csgo/gongju">工具</a></li>
					<li><a href="/csgo/yinyuehe">音乐盒</a></li>
					<li><a href="/csgo/yaoshi">钥匙</a></li>
				</ul>
			</div>
		</li>
		<li class="slide-nav-item" @mouseover="hoverIn(2)" @mouseout="hoverOut(2)">
			<a href="/h1z1">
				<i class="icon icon-h1z1-sm"></i>
				<span>H1Z1</span>
				<i class="icon icon-menu-arrow"></i>
			</a>
			<div class="slide-nav-box" v-show="navIndex == 2">
				<ul>
					<li><a href="/h1z1/qiangxie">枪械</a></li>
					<li><a href="/h1z1/mianju">面具</a></li>
					<li><a href="/h1z1/xiangzi">箱子</a></li>
					<li><a href="/h1z1/beibao">背包</a></li>
					<li><a href="/h1z1/yifu">衣服</a></li>
					<li><a href="/h1z1/kuzi">裤子</a></li>
					<li><a href="/h1z1/maozi">帽子</a></li>
					<li><a href="/h1z1/bishou">匕首</a></li>
					<li><a href="/h1z1/xiezi">鞋子</a></li>
					<li><a href="/h1z1/weijin">围巾</a></li>
					<li><a href="/h1z1/yanjin">眼镜</a></li>
					<li><a href="/h1z1/shoutao">手套</a></li>
				</ul>
			</div>
		</li>
		<li>
			<a href="/help/1vd000002" target="_blank" class="newer-guide">新手指南</a>
		</li>
	</ul>
</template>

<script>
	export default {
		name: 'SlideNav',
		data() {
			return {
				navIndex:'-1'
			};
		},
		mounted: function() {},
		methods: {
			hoverIn(index) {
				this.navIndex = index;
			},
			hoverOut(index) {
				this.navIndex = -1;
			}
		},
	};
</script>
