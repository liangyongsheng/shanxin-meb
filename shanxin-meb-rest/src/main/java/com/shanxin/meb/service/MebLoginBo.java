package com.shanxin.meb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shanxin.core.api.ApiBo;
import com.shanxin.core.exception.CoreException;
import com.shanxin.core.exception.ServiceException;
import com.shanxin.core.util.MyAlgorithmUtils;
import com.shanxin.meb.dao.DicDao;
import com.shanxin.meb.dao.MebDao;
import com.shanxin.meb.dao.MebPropertyDao;
import com.shanxin.meb.dao.model.MebModel;
import com.shanxin.meb.dao.model.MebPropertyModel;
import com.shanxin.meb.service.entity.MebProperty;
import com.shanxin.meb.service.entity.request.MebLoginRequest;
import com.shanxin.meb.service.entity.response.MebLoginResponse;

@Service
public class MebLoginBo extends ApiBo<MebLoginRequest> {
	@Autowired
	private MebDao mebDao;
	@Autowired
	private DicDao dicDao;
	@Autowired
	private MebPropertyDao mebPropertyDao;

	@Override
	public Class<MebLoginRequest> getApiRequestType() {
		return MebLoginRequest.class;
	}

	@Override
	public void doService() throws ServiceException {
		try {
			MebLoginResponse rsp = (MebLoginResponse) this.apiResponse;
			rsp.setMebProperties(new ArrayList<MebProperty>());
			Map<String, Object> paras = new HashMap<String, Object>();

			paras.clear();
			paras.put("name", this.apiRequest.getName());
			List<MebModel> mebModels = this.mebDao.select(paras, MebModel.class);
			if (mebModels.size() <= 0)
				throw new ServiceException("不存在此佛员。");
			if (mebModels.size() > 1)
				throw new ServiceException("佛员name出现重复记录。");
			MebModel mebModel = mebModels.get(0);
			if (!mebModel.getPwd().equalsIgnoreCase(MyAlgorithmUtils.MD5(this.apiRequest.getPwd())))
				throw new ServiceException("密码有误。");

			// 佛员主信息
			rsp.setCreateOprtId(mebModel.getCreateOprtId());
			rsp.setCreateTime(mebModel.getCreateTime());
			rsp.setLastUpdateOprtId(mebModel.getLastUpdateOprtId());
			rsp.setLastUpdateTime(mebModel.getCreateTime());
			rsp.setMebId(mebModel.getId());
			rsp.setName(mebModel.getName());
			rsp.setNickName(mebModel.getNickName());
			rsp.setRemark(mebModel.getRemark());

			// 佛员属性信息
			paras.clear();
			paras.put("mebId", mebModel.getId());
			Map<Integer, String> mapMebPropertyType = this.dicDao.get("meb_property", "type");
			List<MebPropertyModel> mebPropertyModels = this.mebPropertyDao.select(paras, MebPropertyModel.class);
			for (MebPropertyModel e : mebPropertyModels) {
				MebProperty add = new MebProperty();
				add.setCreateOprtId(e.getCreateOprtId());
				add.setCreateTime(e.getCreateTime());
				add.setLastUpdateOprtId(e.getLastUpdateOprtId());
				add.setLastUpdateTime(e.getLastUpdateTime());
				add.setMebId(e.getMebId());
				add.setPropertyId(e.getId());
				add.setPropertyType(e.getType());
				add.setPropertyTypeName(mapMebPropertyType.get(e.getType()));
				add.setPropertyValue(e.getValue());
				rsp.getMebProperties().add(add);
			}

		} catch (Throwable ex) {
			if (ex instanceof CoreException)
				throw new ServiceException(ex.getMessage(), ((CoreException) ex).getCode());
			else
				throw new ServiceException(ex.getMessage());
		}
	}
}
