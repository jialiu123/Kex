package com.moba.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moba.mapper.BasePopedomMapper;
import com.moba.domain.BasePopedom;


/**
 * @author liu, jia
 * @version 2019-02-20 15:57
 * @date 2019-02-20 15:57
 */
@Service
@Transactional
public class BasePopedomService{

	@Resource
	private BasePopedomMapper basePopedomMapper;
	
	public BasePopedom getEntity(BasePopedom t) {
		return this.basePopedomMapper.selectEntity(t);
	}

	public Integer getEntityCount(BasePopedom t) {
		return this.basePopedomMapper.selectEntityCount(t);
	}

	public List<BasePopedom> getEntityList(BasePopedom t) {
		return this.basePopedomMapper.selectEntityList(t);
	}

	public Integer createEntity(BasePopedom t) {
        this.basePopedomMapper.insertEntity(t);
		return t.getId();
	}

	public int updateEntity(BasePopedom t) {
		return this.basePopedomMapper.updateEntity(t);
	}
	
	public int removeEntity(BasePopedom t) {
		return this.basePopedomMapper.deleteEntity(t);
	}

	public List<BasePopedom> getEntityPaginatedList(BasePopedom t) {
		return this.basePopedomMapper.selectEntityPaginatedList(t);
	}

}
