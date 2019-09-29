package com.moba.controller;

import com.alibaba.fastjson.JSONObject;
import com.moba.domain.Role;
import com.moba.emums.CodeType;
import com.moba.emums.YesOrNo;
import com.moba.service.RoleService;
import com.moba.utils.FieldFilter;
import com.moba.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author liu, jia
 * @version 2019-02-20 11:29
 * @date 2019-02-20 11:29
 */
@RestController
@RequestMapping("/role")
@Api(value = "")
public class RoleController extends BaseController {

    @Resource
    RoleService roleService;

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/list")
    public Object list(HttpServletRequest request, @ModelAttribute Role entity, Integer page) {
        if (null == page) {
            page = 1;
        }

        JSONObject jsonObject = new JSONObject();

        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, FieldFilter.PREFIX);
        FieldFilter.setQuery(paramMap, entity);

        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());

        jsonObject.put("dataCount", roleService.getEntityCount(entity));

        entity.getRow().setCount(10);
        entity.getRow().setFirst((page - 1) * (entity.getRow().getCount()));
        List<Role> entityList = roleService.getEntityPaginatedList(entity);
        jsonObject.put("entityList", entityList);
        return ResultUtils.success(jsonObject);
    }

    @ApiOperation(value = "保存")
    @PostMapping(value = "/save")
    public Object save(@RequestBody Role entity) {

        if (null == entity.getId()) {
            entity.setAddDate(new Date());
            roleService.createEntity(entity);
        } else {
            entity.setUpdateDate(new Date());
            roleService.updateEntity(entity);
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "查询单条记录")
    @GetMapping(value = "/{id}")
    public Object edit(@PathVariable(name = "id") Integer id) {
        Role entity = new Role();
        entity.setId(id);
        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        entity = roleService.getEntity(entity);
        if (null == entity) {
            return ResultUtils.error(CodeType.CODE_TYPE_102);
        }
        return ResultUtils.success(entity);
    }

    @ApiOperation(value = "删除")
    @PostMapping(value = "/delete")
    public Object delete(Integer id, String pks[], Integer userId) {
        if (null == id && null == pks && pks.length == 0) {
            return ResultUtils.error(CodeType.CODE_TYPE_101);
        }
        Role entity = new Role();
        entity.setIsDel(YesOrNo.YES_OR_NO_1.getIndex());
        entity.setDelDate(new Date());
        entity.setDelUserId(userId);
        if (null != id) {
            entity.setId(id);
        } else {
            entity.getMap().put("pks", pks);
        }
        roleService.updateEntity(entity);
        return ResultUtils.success();
    }
}
