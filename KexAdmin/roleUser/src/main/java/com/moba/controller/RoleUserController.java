package com.moba.controller;

import com.moba.domain.RoleUser;
import com.moba.emums.CodeType;
import com.moba.service.RoleUserService;
import com.moba.utils.FieldFilter;
import com.moba.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author liu, jia
 * @version 2019-02-20 11:35
 * @date 2019-02-20 11:35
 */
@RestController
@RequestMapping("/roleUser")
@Api(value = "")
public class RoleUserController extends BaseController {

    @Resource
    RoleUserService roleUserService;

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/list")
    public Object list(HttpServletRequest request, @ModelAttribute RoleUser entity,
                       @RequestParam(name = "page") Integer page) {

        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, FieldFilter.PREFIX);
        FieldFilter.setQuery(paramMap, entity);

        entity.getRow().setCount(10);
        entity.getRow().setFirst((page - 1) * (entity.getRow().getCount()));
        List<RoleUser> entityList = roleUserService.getEntityPaginatedList(entity);
        return ResultUtils.success(entityList);
    }


    @ApiOperation(value = "查询单条记录")
    @GetMapping(value = "/{id}")
    public Object edit(@PathVariable(name = "id") Integer id) {

        RoleUser entity = new RoleUser();
        entity.setId(id);
        entity = roleUserService.getEntity(entity);
        if (null == entity) {
            return ResultUtils.error(CodeType.CODE_TYPE_102);
        }
        return ResultUtils.success(entity);
    }


    @ApiOperation(value = "根据id删除")
    @PostMapping(value = "/{id}")
    public Object delete(@PathVariable(name = "id") Integer id) {
        RoleUser entity = new RoleUser();

        entity.setId(id);
        entity = roleUserService.getEntity(entity);
        if (null == entity) {
            return ResultUtils.error(CodeType.CODE_TYPE_102);
        }
        roleUserService.updateEntity(entity);
        return ResultUtils.success();
    }

}