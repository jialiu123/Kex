package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author li, bai,qiang
 * @version 2019-03-07 16:15
 * @date 2019-03-07 16:15
 */
@ApiModel(value = "Msg")
@Data
public class Msg extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "0,站内信息 10短信信息")
    private Integer msgType;

    @ApiModelProperty(value = "接收人ID")
    private Integer userId;

    @ApiModelProperty(value = "发送人id")
    private Integer sendUserId;

    @ApiModelProperty(value = "发送人name")
    private String sendUserName;

    @ApiModelProperty(value = "站内信标题")
    private String msgTitle;

    @ApiModelProperty(value = "站内信说明")
    private String msgContent;

    @ApiModelProperty(value = "发送时间")
    private Date sendTime;

    @ApiModelProperty(value = "0：草稿未发送 1：已发送未读 2：已发送已读 3回收站")
    private Integer infoState;

    @ApiModelProperty(value = "是否删除：0-否，1-是")
    private Integer isDel;

    @ApiModelProperty(value = "")
    private Date addDate;

    @ApiModelProperty(value = "")
    private Integer addUserId;

    @ApiModelProperty(value = "")
    private Date updateDate;

    @ApiModelProperty(value = "")
    private Integer updateUserId;

    @ApiModelProperty(value = "")
    private Date delDate;

    @ApiModelProperty(value = "")
    private Integer delUserId;
}