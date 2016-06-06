package com.shanxin.meb.dao;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shanxin.core.exception.DaoException;

@Repository
public abstract class DaoBase {

	@Autowired
	protected SQLManager sqlManager;

	public <T> List<T> select(Map<String, Object> paras, Class<T> targetClass) throws DaoException {
		try {
			return sqlManager.select(targetClass.getSimpleName() + ".sample", targetClass, paras);
		} catch (Throwable ex) {
			throw new DaoException(ex.getMessage());
		}
	}
}
