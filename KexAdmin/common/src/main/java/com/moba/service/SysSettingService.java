package com.moba.service;

import com.moba.domain.SysSetting;
import com.moba.mapper.SysSettingMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author ding, ning
 * @version 2019-03-11 09:19
 * @date 2019-03-11 09:19
 */
@Service
@Transactional
public class SysSettingService {

    @Resource
    private SysSettingMapper sysSettingMapper;

    public SysSetting getEntity(SysSetting t) {
        return this.sysSettingMapper.selectEntity(t);
    }

    public Integer getEntityCount(SysSetting t) {
        return this.sysSettingMapper.selectEntityCount(t);
    }

    public List<SysSetting> getEntityList(SysSetting t) {
        return this.sysSettingMapper.selectEntityList(t);
    }

    public Integer createEntity(SysSetting t) {
        this.sysSettingMapper.insertEntity(t);
        return null;
    }

    public int updateEntity(SysSetting t) {
        return this.sysSettingMapper.updateEntity(t);
    }

    public int removeEntity(SysSetting t) {
        return this.sysSettingMapper.deleteEntity(t);
    }

    public List<SysSetting> getEntityPaginatedList(SysSetting t) {
        return this.sysSettingMapper.selectEntityPaginatedList(t);
    }

}
