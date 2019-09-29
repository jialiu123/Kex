package com.moba.service;

import com.moba.domain.UserBiRecord;
import com.moba.domain.UserInfo;
import com.moba.emums.BiGetType;
import com.moba.mapper.UserBiRecordMapper;
import com.moba.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;


/**
 * @author ding, ning
 * @version 2019-03-06 11:07
 * @date 2019-03-06 11:07
 */
@Service
@Transactional
public class UserBiRecordService {

    @Resource
    private UserBiRecordMapper userBiRecordMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    public UserBiRecord getEntity(UserBiRecord t) {
        return this.userBiRecordMapper.selectEntity(t);
    }

    public Integer getEntityCount(UserBiRecord t) {
        return this.userBiRecordMapper.selectEntityCount(t);
    }

    public List<UserBiRecord> getEntityList(UserBiRecord t) {
        return this.userBiRecordMapper.selectEntityList(t);
    }

    public Integer createEntity(UserBiRecord t) {
        this.userBiRecordMapper.insertEntity(t);
        return t.getId();
    }

    public int updateEntity(UserBiRecord t) {
        return this.userBiRecordMapper.updateEntity(t);
    }

    public int removeEntity(UserBiRecord t) {
        return this.userBiRecordMapper.deleteEntity(t);
    }

    /**
     * 增加数据记录
     *
     * @param addUserId
     * @param biGetType
     * @param biChuOrRu
     * @param biNo
     * @return
     */
    public Boolean addBiRecord(Integer addUserId, Integer biGetType, Integer biType, Integer biChuOrRu, BigDecimal biNo, Integer orderId) {

        Boolean isSuccess = false;

        UserInfo userInfo = new UserInfo();
        userInfo.setId(addUserId);
        userInfo = userInfoMapper.selectEntity(userInfo);

        UserBiRecord userBiRecord = new UserBiRecord();
        userBiRecord.setBiNoBefore(userInfo.getBiDianzi());
        userBiRecord.setAddUserId(addUserId);
        userBiRecord.setBiGetType(biGetType);
        userBiRecord.setBiNo(biNo);
        userBiRecord.setBiType(biType);
        userBiRecord.setOrderId(orderId);
        userBiRecord.setBiGetMemo(BiGetType.getName(biGetType));
        userBiRecord.setBiChuOrRu(biChuOrRu);

        if (biChuOrRu > 0) {
            userBiRecord.setBiNoAfter(userInfo.getBiDianzi().add(biNo));
        } else {
            userBiRecord.setBiNoAfter(userInfo.getBiDianzi().subtract(biNo));
        }

        userBiRecordMapper.insertEntity(userBiRecord);
        if (userBiRecord.getId() > 0) {
            isSuccess = true;
        }
        return isSuccess;

    }

    public List<UserBiRecord> getEntityPaginatedList(UserBiRecord t) {
        return this.userBiRecordMapper.selectEntityPaginatedList(t);
    }

}
