package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author liu, jia
 * @version 2019-02-20 15:57
 * @date 2019-02-20 15:57
 */
@ApiModel(value = "BasePopedom")
@Data
public class BasePopedom extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1L;

	@ApiModelProperty(value = "")
	private Integer id;
	
	@ApiModelProperty(value = "")
	private Integer ppdmCode;
	
	@ApiModelProperty(value = "")
	private String ppdmDetail;
	
	@ApiModelProperty(value = "")
	private String ppdmDesc;
	
	@ApiModelProperty(value = "0")
	private Integer isBase;
	
}