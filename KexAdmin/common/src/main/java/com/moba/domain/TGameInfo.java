package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author liu, jia
 * @version 2019-03-01 11:02
 * @date 2019-03-01 11:02
 */
@ApiModel(value = "TGameInfo")
@Data
public class TGameInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "游戏名称")
    private String gameName;

    @ApiModelProperty(value = "开发方")
    private String gameDeveloper;

    @ApiModelProperty(value = "终端表现平台 从basedata获取 例：手游，pc")
    private Integer gamePlatform;

    @ApiModelProperty(value = "游戏类别 从base_class中获取")
    private Integer gameClass;

    @ApiModelProperty(value = "游戏主题")
    private String gameTheme;

    @ApiModelProperty(value = "游戏内容")
    private String gameContent;

    @ApiModelProperty(value = "0：未删除  1：已删除")
    private Integer isDel;

    @ApiModelProperty(value = "")
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

    @ApiModelProperty(value = "TGameServers的集合")
    private List<TGameServers> tGameServersList;

    @ApiModelProperty(value = "语言中文简体 zh_CN 中文繁体 zh_TW 英文 en 默认 中文简体")
    private String localeName;
}