<template>
  <div class="full-screen-btn-con">
    <Tooltip content="首页">
			<router-link to="/index" target="_blank">
        <Icon type="ios-home" :size="23"></Icon>
			</router-link>
    </Tooltip>
  </div>
</template>

<script>
export default {
  name: 'Index',
  computed: {
  },
  props: {
  },
  methods: {
  },
  mounted () {}
}
</script>

<style lang="less">
.full-screen-btn-con .ivu-tooltip-rel{
  height: 64px;
  line-height: 56px;
  a{
    color: #515a6e;
  }
}
</style>
