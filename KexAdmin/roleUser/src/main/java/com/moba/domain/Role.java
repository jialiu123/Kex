package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liu, jia
 * @version 2019-02-20 11:29
 * @date 2019-02-20 11:29
 */
@ApiModel(value = "Role")
@Data
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "")
    private String roleName;

    @ApiModelProperty(value = "")
    private Integer orderValue;

    @ApiModelProperty(value = "0：未锁定 1：已锁定，不能删除，即不能将IS_DEL设置为1")
    private Integer isLock;

    @ApiModelProperty(value = "0：未删除 1：已删除")
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

    @ApiModelProperty(value = "")
    private List<UserInfo> userInfoList = new ArrayList<UserInfo>();
}
