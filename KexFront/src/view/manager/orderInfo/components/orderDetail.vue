<template>
  <div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableClass">
      <tr>
        <th colspan="4">订单基本信息</th>
      </tr>
      <tr>
        <td width="18%" nowrap="nowrap" class="title_item">{{$t('orderInfoManager.tradeIndex')}}:</td>
        <td>{{entity.tradeIndex}}</td>
        <td width="18%" nowrap="nowrap" class="title_item">{{$t('orderInfoManager.orderState')}}:</td>
        <td>
          <div
            v-for="item in orderStateList"
            :key="item.index"
            v-show="item.index == entity.orderState"
          >{{item.showName}}</div>
        </td>
      </tr>
      <tr>
        <td width="18%" nowrap="nowrap" class="title_item">{{$t('orderInfoManager.orderNum')}}:</td>
        <td>{{entity.orderNum}}</td>
        <td width="18%" nowrap="nowrap" class="title_item">{{$t('orderInfoManager.orderMoney')}}:</td>
        <td>{{entity.orderMoney}}</td>
      </tr>
      <tr>
        <td width="18%" nowrap="nowrap" class="title_item">{{$t('orderInfoManager.payType')}}:</td>
        <td colspan="3">
          <div
            v-for="item in payTypeList"
            :key="item.index"
            v-show="item.index == entity.payType"
          >{{item.showName}}</div>
        </td>
      </tr>
      <tr>
        <td
          width="18%"
          nowrap="nowrap"
          class="title_item"
        >{{$t('orderInfoManager.orderDateManager')}}:</td>
        <td colspan="3">
          {{$t('orderInfoManager.orderDate')}}:{{new Date(entity.orderDate) | formatDate('yyyy-MM-dd hh:mm:ss')}}
          <br>
          {{$t('orderInfoManager.payDate')}}:{{new Date(entity.payDate) | formatDate('yyyy-MM-dd hh:mm:ss')}}
          <br>
          {{$t('orderInfoManager.deliverDate')}}:{{new Date(entity.fahuoDate) | formatDate('yyyy-MM-dd hh:mm:ss')}}
          <br>
          {{$t('orderInfoManager.receivDate')}}:{{new Date(entity.qrshDate) | formatDate('yyyy-MM-dd hh:mm:ss')}}
          <br>
        </td>
      </tr>
      <tr>
        <td colspan="4">
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableClass">
            <tr>
              <th colspan="5">{{$t('orderInfoManager.orderDetailInfo.tableName')}}</th>
            </tr>
            <tr>
              <th>{{$t('orderInfoManager.orderDetailInfo.commName')}}</th>
              <th>{{$t('orderInfoManager.orderDetailInfo.commNum')}}</th>
              <th>{{$t('orderInfoManager.orderDetailInfo.commUnitPrice')}}</th>
              <th>{{$t('orderInfoManager.orderDetailInfo.commSumPrice')}}</th>
            </tr>
            <tr v-for="item in entity.orderInfoDetailsList" :key="item.index">
              <td align="center">{{item.commName}}</td>
              <td align="center">{{item.goodCount}}</td>
              <td align="center">{{item.goodPrice}}</td>
              <td align="center">{{entity.goodSumPrice}}</td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  </div>
</template>
<script>
import { getOrderDetail } from "@/api/manager/orderInfo";
import { formatDate } from "@/libs/tools";
export default {
  name: "orderDetail",
  props: {
    orderId: {
      type: Number,
      default: null
    }
  },
  data() {
    return {
      entity: {},
      payTypeList: [],
      orderStateList: []
    };
  },
  mounted: function() {},
  watch: {
    orderId: function(value) {
      if (value) {
        this.getData();
      }
    }
  },
  methods: {
    getData() {
      getOrderDetail(this.orderId)
        .then(response => {
          const data = response.data;
          this.entity = data.data.entity;
          this.payTypeList = data.data.payTypeList;
          this.orderStateList = data.data.orderStateList;
        })
        .catch(error => {});
    }
  }
};
</script>