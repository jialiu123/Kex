<template>
	<div>
		<button id="go-top" @click="back" v-show="backTop"><i class="edge"></i>TOP</button>
	</div>
</template>

<script>
	import { scrollTop } from '@/libs/util'
	import { on, off } from '@/libs/tools'
	export default {
		name: 'GoTop',
		data() {
			return {
				container:'#app',
				duration:1000,
				height:400,
				backTop:false
			};
		},
		computed: {
		  containerEle () {
		    return this.container === window ? window : document.querySelector(this.container)
		  }
		},
		mounted () {
		  on(this.containerEle, 'scroll', this.handleScroll,true)
		  on(this.containerEle, 'resize', this.handleScroll,true)
		},
		beforeDestroy () {
		  off(this.containerEle, 'scroll', this.handleScroll,true)
		  off(this.containerEle, 'resize', this.handleScroll,true)
		},
		methods: {
			handleScroll () {
			  this.backTop = this.containerEle.scrollTop >= this.height;
			},
			back () {
			  let target = typeof this.container === 'string' ? this.containerEle : (document.documentElement || document.body)
			  const sTop = target.scrollTop
			  scrollTop(this.containerEle, sTop, 0, this.duration)
			}
		}
	};
</script>
