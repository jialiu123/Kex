package com.moba.controller;

import com.moba.domain.SysModule;
import com.moba.emums.CodeType;
import com.moba.emums.YesOrNo;
import com.moba.service.SysModuleService;
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
 * @version 2019-02-20 15:57
 * @date 2019-02-20 15:57
 */
@RestController
@RequestMapping("/sysModule")
@Api(value = "")
public class SysModuleController extends BaseController {

    @Resource
    SysModuleService sysModuleService;

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/list")
    public Object list(
            HttpServletRequest request,
            @ModelAttribute SysModule entity,
            @RequestParam(name = "page") Integer page) {

        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, FieldFilter.PREFIX);
        FieldFilter.setQuery(paramMap, entity);

        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        entity.getRow().setCount(10);
        entity.getRow().setFirst((page - 1) * (entity.getRow().getCount()));
        List<SysModule> entityList = sysModuleService.getEntityPaginatedList(entity);
        return ResultUtils.success(entityList);
    }

    @ApiOperation(value = "保存")
    @PostMapping(value = "/save")
    public Object save(@RequestBody SysModule entity) {

        if (null == entity.getModId()) {
            sysModuleService.createEntity(entity);
        } else {
            sysModuleService.updateEntity(entity);
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "修改")
    @PostMapping(value = "/edit")
    public Object edit(@RequestBody SysModule entity) {
        if (null == entity.getModId()) {
            return ResultUtils.error(CodeType.CODE_TYPE_101);
        }
        entity = sysModuleService.getEntity(entity);

        return ResultUtils.success(entity);
    }
}
