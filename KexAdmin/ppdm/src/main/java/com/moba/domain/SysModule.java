package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liu, jia
 * @version 2019-02-20 15:57
 * @date 2019-02-20 15:57
 */
@ApiModel(value = "SysModule")
@Data
public class SysModule extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -1L;

  @ApiModelProperty(value = "")
  private Long modId;

  @ApiModelProperty(value = "节点组Keys:1 系统管理员 2普通会员3 商家 3：联盟主 1: 服务中心 ")
  private Integer modGroup;

  @ApiModelProperty(value = "")
  private Long parId;

  @ApiModelProperty(value = "")
  private String modName;

  @ApiModelProperty(value = "")
  private String modDesc;

  @ApiModelProperty(value = "")
  private String modUrl;

  @ApiModelProperty(value = "")
  private Integer modLevel;

  @ApiModelProperty(value = "2代表二级结构")
  private Integer isLeaf;

  @ApiModelProperty(value = "对应 BASE_POPEDOM 中 PPDM_CODE字段")
  private String ppdmCode;

  @ApiModelProperty(value = "")
  private Long orderValue;

  @ApiModelProperty(value = "0：私有，必须设定权限后才能访问1：公共，对所有用户都有权限9:管理，管理员默认有权限的模块")
  private Integer isPublic;

  @ApiModelProperty(value = "0：未锁定1：已锁定，不能删除，即不能将IS_DEL设置为1")
  private Integer isLock;

  @ApiModelProperty(value = "0：未删除1：已删除")
  private Integer isDel;

  @ApiModelProperty(value = "存放字体图标")
  private String fontAwesome;

  @ApiModelProperty(value = "权限编码")
  private String authorityCode;
}
