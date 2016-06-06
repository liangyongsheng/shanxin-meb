package com.shanxin.meb.dao.model;

import java.sql.Timestamp;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;

@Table(name = "meb_property")
public class MebPropertyModel {
	@AutoID
	private int id;
	private int mebId;
	private int type;
	private String value;
	private boolean flag;
	private Timestamp createTime;
	private Integer createOprtId;
	private Timestamp lastUpdateTime;
	private Integer lastUpdateOprtId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMebId() {
		return mebId;
	}

	public void setMebId(int mebId) {
		this.mebId = mebId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateOprtId() {
		return createOprtId;
	}

	public void setCreateOprtId(Integer createOprtId) {
		this.createOprtId = createOprtId;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getLastUpdateOprtId() {
		return lastUpdateOprtId;
	}

	public void setLastUpdateOprtId(Integer lastUpdateOprtId) {
		this.lastUpdateOprtId = lastUpdateOprtId;
	}

}
