package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ding, ning
 * @version 2019-03-01 14:57
 * @date 2019-03-01 14:57
 */
@ApiModel(value = "BaseClass")
@Data
public class BaseClass extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "类别clsID")
    private Integer clsId;

    @ApiModelProperty(value = "0 游戏分类 10 游戏商品分类")
    private Integer clsScope;

    @ApiModelProperty(value = "类别名称")
    @NotBlank(message = "类别名称不能为空")
    private String clsName;

    @ApiModelProperty(value = "父类别ID")
    @NotNull(message = "父类别id不能为空")
    private Integer parId;

    @ApiModelProperty(value = "父类别名称不能为空")
    private String parName;

    @ApiModelProperty(value = "根类别ID")
    private Integer rootId;

    @ApiModelProperty(value = "cls等级")
    private Integer clsLevel;

    @ApiModelProperty(value = "排序值")
    private Integer orderValue;

    @ApiModelProperty(value = "是否锁定：0-否，1-是")
    private Integer isLock;

    @ApiModelProperty(value = "是否删除：0-否，1-是")
    private Integer isDel;

    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addDate;

    @ApiModelProperty(value = "添加人id")
    private Integer addUserId;

    @ApiModelProperty(value = "添加人姓名")
    private String addUserName;

    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    @ApiModelProperty(value = "修改人id")
    private Integer updateUserId;

    @ApiModelProperty(value = "删除时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date delDate;

    @ApiModelProperty(value = "删除人id")
    private Integer delUserId;

    @ApiModelProperty(value = "模板类型，备用字段")
    private Integer templateType;

    @ApiModelProperty(value = "")
    private String preVarchar;

    @ApiModelProperty(value = "语言中文简体 zh_CN 中文繁体 zh_TW 英文 en 默认 中文简体")
    private String localeName;

}