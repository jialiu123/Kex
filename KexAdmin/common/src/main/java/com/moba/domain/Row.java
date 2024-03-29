package com.moba.domain;

import java.io.Serializable;

public class Row implements Serializable {

	private Integer first;

	private Integer count;

	public Row() {
	}

	public Row(Integer first, Integer count) {
		this.first = first;
		this.count = count;
	}

	public Integer getCount() {
		return this.count;
	}

	public Integer getFirst() {
		return this.first;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}
}
