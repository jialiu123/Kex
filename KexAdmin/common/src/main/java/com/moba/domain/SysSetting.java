package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ding, ning
 * @version 2019-03-11 09:19
 * @date 2019-03-11 09:19
 */
@ApiModel(value = "SysSetting")
@Data
public class SysSetting extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "")
    private String title;

    @ApiModelProperty(value = "")
    private String memo;

    @ApiModelProperty(value = "")
    private String content;

}