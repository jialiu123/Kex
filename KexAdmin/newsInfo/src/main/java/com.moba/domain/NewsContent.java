package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author li, bai,qiang
 * @version 2019-02-26 16:55
 * @date 2019-02-26 16:55
 */
@ApiModel(value = "NewsContent")
@Data
public class NewsContent extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "")
    private String content;

}