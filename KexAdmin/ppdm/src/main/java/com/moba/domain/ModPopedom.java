package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liu, jia
 * @version 2019-02-20 15:57
 * @date 2019-02-20 15:57
 */
@ApiModel(value = "ModPopedom")
@Data
public class ModPopedom extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "")
    private Integer modId;

    @ApiModelProperty(value = "")
    private Integer userId;

    @ApiModelProperty(value = "")
    private Integer roleId;

    @ApiModelProperty(value = "对应 BASE_POPEDOM 中 PPDM_CODE字段")
    private String ppdmCode;

    @ApiModelProperty(value = "")
    List<ModPopedom> modPopedomList;
}
