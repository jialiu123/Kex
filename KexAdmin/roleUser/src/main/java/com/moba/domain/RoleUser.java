package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author liu, jia
 * @version 2019-02-20 11:35
 * @date 2019-02-20 11:35
 */
@ApiModel(value = "RoleUser")
@Data
public class RoleUser extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1L;

	@ApiModelProperty(value = "")
	private Integer id;
	
	@ApiModelProperty(value = "")
	private Integer roleId;
	
	@ApiModelProperty(value = "")
	private Integer userId;
	
	@ApiModelProperty(value = "")
	private Integer userOrderValue;
	
}