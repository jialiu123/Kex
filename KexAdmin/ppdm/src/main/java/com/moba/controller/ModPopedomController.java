package com.moba.controller;

import com.moba.domain.ModPopedom;
import com.moba.domain.SysModule;
import com.moba.emums.CodeType;
import com.moba.service.ModPopedomService;
import com.moba.service.SysModuleService;
import com.moba.utils.FieldFilter;
import com.moba.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author liu, jia
 * @version 2019-02-20 15:57
 * @date 2019-02-20 15:57
 */
@RestController
@RequestMapping("/modPopedom")
@Api(value = "")
public class ModPopedomController extends BaseController {

    @Resource
    ModPopedomService modPopedomService;
    @Resource
    SysModuleService sysModuleService;

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/list")
    public Object list(
            HttpServletRequest request,
            @ModelAttribute ModPopedom entity,
            @RequestParam(name = "page") Integer page) {

        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, FieldFilter.PREFIX);
        FieldFilter.setQuery(paramMap, entity);

        entity.getRow().setCount(10);
        entity.getRow().setFirst((page - 1) * (entity.getRow().getCount()));
        List<ModPopedom> entityList = modPopedomService.getEntityPaginatedList(entity);
        return ResultUtils.success(entityList);
    }

    @ApiOperation(value = "保存")
    @PostMapping(value = "/save")
    public Object save(
            @RequestBody ModPopedom entity, @RequestParam(value = "modIds") String modIds) {

        List<SysModule> sysModuleAllList = new ArrayList<SysModule>();
        SysModule sysModule1 = new SysModule();
        sysModule1.setIsDel(0);
        sysModule1.setIsPublic(0);
        sysModule1.setModLevel(1);
        List<SysModule> sysModuleList = sysModuleService.getEntityList(sysModule1);
        for (SysModule sm : sysModuleList) {
            List<SysModule> sysModuleSonList = sysModuleService.proGetSysModuleSonList(sm);
            if (null != sysModuleSonList && sysModuleSonList.size() > 0) {
                sysModuleAllList.addAll(sysModuleSonList);
            }
        }

        ModPopedom mod_popedom = new ModPopedom();
        if (null != entity.getRoleId()) {
            mod_popedom.setRoleId(entity.getRoleId());
        } else if (null != entity.getUserId()) {
            mod_popedom.setUserId(entity.getUserId());
        }

        Set<String> modIdSet = new HashSet<String>();
        List<ModPopedom> modPopedomList = new ArrayList<ModPopedom>();
        int listIndex = -1;

        mod_popedom.setModPopedomList(modPopedomList);
        modPopedomService.createEntity(mod_popedom);
        return ResultUtils.success();
    }

    @ApiOperation(value = "查询单条记录")
    @GetMapping(value = "/{id}")
    public Object edit(@PathVariable(name = "id") Integer id) {

        ModPopedom entity = new ModPopedom();
        entity.setId(id);
        entity = modPopedomService.getEntity(entity);
        if (null == entity) {
            return ResultUtils.error(CodeType.CODE_TYPE_102);
        }
        return ResultUtils.success(entity);
    }

    @ApiOperation(value = "根据id删除")
    @PostMapping(value = "/{id}")
    public Object delete(@PathVariable(name = "id") Integer id) {
        ModPopedom entity = new ModPopedom();

        entity.setId(id);
        entity = modPopedomService.getEntity(entity);
        if (null == entity) {
            return ResultUtils.error(CodeType.CODE_TYPE_102);
        }

        modPopedomService.updateEntity(entity);
        return ResultUtils.success();
    }
}
