package com.moba.service;

import java.util.List;
import javax.annotation.Resource;

import com.moba.domain.UserInfo;
import com.moba.emums.AuditState;
import com.moba.emums.roleUser.RoleType;
import com.moba.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moba.mapper.RoleUserMapper;
import com.moba.domain.RoleUser;


/**
 * @author liu, jia
 * @version 2019-02-20 11:35
 * @date 2019-02-20 11:35
 */
@Service
@Transactional
public class RoleUserService{

	@Resource
	private RoleUserMapper roleUserMapper;

	@Resource
	private UserInfoMapper userInfoMapper;


	public RoleUser getEntity(RoleUser t) {
		return this.roleUserMapper.selectEntity(t);
	}

	public Integer getEntityCount(RoleUser t) {
		return this.roleUserMapper.selectEntityCount(t);
	}

	public List<RoleUser> getEntityList(RoleUser t) {
		return this.roleUserMapper.selectEntityList(t);
	}

	public Integer createEntity(RoleUser t) {
        this.roleUserMapper.insertEntity(t);
		return t.getId();
	}

	public int updateEntity(RoleUser t) {
		return this.roleUserMapper.updateEntity(t);
	}
	
	public int removeEntity(RoleUser t) {
		return this.roleUserMapper.deleteEntity(t);
	}

	public List<RoleUser> getEntityPaginatedList(RoleUser t) {
		return this.roleUserMapper.selectEntityPaginatedList(t);
	}


	public Boolean updateRoleUser(Integer userId,Integer auditState) {

		UserInfo userInfo = new UserInfo();
		userInfo.setId(userId);
		userInfo = this.userInfoMapper.selectEntity(userInfo);
		if(null == userInfo){
			return false;
		}
		userInfo.setIsEntp(0);
		if(auditState.intValue() == AuditState.AUDIT_STATE_1.getIndex()){
			userInfo.setIsEntp(1);
		}
		int count = this.userInfoMapper.updateEntity(userInfo);
		if(count>0){
			//先删后增
			RoleUser entity = new RoleUser();
			entity.getMap().put("userId",userId);
			this.roleUserMapper.deleteEntity(entity);

			if (null != userInfo.getUserType()) {
				RoleUser ru = new RoleUser();
				ru.setUserId(userInfo.getId());
				ru.setRoleId(userInfo.getUserType().intValue());
				roleUserMapper.insertEntity(ru);
				if (userInfo.getIsEntp().intValue() == 1) {
					ru.setId(null);
					ru.setRoleId(RoleType.ROLE_TYPE_3.getIndex());
					roleUserMapper.insertEntity(ru);
				}
			}

			return true;
		}
		return false;
	}

}
