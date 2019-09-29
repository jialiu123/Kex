package com.moba.service;

import com.moba.domain.BaseData;
import com.moba.mapper.BaseDataMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author ding, ning
 * @version 2019-02-28 17:24
 * @date 2019-02-28 17:24
 */
@Service
@Transactional
public class BaseDataService {

    @Resource
    private BaseDataMapper baseDataMapper;

    public BaseData getEntity(BaseData t) {
        return this.baseDataMapper.selectEntity(t);
    }

    public Integer getEntityCount(BaseData t) {
        return this.baseDataMapper.selectEntityCount(t);
    }

    public List<BaseData> getEntityList(BaseData t) {
        return this.baseDataMapper.selectEntityList(t);
    }

    public Integer createEntity(BaseData t) {
        this.baseDataMapper.insertEntity(t);
        return t.getId();
    }

    public int updateEntity(BaseData t) {
        return this.baseDataMapper.updateEntity(t);
    }

    public int removeEntity(BaseData t) {
        return this.baseDataMapper.deleteEntity(t);
    }

    public List<BaseData> getEntityPaginatedList(BaseData t) {
        return this.baseDataMapper.selectEntityPaginatedList(t);
    }

}
