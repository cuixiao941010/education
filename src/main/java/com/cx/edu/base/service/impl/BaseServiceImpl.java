package com.cx.edu.base.service.impl;

import com.cx.edu.base.service.BaseService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Validated
public abstract class BaseServiceImpl<T, Serializable> implements BaseService<T, Serializable> {

	protected abstract Mapper<T> getMapper();

	@Override
	public T getById(Serializable id) {
		return (T) getMapper().selectByPrimaryKey(id);
	}

	@Override
	public void save(T entity) {
		getMapper().insertSelective(entity);
	}

	@Override
	public void updateById(T entity) {
		getMapper().updateByPrimaryKeySelective(entity);
	}

	@Override
	public void removeById(Serializable id) {
		getMapper().deleteByPrimaryKey(id);
	}

	@Override
	public List<T> listAll() {
		return getMapper().selectAll();
	}

	@Override
	public List<T> listByCondition(T entity) {
		return getMapper().select(entity);
	}

	@Override
	public T getByCondition(T entity) {
		return getMapper().selectOne(entity);
	}
}
