package com.moba.service;

import com.moba.domain.UserMoneyApply;
import com.moba.emums.BiChuOrRu;
import com.moba.mapper.UserMoneyApplyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author li, bai,qinag
 * @version 2019-03-06 11:20
 * @date 2019-03-06 11:20
 */
@Service
@Transactional
public class UserMoneyApplyService {

    @Resource
    private UserMoneyApplyMapper userMoneyApplyMapper;

    @Resource
    private UserInfoService userInfoService;

    public UserMoneyApply getEntity(UserMoneyApply t) {
        return this.userMoneyApplyMapper.selectEntity(t);
    }

    public Integer getEntityCount(UserMoneyApply t) {
        return this.userMoneyApplyMapper.selectEntityCount(t);
    }

    public List<UserMoneyApply> getEntityList(UserMoneyApply t) {
        return this.userMoneyApplyMapper.selectEntityList(t);
    }

    public Integer createEntity(UserMoneyApply t) {

        /**
         * 这个用于币种的出入,然后操作的东西
         * 1、需要插入出入记录
         * 2、更新用户对应字段
         * 3、插入提现记录
         * */
        if (null != t.getMap().get("biOutAndDoThing")) {
            Boolean updateBiFlag = userInfoService.updateBi(t.getUserId(),
                    t.getCashType(),
                    Integer.valueOf(t.getMap().get("biType").toString()).intValue(),
                    BiChuOrRu.BI_CHU_OR_RU_X1.getIndex(),
                    t.getCashCount(), null);
            if (updateBiFlag) {
                this.userMoneyApplyMapper.insertEntity(t);
                return t.getId();
            } else {
                return 0;
            }
        } else {
            return this.userMoneyApplyMapper.insertEntity(t);
        }
    }

    public int updateEntity(UserMoneyApply t) {
        return this.userMoneyApplyMapper.updateEntity(t);
    }

    public int removeEntity(UserMoneyApply t) {
        return this.userMoneyApplyMapper.deleteEntity(t);
    }

    public List<UserMoneyApply> getEntityPaginatedList(UserMoneyApply t) {
        return this.userMoneyApplyMapper.selectEntityPaginatedList(t);
    }

}
