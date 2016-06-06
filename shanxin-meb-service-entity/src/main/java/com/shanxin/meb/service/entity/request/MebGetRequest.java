package com.shanxin.meb.service.entity.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.shanxin.core.api.ApiRequest;
import com.shanxin.core.exception.CheckException;
import com.shanxin.core.exception.CheckIllicitValueException;
import com.shanxin.meb.service.entity.response.MebGetResponse;

@JacksonXmlRootElement(localName = "mebGetRequest")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebGetRequest extends ApiRequest<MebGetResponse> {
	private Integer mebId;

	public Integer getMebId() {
		return mebId;
	}

	public void setMebId(Integer mebId) {
		this.mebId = mebId;
	}

	@Override
	public String getLocalMothedName() {
		return "com.shanxin.meb.get";
	}

	@Override
	public Class<MebGetResponse> getApiResponseType() {
		return MebGetResponse.class;
	}

	@Override
	public void checkApiParams() throws CheckException {
		if (this.mebId == null || this.mebId <= 0)
			throw new CheckIllicitValueException("field: mebId, value is illicit.");
	}

}
