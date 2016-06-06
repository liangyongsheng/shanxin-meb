package com.shanxin.meb.service.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.shanxin.core.fastxml.jackson.ser.JsonStr2DatetimeDeserializer;
import com.shanxin.core.fastxml.jackson.ser.JsonStr2DatetimeSerializer;

@JacksonXmlRootElement(localName = "meb")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Meb {
	private int mebId;
	private String name;
	private String realName;
	private String nickName;
	@JsonSerialize(using = JsonStr2DatetimeSerializer.class)
	@JsonDeserialize(using = JsonStr2DatetimeDeserializer.class)
	private Date createTime;
	private Integer createOprtId;
	@JsonSerialize(using = JsonStr2DatetimeSerializer.class)
	@JsonDeserialize(using = JsonStr2DatetimeDeserializer.class)
	private Date lastUpdateTime;
	private Integer lastUpdateOprtId;
	private String remark;

	@JacksonXmlElementWrapper(localName = "mebProperties")
	@JacksonXmlProperty(localName = "mebProperty")
	private List<MebProperty> mebProperties;

	public int getMebId() {
		return mebId;
	}

	public void setMebId(int mebId) {
		this.mebId = mebId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateOprtId() {
		return createOprtId;
	}

	public void setCreateOprtId(Integer createOprtId) {
		this.createOprtId = createOprtId;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getLastUpdateOprtId() {
		return lastUpdateOprtId;
	}

	public void setLastUpdateOprtId(Integer lastUpdateOprtId) {
		this.lastUpdateOprtId = lastUpdateOprtId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<MebProperty> getMebProperties() {
		return mebProperties;
	}

	public void setMebProperties(List<MebProperty> mebProperties) {
		this.mebProperties = mebProperties;
	}

}
