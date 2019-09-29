package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author li, bai, qiang
 * @version 2019-02-26 14:53
 * @date 2019-02-26 14:53
 */
@ApiModel(value = "NewsInfo")
@Data
public class NewsInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "主id")
    private Integer id;

    @ApiModelProperty(value = "通用唯一识别码")
    private String uuid;

    @ApiModelProperty(value = "菜单权限id")
    private String modId;

    @ApiModelProperty(value = "标题")
    @NotBlank(message = "标题不能为空")
    private String title;

    @ApiModelProperty(value = "标题颜色")
    private String titleColor;

    @ApiModelProperty(value = "标题是否加粗")
    private Integer titleIsStrong;

    @ApiModelProperty(value = "短标题")
    private String titleShort;

    @ApiModelProperty(value = "副标题")
    @NotBlank(message = "副标题不能为空")
    private String titleSub;

    @ApiModelProperty(value = "是否使用链接")
    private Integer isUseDirectUri;

    @ApiModelProperty(value = "链接地址")
    private String directUri;

    @ApiModelProperty(value = "关键字")
    private String keyword;

    @ApiModelProperty(value = "主图路径")
    private String imagePath;

    @ApiModelProperty(value = "主图描述")
    private String imageDesc;

    @ApiModelProperty(value = "摘要")
    private String summary;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "消息来源")
    private String infoSource;

    @ApiModelProperty(value = "是否使用失效时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Integer isUseInvalidDate;

    @ApiModelProperty(value = "失效时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date invalidDate;

    @ApiModelProperty(value = "发布时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pubTime;

    @ApiModelProperty(value = "浏览次数")
    private Integer viewCount;

    @ApiModelProperty(value = "浏览日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date viewDatetime;

    @ApiModelProperty(value = "")
    private Integer infoState;

    @ApiModelProperty(value = "排序值")
    private Integer orderValue;

    @ApiModelProperty(value = "是否删除")
    private Integer isDel;

    @ApiModelProperty(value = "视频路径")
    private String videoPath;

    @ApiModelProperty(value = "视频说明")
    private String videoDesc;

    @ApiModelProperty(value = "语言中文简体 zh_CN 中文繁体 zh_TW 英文 en 默认 中文简体")
    private String localeName;

    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addDate;

    @ApiModelProperty(value = "添加人id")
    @NotNull(message = "添加人不能为空")
    private Integer addUserId;

    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    private Date updateDate;

    @ApiModelProperty(value = "修改人id")
    private Integer updateUserId;

    @ApiModelProperty(value = "删除时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date delDate;

    @ApiModelProperty(value = "删除人id")
    private Integer delUserId;

    @ApiModelProperty(value = "所屬語言")
    private String language;

    @ApiModelProperty(value = "新闻内容")
    private String newsContent;

    @ApiModelProperty(value = "新闻文件")
    private List<NewsFile> newsFileList;

}