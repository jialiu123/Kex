package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author li, bai, qiang
 * @version 2019-03-06 11:20
 * @date 2019-03-06 11:20
 */
@ApiModel(value = "UserMoneyApply")
@Data
public class UserMoneyApply extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "")
    private BigDecimal cashCountBefore;

    @ApiModelProperty(value = "提现金额")
    private BigDecimal cashCount;

    @ApiModelProperty(value = "提现后金额")
    private BigDecimal cashCountAfter;

    @ApiModelProperty(value = "申请类型：keys:10余额提现")
    private Integer cashType;

    @ApiModelProperty(value = "申请人ID")
    private Integer addUid;

    @ApiModelProperty(value = "申请时间")
    private Date addDate;

    @ApiModelProperty(value = "申请说明")
    private String addMemo;

    @ApiModelProperty(value = "审核人ID")
    private Integer auditUid;

    @ApiModelProperty(value = "审核时间")
    private Date auditDate;

    @ApiModelProperty(value = "审核说明")
    private String auditMemo;

    @ApiModelProperty(value = "审核状态：Keys：0 未审核（默认），1 已审核 ，2  已付款 -1 审核不通过")
    private Integer infoState;

    @ApiModelProperty(value = "0：未删除 1：已删除")
    private Integer isDel;

    @ApiModelProperty(value = "")
    private Date delDate;

    @ApiModelProperty(value = "")
    private Integer delUserId;

    @ApiModelProperty(value = "付款金额")
    private BigDecimal cashPay;

    @ApiModelProperty(value = "手续费")
    private BigDecimal cashRate;

    @ApiModelProperty(value = "")
    private String realName;

    @ApiModelProperty(value = "")
    private String mobile;

    @ApiModelProperty(value = "")
    private String bankName;

    @ApiModelProperty(value = "")
    private String bankAccount;

    @ApiModelProperty(value = "")
    private String bankAccountName;

    @ApiModelProperty(value = "退款说明")
    private String tuikuanMemo;

    @ApiModelProperty(value = "退款时间")
    private Date tuikuanDate;

    @ApiModelProperty(value = "付款备注")
    private String remark;

    @ApiModelProperty(value = "提现凭证")
    private String proofImg;

}