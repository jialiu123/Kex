package com.moba.service;

import com.moba.domain.TGameServers;
import com.moba.mapper.TGameServersMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author liu, jia
 * @version 2019-03-01 11:02
 * @date 2019-03-01 11:02
 */
@Service
@Transactional
public class TGameServersService {

	@Resource
	private TGameServersMapper tGameServersMapper;

	public TGameServers getEntity(TGameServers t) {
		return this.tGameServersMapper.selectEntity(t);
	}

	public Integer getEntityCount(TGameServers t) {
		return this.tGameServersMapper.selectEntityCount(t);
	}

	public List<TGameServers> getEntityList(TGameServers t) {
		return this.tGameServersMapper.selectEntityList(t);
	}

	public Integer createEntity(TGameServers t) {
        this.tGameServersMapper.insertEntity(t);
		return t.getId();
	}

	public int updateEntity(TGameServers t) {

		return this.tGameServersMapper.updateEntity(t);
	}

	public int removeEntity(TGameServers t) {
		return this.tGameServersMapper.deleteEntity(t);
	}

	public List<TGameServers> getEntityPaginatedList(TGameServers t) {
		return this.tGameServersMapper.selectEntityPaginatedList(t);
	}

}
