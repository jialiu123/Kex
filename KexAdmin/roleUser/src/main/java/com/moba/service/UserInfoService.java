package com.moba.service;

import com.moba.domain.UserInfo;
import com.moba.emums.BiChuOrRu;
import com.moba.emums.BiType;
import com.moba.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;


/**
 * @author liu, jia
 * @version 2019-02-20 09:59
 * @date 2019-02-20 09:59
 */
@Service
@Transactional
public class UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserBiRecordService userBiRecordService;

    public UserInfo getEntity(UserInfo t) {
        return this.userInfoMapper.selectEntity(t);
    }

    public Integer getEntityCount(UserInfo t) {
        return this.userInfoMapper.selectEntityCount(t);
    }

    public List<UserInfo> getEntityList(UserInfo t) {
        return this.userInfoMapper.selectEntityList(t);
    }

    public Integer createEntity(UserInfo t) {
        this.userInfoMapper.insertEntity(t);
        return t.getId();
    }

    public int updateEntity(UserInfo t) {
        return this.userInfoMapper.updateEntity(t);
    }

    public int removeEntity(UserInfo t) {
        return this.userInfoMapper.deleteEntity(t);
    }

    public List<UserInfo> getEntityPaginatedList(UserInfo t) {
        return this.userInfoMapper.selectEntityPaginatedList(t);
    }

    /**
     * 增加账单记录，同时把用户的余额更新成最新的余额
     *
     * @param userId(用户id)
     * @param biGetType(枚举BiGetType中余额出入方式)
     * @param biType(枚举BiType中余额是什么类型的数据比如是金币还是积分还是钱)
     * @param biChuOrRu(枚举BiChuOrRu中余额是减少还是增加)
     * @param biNo(余额的增加或者减少的量)
     * @param orderId(订单的id)
     * @return
     * @author ding, ning
     */
    public Boolean updateBi(Integer userId, Integer biGetType, Integer biType, Integer biChuOrRu, BigDecimal biNo, Integer orderId) {

        boolean addBiRecordFlag = userBiRecordService.addBiRecord(userId, biGetType, biType, biChuOrRu, biNo, orderId);

        if (addBiRecordFlag) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(userId);
            if (biType.intValue() == BiType.BI_TYPE_100.getIndex()) {
                if (biChuOrRu.intValue() == BiChuOrRu.BI_CHU_OR_RU_1.getIndex()) {
                    userInfo.getMap().put("addBiDianZi", biNo);
                }
                if (biChuOrRu.intValue() == BiChuOrRu.BI_CHU_OR_RU_X1.getIndex()) {
                    userInfo.getMap().put("subBiDianZi", biNo);
                }
            }
            int count = userInfoMapper.updateEntity(userInfo);
            if (count <= 0) {
                addBiRecordFlag = false;
            }
        }
        return addBiRecordFlag;

    }

}
