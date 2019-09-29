package com.moba.controller;

import com.moba.domain.UserInfo;
import com.moba.emums.BaseDataType;
import com.moba.service.UserInfoService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liujia
 * @desc 基础的类
 */
@Component
public class BaseUserController extends BaseController {

    @Resource
    UserInfoService userInfoService;

    /**
     * 获取用户信息
     */
    public UserInfo getUserInfo(Integer id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        return userInfoService.getEntity(userInfo);
    }

    /**
     * 获取提现总余额和手续费
     */
    public Map<String, BigDecimal> getMoneyApply(Integer id) {
        UserInfo userInfo = this.getUserInfo(id);
        if (null == userInfo) {
            return null;
        }
        Map<String, BigDecimal> map = new HashMap();
        BigDecimal rate = new BigDecimal(super.getBaseData(BaseDataType.BaseDataType_30.getIndex()).getPreData());
        map.put("biDianzi", userInfo.getBiDianzi());
        map.put("rate", rate);
        return map;
    }

}
