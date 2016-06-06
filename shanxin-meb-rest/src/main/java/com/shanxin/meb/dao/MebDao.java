package com.shanxin.meb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shanxin.core.exception.DaoException;
import com.shanxin.meb.dao.model.MebModel;

@Repository
public class MebDao extends DaoBase {
	public List<MebModel> queryMeb(Integer mebId, String mobile, Integer pageIndex, Integer pageCount) throws DaoException {
		try {
			Map<String, Object> paras = new HashMap<String, Object>();
			paras.put("id", mebId);
			paras.put("mobile", mobile);
			paras.put("pageIndex", pageIndex);
			paras.put("pageCount", pageCount);
			List<MebModel> ls = sqlManager.select("MebModel.queryMeb", MebModel.class, paras);
			return ls;
		} catch (Throwable ex) {
			throw new DaoException(ex.getMessage());
		}
	}
}
