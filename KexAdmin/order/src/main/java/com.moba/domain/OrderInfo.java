package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author li, bai, qiang
 * @version 2019-03-04 14:10
 * @date 2019-03-04 14:10
 */
@ApiModel(value = "OrderInfo")
@Data
public class OrderInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "订单ID")
    private Integer id;

    @ApiModelProperty(value = "交易流水号")
    private String tradeIndex;

    @ApiModelProperty(value = "10 消费品订单")
    private Integer orderType;

    @ApiModelProperty(value = "订单产品数量")
    private Integer orderNum;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderMoney;

    @ApiModelProperty(value = "-10-已取消 0-已预订 10-等待发货 20-已发货 30-已到货 40-确认收货 90-关闭 ")
    private Integer orderState;

    @ApiModelProperty(value = "支付方式：0、线下支付（货到付款）1：拍拍，2：银行汇款 ")
    private Integer payType;

    @ApiModelProperty(value = "电子币支付")
    private BigDecimal moneyBi;

    @ApiModelProperty(value = "收货地址ID")
    private Integer shippingAddressId;

    @ApiModelProperty(value = "下单时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDate;

    @ApiModelProperty(value = "发票类型：0：普通发票；1：增值税发票")
    private Integer invoiceType;

    @ApiModelProperty(value = "发票抬头")
    private String invoicesPayable;

    @ApiModelProperty(value = "发货时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String deliveryTime;

    @ApiModelProperty(value = "发票状态：0：未发送；1：已发送；2：已接收")
    private Integer invoiceStatus;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "添加日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addDate;

    @ApiModelProperty(value = "购买的产品所属企业ID, 服务中心，区域经理，没有企业ID所以是-100")
    private Integer entpId;

    @ApiModelProperty(value = "企业名称")
    private String entpName;

    @ApiModelProperty(value = "添加人ID")
    private Integer addUserId;

    @ApiModelProperty(value = "添加人姓名")
    private String addUserName;

    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    @ApiModelProperty(value = "更新人ID")
    private Integer updateUserId;

    @ApiModelProperty(value = "运费价格（总价格）")
    private BigDecimal matflowPrice;

    @ApiModelProperty(value = "支付时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payDate;

    @ApiModelProperty(value = "订单总重量")
    private BigDecimal orderWeight;

    @ApiModelProperty(value = "是否包邮 0 不包邮 1 包邮.付款后根据合并付款情况才能确认是否包邮状态,此前都为默认值")
    private Integer isFreeship;

    @ApiModelProperty(value = "订单价格是否修改过")
    private Integer isPriceModify;

    @ApiModelProperty(value = "修改前商品价格:ORDER_MONEY修改前的价格")
    private BigDecimal priceModifyOld;

    @ApiModelProperty(value = "订单价格修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date priceModifyDate;

    @ApiModelProperty(value = "订单价格价格备注")
    private String priceModifyRemark;

    @ApiModelProperty(value = "修改前订单运费价格:MATFLOW_PRICE修改前的价格")
    private BigDecimal matflowPriceModifyOld;

    @ApiModelProperty(value = "修改者的用户ID")
    private Integer priceModifyUserId;

    @ApiModelProperty(value = "收货地址联系人姓名")
    private String relName;

    @ApiModelProperty(value = "收货地址联系人手机号码")
    private String relPhone;

    @ApiModelProperty(value = "收货地址联系人电话号码")
    private String relTel;

    @ApiModelProperty(value = "收货地址所在省")
    private Integer relProvince;

    @ApiModelProperty(value = "收货地址所在市")
    private Integer relCity;

    @ApiModelProperty(value = "收货地址所在地区")
    private Integer relRegion;

    @ApiModelProperty(value = "收货地址联系地址")
    private String relAddr;

    @ApiModelProperty(value = "收货地址电子邮件")
    private String relEmail;

    @ApiModelProperty(value = "收货地址邮政编码")
    private Integer relZip;

    @ApiModelProperty(value = "优惠券ID")
    private Integer yhqId;

    @ApiModelProperty(value = "优惠券提示语")
    private String yhqTipDesc;

    @ApiModelProperty(value = "支付交易号(银联,支付宝返回的交易流水号)")
    private String tradeNo;

    @ApiModelProperty(value = "银联订单号")
    private String tradeMergerIndex;

    @ApiModelProperty(value = "确认收货时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date qrshDate;

    @ApiModelProperty(value = "订单成本总价格")
    private BigDecimal orderMoneyCost;

    @ApiModelProperty(value = "")
    private String orderPassword;

    @ApiModelProperty(value = "订单失效日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @ApiModelProperty(value = "发货时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fahuoDate;

    @ApiModelProperty(value = "是否操作中")
    private Integer isOpt;

    @ApiModelProperty(value = "现金支付")
    private BigDecimal moneyCash;

    @ApiModelProperty(value = "支付平台：10：PC,20：WAP,31：APP_ANDROID,32：APP_IPHONE")
    private Integer payPlatform;

    @ApiModelProperty(value = "没有打折前金额")
    private BigDecimal noDisMoney;

    @ApiModelProperty(value = "利润")
    private BigDecimal profitMoney;

    @ApiModelProperty(value = "利润比例")
    private BigDecimal profitBl;

    @ApiModelProperty(value = "订单结束时间（交易完成后7天，自动ORDER_STATE=90，订单结束不能退换货）")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishDate;

    @ApiModelProperty(value = "是否评价")
    private Integer isComment;

    @ApiModelProperty(value = "是否需要发票 0：不需要 1：需要")
    private Integer fpState;

    @ApiModelProperty(value = "企业税号")
    private String entpChargeNo;

    @ApiModelProperty(value = "发票税号")
    private String invoicesNo;

    @ApiModelProperty(value = "订单详情类")
    private List<OrderInfoDetails> orderInfoDetailsList;


}