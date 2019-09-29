package com.moba.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moba.mapper.ModPopedomMapper;
import com.moba.domain.ModPopedom;


/**
 * @author liu, jia
 * @version 2019-02-20 15:57
 * @date 2019-02-20 15:57
 */
@Service
@Transactional
public class ModPopedomService{

	@Resource
	private ModPopedomMapper modPopedomMapper;
	
	public ModPopedom getEntity(ModPopedom t) {
		return this.modPopedomMapper.selectEntity(t);
	}

	public Integer getEntityCount(ModPopedom t) {
		return this.modPopedomMapper.selectEntityCount(t);
	}

	public List<ModPopedom> getEntityList(ModPopedom t) {
		return this.modPopedomMapper.selectEntityList(t);
	}

	public Integer createEntity(ModPopedom t) {
        this.modPopedomMapper.insertEntity(t);
		return t.getId();
	}

	public int updateEntity(ModPopedom t) {
		return this.modPopedomMapper.updateEntity(t);
	}
	
	public int removeEntity(ModPopedom t) {
		return this.modPopedomMapper.deleteEntity(t);
	}

	public List<ModPopedom> getEntityPaginatedList(ModPopedom t) {
		return this.modPopedomMapper.selectEntityPaginatedList(t);
	}

}
