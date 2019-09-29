package com.moba.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moba.mapper.RoleMapper;
import com.moba.domain.Role;


/**
 * @author liu, jia
 * @version 2019-02-20 11:29
 * @date 2019-02-20 11:29
 */
@Service
@Transactional
public class RoleService{

	@Resource
	private RoleMapper roleMapper;
	
	public Role getEntity(Role t) {
		return this.roleMapper.selectEntity(t);
	}

	public Integer getEntityCount(Role t) {
		return this.roleMapper.selectEntityCount(t);
	}

	public List<Role> getEntityList(Role t) {
		return this.roleMapper.selectEntityList(t);
	}

	public Integer createEntity(Role t) {
        this.roleMapper.insertEntity(t);
		return t.getId();
	}

	public int updateEntity(Role t) {
		return this.roleMapper.updateEntity(t);
	}
	
	public int removeEntity(Role t) {
		return this.roleMapper.deleteEntity(t);
	}

	public List<Role> getEntityPaginatedList(Role t) {
		return this.roleMapper.selectEntityPaginatedList(t);
	}

}
