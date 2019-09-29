<template>
	<div class="floor-list floor-swiper">
		<div class="floorhead-img" v-show="isShowLeft">
			<a href="/dota2" target="_blank">
				<img src="https://oss.stmbuy.com/static/upload/bannerpic/5b04e08716c47.png" alt="floor-tit-img">
			</a>
		</div>
		<ul class="goods-list swiper-wrapper">
			<li class="swiper-slide">
				<a href="/dota2/item-43450111cfd5a8b8" target="_blank">
					<div class="goods-item">
						<div class="goods-img">
							<img src="https://oss.stmbuy.com/static/image/game/570/43450111cfd5a8b8.png" alt="纯正 齿樱Genuine Serrakura">
							<div class="special-icon">
							</div>
						</div>
						<p class="tit" title="Genuine Serrakura">纯正 齿樱</p>
						<p class="sec-tit">在售数：<span>27</span></p>
						<i class="icon icon-dota2-sm"></i>
					</div>
					<div class="goods-bottom">
						<p class="">
							<span>最低价</span>
							<span>¥30.00 </span>
						</p>
					</div>
				</a>
			</li>
			<li class="swiper-slide">
				<a href="/dota2/item-c9823f157d19bd8c" target="_blank">
					<div class="goods-item">
						<div class="goods-img">
							<img src="https://oss.stmbuy.com/static/image/game/570/c9823f157d19bd8c.png" alt="尊享 剑心之遗Exalted Bladeform Legacy">
							<div class="special-icon">
							</div>
						</div>
						<p class="tit" title="Exalted Bladeform Legacy">尊享 剑心之遗</p>
						<p class="sec-tit">在售数：<span>6</span></p>
						<i class="icon icon-dota2-sm"></i>
					</div>
					<div class="goods-bottom">
	
						<p class="">
							<span>最低价</span>
							<span>
								¥154.90 </span>
						</p>
					</div>
				</a>
			</li>
			<li class="swiper-slide">
				<a href="/dota2/item-aa7f93f9a32c1df0" target="_blank">
					<div class="goods-item">
						<div class="goods-img">
							<img src="https://oss.stmbuy.com/static/image/game/570/aa7f93f9a32c1df0.png" alt="心渊魔角Fractal Horns of Inner Abysm">
							<div class="special-icon">
							</div>
						</div>
						<p class="tit" title="Fractal Horns of Inner Abysm">心渊魔角</p>
						<p class="sec-tit">在售数：<span>8</span></p>
						<i class="icon icon-dota2-sm"></i>
					</div>
					<div class="goods-bottom">
	
						<p class="">
							<span>最低价</span>
							<span>¥154.40 </span>
						</p>
					</div>
				</a>
			</li>
			<li class="swiper-slide">
				<a href="/dota2/item-b34d828104f67ab0" target="_blank">
					<div class="goods-item">
						<div class="goods-img">
							<img src="https://oss.stmbuy.com/static/image/game/570/b34d828104f67ab0.png" alt="尊享 噬魔之王Exalted Demon Eater">
							<div class="special-icon">
							</div>
						</div>
						<p class="tit" title="Exalted Demon Eater">尊享 噬魔之王</p>
						<p class="sec-tit">在售数：<span>8</span></p>
						<i class="icon icon-dota2-sm"></i>
					</div>
					<div class="goods-bottom">
	
						<p class="">
							<span>最低价</span>
							<span>
								¥148.20 </span>
						</p>
					</div>
				</a>
			</li>
			<li class="swiper-slide">
				<a href="/dota2/item-c5f772810a241822" target="_blank">
					<div class="goods-item">
						<div class="goods-img">
							<img src="https://oss.stmbuy.com/static/image/game/570/c5f772810a241822.png" alt="龙宫浩劫Havoc of Dragon Palace">
							<div class="special-icon">
							</div>
						</div>
						<p class="tit" title="Havoc of Dragon Palace">龙宫浩劫</p>
						<p class="sec-tit">在售数：<span>11</span></p>
						<i class="icon icon-dota2-sm"></i>
					</div>
					<div class="goods-bottom">
	
						<p class="">
							<span>最低价</span>
							<span>
								¥5.97 </span>
						</p>
					</div>
				</a>
			</li>
			<li class="swiper-slide">
				<a href="/dota2/item-a686e001279cf7af" target="_blank">
					<div class="goods-item">
						<div class="goods-img">
							<img src="https://oss.stmbuy.com/static/image/game/570/a686e001279cf7af.png" alt="铭刻 战鬼双刃Inscribed Blades of Voth Domosh">
							<div class="special-icon">
							</div>
						</div>
						<p class="tit" title="Inscribed Blades of Voth Domosh">铭刻 战鬼双刃</p>
						<p class="sec-tit">在售数：<span>6</span></p>
						<i class="icon icon-dota2-sm"></i>
					</div>
					<div class="goods-bottom">
	
						<p class="">
							<span>最低价</span>
							<span>
								¥149.99 </span>
						</p>
					</div>
				</a>
			</li>
		</ul>
	</div>
</template>

<script>
	export default {
		name: 'FloorListTwoItem',
		props:{
			dataList:{
				type: Array,
				default:() => []
			},
			isShowLeft:{
				type:Boolean,
				default:true
			},
		},
		data() {
			return {

			};
		},
		mounted: function() {},
	};
</script>
