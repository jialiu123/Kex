<template>
	<li class="dropmenu sort" :class="showItem?'active':''" @mouseenter="handleMouseenter" @mouseleave="handleMouseleave">
		<div class="drop-title" @click="handleClick">
			<input type="text" :placeholder="showName" readonly="readonly" unselectable="on" autocomplete="off" />
			<i class="edge"></i>
		</div>
		<dl class="drop-menu" v-show="showItem">
			<dd v-for="(item,index) in menuList" @click="selectItem(item)" :key="item.index" :class="selectedIndex == index ? 'selected' : ''">{{item.name}}</dd>
		</dl>
	</li>
</template>

<script>
	export default {
		name: 'drop_down',
		props: {
			menuList: {
				type: Array,
				default: () => []
			},
			trigger: {
				type: String,
				default: "hover"
			}
		},
		data() {
			return {
				selectedIndex: 0,
				showItem: false,
				showName: this.menuList[0].name
			}
		},
		watch: {
		    showName(val) {
		      this.$emit('on-item-click', val);
		    }
		},
		methods: {
			handleClick() {
				if (this.trigger !== 'click') {
					return false;
				}
				this.showItem = !this.showItem;
			},
			handleMouseenter() {
				if (this.trigger !== 'hover') {
					return false;
				}
				if (this.timeout) clearTimeout(this.timeout);
				this.timeout = setTimeout(() => {
					this.showItem = true;
				}, 250);
			},
			handleMouseleave() {
				if (this.trigger !== 'hover') {
					return false;
				}
				if (this.timeout) {
					clearTimeout(this.timeout);
					this.timeout = setTimeout(() => {
						this.showItem = false;
					}, 150);
				}
			},
			selectItem(item) {
				this.showName = item.name;
				this.showItem = !this.showItem;
			}
		},
		mounted() {}
	}
</script>

<style lang="less">

</style>
