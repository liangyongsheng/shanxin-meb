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
import com.shanxin.meb.dao.DicDao;
import com.shanxin.meb.dao.MebDao;
import com.shanxin.meb.dao.MebPropertyDao;
import com.shanxin.meb.dao.model.MebModel;
import com.shanxin.meb.dao.model.MebPropertyModel;
import com.shanxin.meb.service.entity.Meb;
import com.shanxin.meb.service.entity.MebProperty;
import com.shanxin.meb.service.entity.request.MebQueryRequest;
import com.shanxin.meb.service.entity.response.MebQueryResponse;

@Service
public class MebQueryBo extends ApiBo<MebQueryRequest> {
	@Autowired
	private MebDao mebDao;
	@Autowired
	private DicDao dicDao;
	@Autowired
	private MebPropertyDao mebPropertyDao;

	@Override
	public Class<MebQueryRequest> getApiRequestType() {
		return MebQueryRequest.class;
	}

	@Override
	public void doService() throws ServiceException {
		try {
			MebQueryResponse rsp = (MebQueryResponse) this.apiResponse;
			rsp.setMebs(new ArrayList<Meb>());

			Map<String, Object> paras = new HashMap<String, Object>();
			Map<Integer, String> mapMebPropertyType = this.dicDao.get("meb_property", "type");
			List<MebModel> mebModels = this.apiRequest.getPageCount() <= 0 ? new ArrayList<MebModel>() : this.mebDao.queryMeb(this.apiRequest.getMebId(), this.apiRequest.getMobile(), this.apiRequest.getPageIndex(), this.apiRequest.getPageCount() + 1);
			if (mebModels.size() > this.apiRequest.getPageCount()) {
				rsp.setHaveNext(true);
				mebModels.remove(mebModels.size() - 1);
			}

			for (MebModel e : mebModels) {
				// 佛员主信息
				Meb meb = new Meb();
				meb.setCreateOprtId(e.getCreateOprtId());
				meb.setCreateTime(e.getCreateTime());
				meb.setLastUpdateOprtId(e.getLastUpdateOprtId());
				meb.setLastUpdateTime(e.getLastUpdateTime());
				meb.setMebId(e.getId());
				meb.setName(e.getName());
				meb.setRealName(e.getRealName());
				meb.setNickName(e.getNickName());
				meb.setRemark(e.getRemark());

				meb.setMebProperties(new ArrayList<MebProperty>());
				// 佛员属性信息
				paras.clear();
				paras.put("mebId", meb.getMebId());
				List<MebPropertyModel> mebPropertyModels = this.mebPropertyDao.select(paras, MebPropertyModel.class);
				for (MebPropertyModel ee : mebPropertyModels) {
					MebProperty add = new MebProperty();
					add.setCreateOprtId(ee.getCreateOprtId());
					add.setCreateTime(ee.getCreateTime());
					add.setLastUpdateOprtId(ee.getLastUpdateOprtId());
					add.setLastUpdateTime(ee.getLastUpdateTime());
					add.setMebId(ee.getMebId());
					add.setPropertyId(ee.getId());
					add.setPropertyType(ee.getType());
					add.setPropertyTypeName(mapMebPropertyType.get(ee.getType()));
					add.setPropertyValue(ee.getValue());
					meb.getMebProperties().add(add);
				}

				rsp.getMebs().add(meb);
			}
		} catch (Throwable ex) {
			if (ex instanceof CoreException)
				throw new ServiceException(ex.getMessage(), ((CoreException) ex).getCode());
			else
				throw new ServiceException(ex.getMessage());
		}
	}

}
