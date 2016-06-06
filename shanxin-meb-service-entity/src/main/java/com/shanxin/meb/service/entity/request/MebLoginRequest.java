package com.shanxin.meb.service.entity.request;

import com.shanxin.core.api.ApiRequest;
import com.shanxin.core.exception.CheckException;
import com.shanxin.core.exception.CheckIllicitValueException;
import com.shanxin.core.util.MyStringUtils;
import com.shanxin.meb.service.entity.response.MebLoginResponse;

public class MebLoginRequest extends ApiRequest<MebLoginResponse> {
	private String name;
	private String pwd;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String getLocalMothedName() {
		return "com.shanxin.meb.login";
	}

	@Override
	public Class<MebLoginResponse> getApiResponseType() {
		return MebLoginResponse.class;
	}

	@Override
	public void checkApiParams() throws CheckException {
		if (MyStringUtils.isEmpty(this.name))
			throw new CheckIllicitValueException("field: name, value is empty.");
		if (MyStringUtils.isEmpty(this.pwd))
			throw new CheckIllicitValueException("field: pwd, value is empty.");

	}

}
