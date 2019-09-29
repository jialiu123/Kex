package com.moba.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

public class BaseEntity {

	@ApiModelProperty(value = "", hidden = true)
	private Map<String, Object> map = new HashMap<String, Object>();

	@ApiModelProperty(value = "", hidden = true)
	private String queryString;

	@ApiModelProperty(value = "", hidden = true)
	private Row row = new Row();

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getQueryString() {
		return this.queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public Row getRow() {
		return row;
	}

	public void setRow(Row row) {
		this.row = row;
	}

}
