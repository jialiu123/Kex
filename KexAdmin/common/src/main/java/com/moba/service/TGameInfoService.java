package com.moba.service;

import com.moba.domain.TGameInfo;
import com.moba.domain.TGameServers;
import com.moba.emums.YesOrNo;
import com.moba.mapper.TGameInfoMapper;
import com.moba.mapper.TGameServersMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @author liu, jia
 * @version 2019-03-01 11:02
 * @date 2019-03-01 11:02
 */
@Service
@Transactional
public class TGameInfoService {

    @Resource
    private TGameInfoMapper tGameInfoMapper;

    @Resource
    private TGameServersMapper tGameServersMapper;

    public TGameInfo getEntity(TGameInfo t) {
        return this.tGameInfoMapper.selectEntity(t);
    }

    public Integer getEntityCount(TGameInfo t) {
        return this.tGameInfoMapper.selectEntityCount(t);
    }

    public List<TGameInfo> getEntityList(TGameInfo t) {
        return this.tGameInfoMapper.selectEntityList(t);
    }

    public Integer createEntity(TGameInfo t) {
        int id = this.tGameInfoMapper.insertEntity(t);
        List<TGameServers> tGameServersList = t.getTGameServersList();
        if (tGameServersList != null && tGameServersList.size() > 0) {
            for (TGameServers tGameServers : tGameServersList) {
                tGameServers.setGameId(t.getId());
                tGameServersMapper.insertEntity(tGameServers);
            }
        }

        return id;
    }

    public int updateEntity(TGameInfo t) {

        TGameServers tGameServers = new TGameServers();
        tGameServers.setIsDel(YesOrNo.YES_OR_NO_1.getIndex());
        tGameServers.setDelDate(new Date());
        tGameServers.setDelUserId(t.getDelUserId());
        if (t.getId() != null) {
            tGameServers.setGameId(t.getId());
            tGameServersMapper.updateEntity(tGameServers);
        } else {
            tGameServers.getMap().put("gameId_pks", t.getMap().get("pks"));
            tGameServersMapper.updateEntity(tGameServers);
        }

        return this.tGameInfoMapper.updateEntity(t);
    }

    public int removeEntity(TGameInfo t) {
        return this.tGameInfoMapper.deleteEntity(t);
    }

    public List<TGameInfo> getEntityPaginatedList(TGameInfo t) {
        return this.tGameInfoMapper.selectEntityPaginatedList(t);
    }

}
