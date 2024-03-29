package com.moba.domain;

public class Result<T> {

	/** 错误码 */
	public Integer code;

	/** 错误提示 */
	public String msg;

	/** 错误数据 */
	public T data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
