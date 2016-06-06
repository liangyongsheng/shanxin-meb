package com.shanxin.meb.dao.model;

import java.sql.Timestamp;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;

@Table(name = "")
public class DicModel {
	@AutoID
	private int id;
	private String fieldName;
	private int key;
	private String value;
	private Timestamp createTime;
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
