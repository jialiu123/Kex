package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liu, jia
 * @version 2019-03-01 11:02
 * @date 2019-03-01 11:02
 */
@ApiModel(value = "TGameServers")
@Data
public class TGameServers extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "游戏id")
    private Integer gameId;

    @ApiModelProperty(value = "服务区名称")
    private String gameServerName;

    @ApiModelProperty(value = "服务区级别")
    private Integer gameServerLevel;

    @ApiModelProperty(value = "上级id   0则没有上级")
    private Integer parId;

    @ApiModelProperty(value = "0：未删除 1：已删除")
    private Integer isDel;

    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addDate;

    @ApiModelProperty(value = "添加人ID")
    private Integer addUserId;

    @ApiModelProperty(value = "添加人姓名")
    private String addUserName;

    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    @ApiModelProperty(value = "更新人id")
    private Integer updateUserId;

    @ApiModelProperty(value = "删除时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date delDate;

    @ApiModelProperty(value = "删除人id")
    private Integer delUserId;

}