package com.moba.service;

import com.moba.domain.SysModule;
import com.moba.emums.roleUser.UserType;
import com.moba.mapper.SysModuleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liu, jia
 * @version 2019-02-20 15:57
 * @date 2019-02-20 15:57
 */
@Service
@Transactional
public class SysModuleService {

  @Resource private SysModuleMapper sysModuleMapper;

  public SysModule getEntity(SysModule t) {
    return this.sysModuleMapper.selectEntity(t);
  }

  public Integer getEntityCount(SysModule t) {
    return this.sysModuleMapper.selectEntityCount(t);
  }

  public List<SysModule> getEntityList(SysModule t) {
    return this.sysModuleMapper.selectEntityList(t);
  }

  public List<SysModule> getEntityListByUserId(Integer userId, Integer userType) {
    SysModule sysModule = new SysModule();

    if (userType == UserType.USER_TYPE_1.getIndex()) {
      sysModule.getMap().put("is_admin", "true");
    }
    sysModule.getMap().put("userId", sysModule.getIsDel());
    List<SysModule> list = sysModuleMapper.selectEntityList(sysModule);

    return list;
  }

  public Integer createEntity(SysModule t) {
    return this.sysModuleMapper.insertEntity(t);
  }

  public int updateEntity(SysModule t) {
    return this.sysModuleMapper.updateEntity(t);
  }

  public int removeEntity(SysModule t) {
    return this.sysModuleMapper.deleteEntity(t);
  }

  public List<SysModule> getEntityPaginatedList(SysModule t) {
    return this.sysModuleMapper.selectEntityPaginatedList(t);
  }

  public List<SysModule> proGetSysModuleSonList(SysModule t) {

    // 父查子
    List<SysModule> sonList = new ArrayList<SysModule>();
    List<SysModule> proGetSysModuleSonList =
            this.getSonList(true, t.getModId(), sonList, t.getModGroup());
    return proGetSysModuleSonList;

    // return this.sysModuleDao.procedureGetSysModelSonList(t);
  }

  public List<SysModule> getSonList(
          Boolean is_first, Long par_id, List<SysModule> sonList, Integer mod_group) {

    if (is_first) {
      SysModule temp = new SysModule();
      temp.setModId(par_id);
      temp.setIsDel(0);
      temp = this.sysModuleMapper.selectEntity(temp);
      if (null != temp) {
        sonList.add(temp);
      }
    }

    SysModule SysModule = new SysModule();
    SysModule.setParId(par_id);
    SysModule.setIsDel(0);
    SysModule.setModGroup(mod_group);
    List<SysModule> SysModuleList = this.sysModuleMapper.selectEntityList(SysModule);
    if (null != SysModuleList && SysModuleList.size() > 0) {
      for (SysModule temp : SysModuleList) {
        sonList.add(temp);
        this.getSonList(false, temp.getModId(), sonList, temp.getModGroup());
      }
    }
    return sonList;
  }
}
