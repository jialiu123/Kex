package com.moba.controller;

import com.alibaba.fastjson.JSONObject;
import com.moba.domain.Result;
import com.moba.domain.UserInfo;
import com.moba.emums.CodeType;
import com.moba.emums.IsDel;
import com.moba.service.SysModuleService;
import com.moba.service.UserInfoService;
import com.moba.utils.ResultUtils;
import com.moba.utils.encrypt.DESPlusUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 登录接口
 *
 * @author liujia
 */
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

  @Autowired UserInfoService userInfoService;

  @Resource SysModuleService sysModuleService;

  @ApiOperation(value = "自定义登录")
  @GetMapping(value = "/getToken")
  public Object getToken(
      String username, String password, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    UserInfo userInfo = new UserInfo();
    Result<Object> result = new Result<Object>();
    if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
      return ResultUtils.error(CodeType.CODE_TYPE_101.getIndex(), "用户名密码为空，或参数名错误");
    }
    userInfo.setUserName(username);
    DESPlusUtils des = new DESPlusUtils();
    userInfo.setPassword(des.encrypt(password));
    userInfo.setIsDel(IsDel.IS_DEL_0.getIndex());

    UserInfo userInfoQuery = userInfoService.getEntity(userInfo);
    if (null == userInfoQuery) {
      return ResultUtils.error(CodeType.CODE_TYPE_101.getIndex(), "用户名，密码错误");
    }

    // 更新登录次数
    UserInfo ui = new UserInfo();
    ui.setId(userInfoQuery.getId());
    ui.setLoginCount(userInfoQuery.getLoginCount() + 1);
    ui.setLastLoginTime(new Date());
    userInfoService.updateEntity(ui);

    // 增加登录日志
    //        super.createSysOperLog(request, SysOperType.SysOperType_10.getIndex(),
    //                userInfoQuery.getId().toString(), userInfoQuery.getClass().getSimpleName(),
    // userInfoQuery.getId());


    JSONObject jsonObject = new JSONObject();

    return ResultUtils.success(jsonObject);
  }

}
