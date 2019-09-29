package com.moba.controller;

import com.alibaba.fastjson.JSONObject;
import com.moba.domain.UserInfo;
import com.moba.emums.CodeType;
import com.moba.emums.YesOrNo;
import com.moba.emums.roleUser.InitPassword;
import com.moba.emums.roleUser.UserType;
import com.moba.service.UserInfoService;
import com.moba.utils.FieldFilter;
import com.moba.utils.ResultUtils;
import com.moba.utils.encrypt.DESPlusUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author liu, jia
 * @version 2019-02-20 09:59
 * @date 2019-02-20 09:59
 */
@RestController
@RequestMapping("/userInfo")
@Api(tags = {"用户接口"})
public class UserInfoController extends BaseController {

    @Resource
    UserInfoService userInfoService;

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/list")
    public Object list(
            HttpServletRequest request,
            @ModelAttribute UserInfo entity,
            @RequestParam(name = "page") Integer page) {
        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, FieldFilter.PREFIX);
        FieldFilter.setQuery(paramMap, entity);

        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("dataCount", userInfoService.getEntityCount(entity));

        entity.getRow().setCount(10);

        entity.getRow().setFirst((page - 1) * (entity.getRow().getCount()));
        List<UserInfo> entityList = userInfoService.getEntityPaginatedList(entity);
        jsonObject.put("entityList", entityList);

        jsonObject.put("userTypeList", super.parseEnmuToJSONArray(UserType.class, UserType.values()));
        System.out.println(jsonObject);
        return ResultUtils.success(jsonObject);
    }

    @ApiOperation(value = "保存")
    @PostMapping(value = "/save")
    public Object save(@RequestBody @Valid UserInfo entity, BindingResult result)
            throws Exception {
        System.out.println("getUserLogo:" + entity.getUserLogo());
        DESPlusUtils des = new DESPlusUtils();
        if (null == entity.getId()) {
            UserInfo ui1 = new UserInfo();
            if (StringUtils.isBlank(entity.getUserName())) {
                String msg = "用户名不能为空！";
                return ResultUtils.error(CodeType.CODE_TYPE_101.getIndex(), msg);
            }
            ui1.setUserName(entity.getUserName());
            ui1.setIsDel(0);
            int count1 = userInfoService.getEntityCount(ui1);
            if (count1 > 0) {
                String msg = "用户名已存在！";
                return ResultUtils.error(CodeType.CODE_TYPE_101.getIndex(), msg);
            }

            if (StringUtils.isBlank(entity.getPassword())) {
                entity.setPassword(des.encrypt(InitPassword.INIT_PWD));
            } else {
                entity.setPassword(des.encrypt(entity.getPassword()));
            }

            entity.setAddDate(new Date());
            userInfoService.createEntity(entity);
        } else {
            if (StringUtils.isBlank(entity.getUserName()) || StringUtils.isBlank(entity.getPassword())) {
                String msg = "用户名或密码不能为空！";
                return ResultUtils.error(CodeType.CODE_TYPE_101.getIndex(), msg);
            }

            UserInfo user1 = new UserInfo();
            user1.setId(entity.getId());
            String oldName = userInfoService.getEntity(user1).getUserName();

            if (!oldName.equals(entity.getUserName())) {
                UserInfo ui1 = new UserInfo();
                ui1.setUserName(entity.getUserName());
                int count1 = userInfoService.getEntityCount(ui1);
                if (count1 > 0) {
                    String msg = "用户名已存在！";
                    return ResultUtils.error(CodeType.CODE_TYPE_101.getIndex(), msg);
                }
            }


            UserInfo user = new UserInfo();
            user.setId(entity.getId());
            String oldPwd = userInfoService.getEntity(user).getPassword();
            if (!oldPwd.equals(entity.getPassword())) {
                entity.setPassword(des.encrypt(entity.getPassword()));
            }
            entity.setUpdateDate(new Date());
            userInfoService.updateEntity(entity);
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "查询单条记录")
    @GetMapping(value = "/{id}")
    public Object edit(@PathVariable(name = "id") Integer id) {

        UserInfo entity = new UserInfo();
        entity.setId(id);
        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        entity = userInfoService.getEntity(entity);
        if (null == entity) {
            return ResultUtils.error(CodeType.CODE_TYPE_102);
        }
        return ResultUtils.success(entity);
    }

    @ApiOperation(value = "根据id删除")
    @PostMapping(value = "/delete")
    public Object delete(Integer id, Integer delUserId, String[] pks) {

        if (null == id && ArrayUtils.isEmpty(pks)) {
            return ResultUtils.error(CodeType.CODE_TYPE_101);
        }
        UserInfo entity = new UserInfo();
        entity.setIsDel(YesOrNo.YES_OR_NO_1.getIndex());
        entity.setDelDate(new Date());
        entity.setDelUserId(delUserId);
        if (null != id) {
            entity.setId(id);
        } else {
            entity.getMap().put("pks", pks);
        }
        userInfoService.updateEntity(entity);
        return ResultUtils.success();

    }
}
