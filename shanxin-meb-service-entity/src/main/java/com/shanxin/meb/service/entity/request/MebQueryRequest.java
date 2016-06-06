package com.shanxin.meb.service.entity.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.shanxin.core.api.ApiRequest;
import com.shanxin.core.exception.CheckException;
import com.shanxin.meb.service.entity.response.MebQueryResponse;

@JacksonXmlRootElement(localName = "mebQueryRequest")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MebQueryRequest extends ApiRequest<MebQueryResponse> {
	public static final int MAX_PAGE_COUNT = 500;
	private Integer pageIndex;// 从0开始
	private Integer pageCount;// 多少条记录
	private Integer mebId;
	private String mobile;

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getMebId() {
		return mebId;
	}

	public void setMebId(Integer mebId) {
		this.mebId = mebId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String getLocalMothedName() {
		return "com.shanxin.meb.query";
	}

	@Override
	public Class<MebQueryResponse> getApiResponseType() {
		return MebQueryResponse.class;
	}

	@Override
	public void checkApiParams() throws CheckException {
		if (this.pageIndex == null || this.pageIndex <= 0)
			this.pageIndex = 0;

		if (this.pageCount == null)
			this.pageCount = MAX_PAGE_COUNT;
		else if (this.pageCount <= 0)
			this.pageCount = 0;
		else if (this.pageCount > MAX_PAGE_COUNT)
			this.pageCount = MAX_PAGE_COUNT;

	}

}
