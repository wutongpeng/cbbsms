package com.example.sensor.model;

/**
 * 创建与XML属性对应的Bean实体类Return 对服务器返回结果的解析实体
 */
public class Return {
	// 采集操作是否成功
	private String success;
	// 当前灯的状态
	private String value;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
