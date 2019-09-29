package com.moba.service;

import com.moba.domain.Msg;
import com.moba.emums.InfoState;
import com.moba.emums.YesOrNo;
import com.moba.mapper.MsgMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @author li, bai,qiang
 * @version 2019-03-07 16:15
 * @date 2019-03-07 16:15
 */
@Service
@Transactional
public class MsgService {

    @Resource
    private MsgMapper msgMapper;


    public Msg getEntity(Msg t) {
        return this.msgMapper.selectEntity(t);
    }

    public Integer getEntityCount(Msg t) {
        return this.msgMapper.selectEntityCount(t);
    }

    public List<Msg> getEntityList(Msg t) {
        return this.msgMapper.selectEntityList(t);
    }

    public Integer createEntity(Msg t) {
        this.msgMapper.insertEntity(t);
        return t.getId();
    }

    /**
     * @author li, bai,qiang
     * @version 2019-03-08 11:15
     * 此公共方法用来发送信息，向数据库中插入记录
     * 在调用此方法时外层需通过userID把用户名查出来然后塞进对象里
     */

    public Integer send(Msg t) {

        if (null != t.getSendUserId() && null != t.getSendUserName()) {
            t.setSendTime(new Date());
            t.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
            t.setInfoState(InfoState.INFO_STATE_1.getIndex());

            this.msgMapper.insertEntity(t);
        }

        return t.getId();
    }

    public int updateEntity(Msg t) {
        return this.msgMapper.updateEntity(t);
    }

    public int removeEntity(Msg t) {
        return this.msgMapper.deleteEntity(t);
    }

    public List<Msg> getEntityPaginatedList(Msg t) {
        return this.msgMapper.selectEntityPaginatedList(t);
    }

}
