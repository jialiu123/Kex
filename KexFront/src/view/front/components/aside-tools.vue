<template>
	<div>
		<ul class="aside-tools">
			<li class="score-task">
				<a><i class="icon icon-task"></i><span>任务</span></a>
			</li>
			<li>
				<a href="http://wpa.b.qq.com/cgi/wpa.php?ln=1&amp;key=XzgwMDE5ODk3N180NzU4NDhfODAwMTk4OTc3XzJf" target="_blank">
					<i class="icon icon-zixun"></i>
					咨询
				</a>
			</li>
			<li>
				<a>
					<i class="icon icon-share"></i>
					分享
				</a>
				<div class="hover-pop left">
					<ul class="share bdsharebuttonbox bdshare-button-style0-16" data-bd-bind="1552876047080">
						<li><a class="js-hoverSwitchIcon" data-cmd="weixin" title="分享到微信"><i class="icon icon-share-weixin"></i> 微信分享
							</a></li>
						<li><a class="js-hoverSwitchIcon" data-cmd="sqq" title="分享到QQ"><i class="icon icon-share-qq"></i> QQ分享 </a></li>
						<li><a class="js-hoverSwitchIcon" data-cmd="tsina" title="分享到新浪微博"><i class="icon icon-share-weibo"></i> 新浪微博分享
							</a></li>
					</ul>
				</div>
			</li>
			<li>
				<a href="/help" target="_blank">
					<i class="icon icon-help"></i>
					帮助
				</a>
			</li>
		</ul>
	</div>
</template>

<script>
	export default {
		name: 'AsideTools',
		data() {
			return {

			};
		},
		mounted: function() {},
	};
</script>
