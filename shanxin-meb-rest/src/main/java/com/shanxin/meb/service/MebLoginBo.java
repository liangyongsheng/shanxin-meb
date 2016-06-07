package com.shanxin.meb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shanxin.core.api.ApiBo;
import com.shanxin.core.exception.CoreException;
import com.shanxin.core.exception.ServiceException;
import com.shanxin.core.util.MyAlgorithmUtils;
import com.shanxin.meb.dao.MebDao;
import com.shanxin.meb.dao.model.MebModel;
import com.shanxin.meb.service.entity.request.MebLoginRequest;
import com.shanxin.meb.service.entity.response.MebLoginResponse;

@Service
public class MebLoginBo extends ApiBo<MebLoginRequest> {
	@Autowired
	private MebDao mebDao;

	@Override
	public Class<MebLoginRequest> getApiRequestType() {
		return MebLoginRequest.class;
	}

	@Override
	public void doService() throws ServiceException {
		try {
			MebLoginResponse rsp = (MebLoginResponse) this.apiResponse;

			if (this.apiRequest.getLoginType() != 1)
				throw new ServiceException("现只支持手机号登录。");

			List<MebModel> mebModels = this.mebDao.queryMeb(null, this.apiRequest.getLoginName(), 0, 2);
			if (mebModels.size() <= 0)
				throw new ServiceException("不存在此手机号的佛员。");
			if (mebModels.size() > 1)
				throw new ServiceException("存在同一手机号的多个佛员。");

			MebModel mebModel = mebModels.get(0);
			if (!mebModel.getPwd().equalsIgnoreCase(MyAlgorithmUtils.MD5(this.apiRequest.getLoginPwd())))
				throw new ServiceException("密码有误。");

			// 佛员主信息
			rsp.setCreateOprtId(mebModel.getCreateOprtId());
			rsp.setCreateTime(mebModel.getCreateTime());
			rsp.setLastUpdateOprtId(mebModel.getLastUpdateOprtId());
			rsp.setLastUpdateTime(mebModel.getCreateTime());
			rsp.setMebId(mebModel.getId());
			rsp.setName(mebModel.getName());
			rsp.setRealName(mebModel.getRealName());
			rsp.setNickName(mebModel.getNickName());
			rsp.setRemark(mebModel.getRemark());

		} catch (Throwable ex) {
			if (ex instanceof CoreException)
				throw new ServiceException(ex.getMessage(), ((CoreException) ex).getCode());
			else
				throw new ServiceException(ex.getMessage());
		}
	}
}
