package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author li, bai, qiang
 * @version 2019-03-04 14:10
 * @date 2019-03-04 14:10
 */
@ApiModel(value = "OrderInfoDetails")
@Data
public class OrderInfoDetails extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "订单ID")
    private Integer orderId;

    @ApiModelProperty(value = "10 消费品订单")
    private Integer orderType;

    @ApiModelProperty(value = "产品类别")
    private Integer clsId;

    @ApiModelProperty(value = "产品类别名称")
    private String clsName;

    @ApiModelProperty(value = "备用")
    private Integer goodState;

    @ApiModelProperty(value = "商品数量")
    private Integer goodCount;

    @ApiModelProperty(value = "商品单价")
    private BigDecimal goodPrice;

    @ApiModelProperty(value = "商品单位")
    private String goodUnit;

    @ApiModelProperty(value = "商品总金额")
    private BigDecimal goodSumPrice;

    @ApiModelProperty(value = "实际支付金额")
    private BigDecimal actualMoney;

    @ApiModelProperty(value = "运费价格（单位：元）")
    private BigDecimal matflowPrice;

    @ApiModelProperty(value = "商品所属企业ID")
    private Integer entpId;

    @ApiModelProperty(value = "物流公司")
    private String wlCompName;

    @ApiModelProperty(value = "商品ID")
    private Integer commId;

    @ApiModelProperty(value = "商品名称")
    private String commName;

    @ApiModelProperty(value = "商品套餐组合ID")
    private Integer commTczhId;

    @ApiModelProperty(value = "商品套餐组合名称")
    private String commTczhName;

    @ApiModelProperty(value = "商品配送范围P_INDEX")
    private Integer deliveryPIndex;

    @ApiModelProperty(value = "修改前商品价格")
    private BigDecimal priceModifyOld;

    @ApiModelProperty(value = "商品价格修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date priceModifyDate;

    @ApiModelProperty(value = "修改价格备注")
    private String priceModifyRemark;

    @ApiModelProperty(value = "订单价格是否修改过")
    private Integer isPriceModify;

    @ApiModelProperty(value = "配送方式 1快递，2EMS，3平邮")
    private Integer deliveryWay;

    @ApiModelProperty(value = "商品添加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addDate;

    @ApiModelProperty(value = "修改前商品价格")
    private BigDecimal matflowPriceOld;

    @ApiModelProperty(value = "成本价格")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "利润")
    private BigDecimal profitMoney;

    @ApiModelProperty(value = "1、退货退款 2、换货 3、仅退款")
    private Integer isTuihuo;

    @ApiModelProperty(value = "是否评价")
    private Integer isComment;

    @ApiModelProperty(value = "是否需要发票 0：不需要 1：需要 2：已寄送发票")
    private Integer fpState;

    @ApiModelProperty(value = "使用优惠券ID")
    private Integer yhqSonId;

    @ApiModelProperty(value = "优惠金额")
    private BigDecimal yhqMoney;

}