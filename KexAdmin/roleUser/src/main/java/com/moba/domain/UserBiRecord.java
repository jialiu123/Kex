package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liu, jia
 * @version 2019-03-06 11:07
 * @date 2019-03-06 11:07
 */
@ApiModel(value = "UserBiRecord")
@Data
public class UserBiRecord extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "关联商品ID")
    private Integer linkId;

    @ApiModelProperty(value = "1入 -1 出")
    @NotNull(message = "请确定是进账还是出账")
    private Integer biChuOrRu;

    @ApiModelProperty(value = "100 余额")
    private Integer biType;

    @ApiModelProperty(value = "")
    private BigDecimal biNoBefore;

    @ApiModelProperty(value = "所获币的数量")
    @NotBlank(message = "请输入进出账金额")
    private BigDecimal biNo;

    @ApiModelProperty(value = "")
    private BigDecimal biNoAfter;

    @ApiModelProperty(value = "获取币来源")
    @NotNull(message = "获取金币的来源方式")
    private Integer biGetType;

    @ApiModelProperty(value = "获取币说明")
    private String biGetMemo;

    @ApiModelProperty(value = "添加人ID")
    @NotNull(message = "添加人ID不能为空")
    private Integer addUserId;

    @ApiModelProperty(value = "添加时间")
    private Date addDate;

    @ApiModelProperty(value = "货币状态 -1错失 0 正常 1已锁定（根据币来源类型，说明锁定原因 惠赚推广奖励、惠赚复销、惠赚规则锁定）")
    private Integer biState;

    @ApiModelProperty(value = "0：未删除 1：已删除")
    private Integer isDel;

    @ApiModelProperty(value = "")
    private Date delDate;

    @ApiModelProperty(value = "")
    private Integer delUserId;

    @ApiModelProperty(value = "通道类型,基础数据维护")
    private Integer lineType;

    @ApiModelProperty(value = "0 未返还 1已返回")
    private Integer isFanhuan;

    @ApiModelProperty(value = "")
    private String addUname;

    @ApiModelProperty(value = "")
    private Integer destUid;

    @ApiModelProperty(value = "")
    private String destUname;

    @ApiModelProperty(value = "")
    private BigDecimal biRate;

    @ApiModelProperty(value = "余额获取比例（万分制）")
    private BigDecimal getRate;

    @ApiModelProperty(value = "订单ID")
    private Integer orderId;

}