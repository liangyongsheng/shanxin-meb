package com.shanxin.meb.service.entity.request;

import com.shanxin.core.api.ApiRequest;
import com.shanxin.core.exception.CheckException;
import com.shanxin.core.exception.CheckIllicitValueException;
import com.shanxin.core.util.MyStringUtils;
import com.shanxin.meb.service.entity.response.MebLoginResponse;

public class MebLoginRequest extends ApiRequest<MebLoginResponse> {
	private Integer loginType;// 1：为手机号
	private String loginName;
	private String loginPwd;

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
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
		if (this.loginType == null || this.loginType != 1)
			throw new CheckIllicitValueException("field: loginType, value is illicit.");
		if (MyStringUtils.isEmpty(this.loginName))
			throw new CheckIllicitValueException("field: name, value is empty.");
		if (MyStringUtils.isEmpty(this.loginPwd))
			throw new CheckIllicitValueException("field: pwd, value is empty.");

	}

}
