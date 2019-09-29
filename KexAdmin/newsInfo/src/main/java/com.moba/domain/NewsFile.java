package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author li, bai,qiang
 * @version 2019-02-27 14:05
 * @date 2019-02-27 14:05
 */
@ApiModel(value = "NewsFile")
@Data
public class NewsFile extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "关联ID")
    private Integer linkId;

    @ApiModelProperty(value = "关联表名")
    private String linkTab;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件类型")
    private String fileType;

    @ApiModelProperty(value = "文件大小kb")
    private Integer fileSize;

    @ApiModelProperty(value = "保存路劲")
    private String savePath;

    @ApiModelProperty(value = "保存名称")
    private String saveName;

    @ApiModelProperty(value = "文件描述")
    private String fileDesc;

    @ApiModelProperty(value = "是否删除 1：已删除 0 未删除")
    private Integer isDel;

}