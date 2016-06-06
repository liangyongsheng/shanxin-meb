package com.shanxin.meb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shanxin.core.util.MyStringUtils;
import com.shanxin.meb.dao.model.DicModel;

@Repository
public class DicDao extends DaoBase {

	public Map<Integer, String> get(String tableName, String fieldName) {
		Map<Integer, String> rs = new HashMap<Integer, String>();
		if (MyStringUtils.isEmpty(tableName) || MyStringUtils.isEmpty(fieldName))
			return rs;

		tableName = tableName.indexOf("_dic") >= 0 ? tableName : tableName + "_dic";
		
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("tableName", tableName);
		paras.put("fieldName", fieldName);
		List<DicModel> dicModels = sqlManager.select("DicModel.sample", DicModel.class, paras);

		for (DicModel e : dicModels)
			rs.put(e.getKey(), e.getValue());
		return rs;
	}
}
