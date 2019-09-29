package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ding, ning
 * @version 2019-02-28 17:24
 * @date 2019-02-28 17:24
 */
@ApiModel(value = "BaseData")
@Data
public class BaseData extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "类型  ")
    @NotNull(message = "类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "类型名称")
    private String typeName;

    @ApiModelProperty(value = "默认等于id的值，如果有特殊需要可以自己在数据库中修改")
    private String typeValue;

    @ApiModelProperty(value = "辅助备用字段")
    private Integer value;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "排序值")
    private Integer orderValue;

    @ApiModelProperty(value = "是否锁定")
    private Integer isLock;

    @ApiModelProperty(value = "是否删除")
    private Integer isDel;

    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addDate;

    @ApiModelProperty(value = "添加人")
    @NotNull(message = "添加人的ID不能为空")
    private Integer addUserId;

    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    @ApiModelProperty(value = "更新人")
    private Integer updateUserId;

    @ApiModelProperty(value = "删除时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date delDate;

    @ApiModelProperty(value = "删除人")
    private Integer delUserId;

    @ApiModelProperty(value = "备用字段")
    private String preData;

    @ApiModelProperty(value = "语言中文简体 zh_CN 中文繁体 zh_TW 英文 en 默认 中文简体")
    private String localeName;
}