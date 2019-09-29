<template>
	<div>
		<Form :model="searchFrom" :label-width="80">
			<Card style="margin-bottom: 10px;">
				<Input :placeholder="$t('msgManager.msgTitle')" v-model="searchFrom.f_msgTitleLike" style="width:200px"></Input>
				&nbsp;<Button type="primary" @click="getData(1)" icon="ios-search">{{$t('button.search')}}</Button>
			</Card>
		</Form>
		<Table border :data="tableData" :columns="tableColumns" stripe ref="selection" @on-selection-change="handleSelectRow()" />
		<div style="margin:10px 0px;text-align: center;">
			<Page :total="dataCount" :current="searchFrom.page" @on-change="changePage"></Page>
		</div>
		 <Drawer :title="$t('msgManager.msgDetail')" width="720" v-model="msgContent">
            <p :style="pStyle">{{entity.msgTitle}}</p>
            <div class="demo-drawer-profile">
                <Row>
                    <Col span="12">
                        {{$t('msgManager.sendUserName')}}: {{entity.sendUserName}}
                    </Col>
                    <Col span="12">
                        {{$t('msgManager.sendTime')}}: {{new Date(entity.sendTime) | formatDate('yyyy-MM-dd hh:mm')}}
                    </Col>
                </Row>
				<Divider />
			  <p :style="pStyle">{{entity.msgContent}}</p>
			   <p :style="pStyle" style="text-align: center;" v-show="entity.infoState != 3">
				  <Button type="error" icon="md-close-circle" @click="removeRecycle">{{$t('button.removeRecycle')}}</Button>
			   </p>
            </div>
		</Drawer>
	</div>
</template>

<script>
	import { mapState, mapGetters, mapMutations, mapActions } from 'vuex'
	import {list} from '@/api/manager/msg';
	import {formatDate} from '@/libs/tools';
	export default {
		name: 'dataPane',
		props: {
		  infoState: {
			type: String
		  }
		},
		data() {
			return {
				pStyle: {
                    fontSize: '16px',
                    color: 'rgba(0,0,0,0.85)',
                    lineHeight: '24px',
                    display: 'block',
                    marginBottom: '16px'
                },
				dataCount: 0,
				tableData: [],
				msgTypeList:[],
				msgContent: false,
				entity:[],
				searchFrom: {
					page: 1,
					infoState:this.infoState,
					userId:this.$store.state.user.userId,
				},
			};
		},
		mounted: function() {
			this.getData();
		},
		computed: {
			tableColumns() {
				let that = this;
				return [{
						title:this.$t('msgManager.tableIndex'),
						type: 'index',
						width: 60,
						align: 'center',
					},
					{
						title: this.$t('msgManager.msgTitle'),
						align: 'center',
						key: 'msgTitle'
					},
					{
						title: this.$t('msgManager.sendUserName'),
						align: 'center',
						key: 'sendUserName'
					},
					{
						title: this.$t('msgManager.msgType'),
						align: 'center',
						key: 'msgType',
						render: (h, params) => {
							let showName = "";
							for (let i = 0; i < this.msgTypeList.length; i++) {
								if (this.msgTypeList[i].index == params.row.msgType) {
									showName = this.msgTypeList[i].showName;
									break;
								}
							}
							return h('div',showName);
						}
					},
					{
						title: this.$t('msgManager.sendTime'), 
						align: 'center',
						key: 'sendTime',
						render: (h, params) => {
							return h('div',
								formatDate(new Date(params.row.sendTime), 'yyyy-MM-dd hh:mm')
							)
						}
					},
					{
						title: this.$t('button.operation'),
						key: 'action',
						fixed: 'right',
						align: 'center',
						width: 130,
						render: (h, params) => {
							let view;
							view = h('Button', {
								props: {
									type: 'primary',
								},
								on: {
									click: () => {
										that.msgContent = !that.msgContent;
										that.entity = that.tableData[params.index];
										//这个地方调用接口修改站内信的状态
										if(params.row.infoState == 1){
											that.updateMsgState(params.row.id);
										}
									}
								}
							}, this.$t('button.view'));
							return h('div', [view]);
						}
					}
				]
			}
		},
		methods: {
			...mapActions([
			  'hasRead'
			]),
			getData(page) {
				if(page){
					this.searchFrom.page = page;
				}
				list(this.searchFrom).then(response => {
					const data = response.data;
					this.dataCount = data.data.dataCount;
					this.msgTypeList = data.data.msgTypeList;
					this.tableData = data.data.entityList;
				}).catch(error => {});
			},
			removeRecycle(){
				this.$Modal.confirm({
					title: this.$t('messageTip.tip'),
					content: '<p>'+ this.$t('messageTip.confirm') +'</p>',
					onOk: () => {
						let entity = {
							id:this.entity.id,
							infoState:3,
							oldInfoState:this.entity.infoState
						};
						this.hasRead(entity).then(response => {
							this.getData();
						}).catch(error => {});
					}
				});
			},
			updateMsgState(id){
				let entity = {
					id:id,
					infoState:2
				};
				this.hasRead(entity).then(response => {
					this.getData();
				}).catch(error => {});
			},
			changePage(page) {
				this.searchFrom.page = page;
				this.getData();
			}
		}
	};
</script>
