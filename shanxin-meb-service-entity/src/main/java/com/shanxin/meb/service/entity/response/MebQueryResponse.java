package com.shanxin.meb.service.entity.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.shanxin.core.api.ApiResponse;
import com.shanxin.meb.service.entity.Meb;

@JacksonXmlRootElement(localName = "mebQueryResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebQueryResponse extends ApiResponse {
	private boolean haveNext;
	@JacksonXmlElementWrapper(localName = "mebs")
	@JacksonXmlProperty(localName = "meb")
	private List<Meb> mebs;

	public boolean isHaveNext() {
		return haveNext;
	}

	public void setHaveNext(boolean haveNext) {
		this.haveNext = haveNext;
	}

	public List<Meb> getMebs() {
		return mebs;
	}

	public void setMebs(List<Meb> mebs) {
		this.mebs = mebs;
	}

}
