package com.shanxin.meb.service.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.shanxin.core.fastxml.jackson.ser.JsonStr2DatetimeDeserializer;
import com.shanxin.core.fastxml.jackson.ser.JsonStr2DatetimeSerializer;

@JacksonXmlRootElement(localName = "mebProperty")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebProperty {
	private int propertyId;
	private int mebId;
	private int propertyType;
	private String propertyTypeName;
	private String propertyValue;
	@JsonSerialize(using = JsonStr2DatetimeSerializer.class)
	@JsonDeserialize(using = JsonStr2DatetimeDeserializer.class)
	private Date createTime;
	private Integer createOprtId;
	@JsonSerialize(using = JsonStr2DatetimeSerializer.class)
	@JsonDeserialize(using = JsonStr2DatetimeDeserializer.class)
	private Date lastUpdateTime;
	private Integer lastUpdateOprtId;

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public int getMebId() {
		return mebId;
	}

	public void setMebId(int mebId) {
		this.mebId = mebId;
	}

	public int getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(int propertyType) {
		this.propertyType = propertyType;
	}

	public String getPropertyTypeName() {
		return propertyTypeName;
	}

	public void setPropertyTypeName(String propertyTypeName) {
		this.propertyTypeName = propertyTypeName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
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

}
