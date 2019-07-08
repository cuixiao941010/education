package com.cx.edu.base.service;

import java.util.List;

public interface BaseService<T, Serializable> {

	T getById(Serializable id);

	void save(T entity);

	void updateById(T entity);

	void removeById(Serializable id);

	List<T> listAll();

	List<T> listByCondition(T entity);

	T getByCondition(T entity);
}
