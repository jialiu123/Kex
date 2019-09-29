package com.moba.mapper;

import java.util.List;

public interface MyMapper<T> {

	public Integer insertEntity(T t);

	public int updateEntity(T t);

	public int deleteEntity(T t);

	public T selectEntity(T t);

	public Integer selectEntityCount(T t);

	public List<T> selectEntityList(T t);

	public List<T> selectEntityPaginatedList(T t);
}
