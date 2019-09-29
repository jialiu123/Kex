package com.moba.controller;

import com.alibaba.fastjson.JSONObject;
import com.moba.annotation.ParamValid;
import com.moba.domain.TGameInfo;
import com.moba.domain.TGameServers;
import com.moba.emums.CodeType;
import com.moba.emums.YesOrNo;
import com.moba.service.TGameInfoService;
import com.moba.service.TGameServersService;
import com.moba.utils.FieldFilter;
import com.moba.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
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
 * @version 2019-03-01 10:43
 * @date 2019-03-01 10:43
 */
@RestController
@RequestMapping("/tGameServers")
@Api(value = "")
public class TGameServersController extends BaseController {

    @Resource
    TGameServersService tGameServersService;
    @Resource
    TGameInfoService tGameInfoService;

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/list")
    public Object list(HttpServletRequest request, @ModelAttribute TGameServers entity, Integer page) {
        if (null == page) {
            page = 1;
        }

        JSONObject jsonObject = new JSONObject();
        Map
                <String, Object> paramMap = WebUtils.getParametersStartingWith(request, FieldFilter.PREFIX);
        FieldFilter.setQuery(paramMap, entity);

        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        jsonObject.put("dataCount", tGameServersService.getEntityCount(entity));
        entity.getRow().setCount(10);
        entity.getRow().setFirst((page - 1) * (entity.getRow().getCount()));
        List<TGameServers> entityList = tGameServersService.getEntityPaginatedList(entity);
        jsonObject.put("entityList", entityList);
        return ResultUtils.success(jsonObject);
    }

    @ParamValid
    @ApiOperation(value = "保存")
    @PostMapping(value = "/save")
    public Object save(@RequestBody @Valid TGameServers entity, BindingResult result) {

        if (null == entity.getId()) {
            TGameInfo tGameInfo = new TGameInfo();
            tGameInfo.setId(entity.getParId());
            tGameInfo.setIsDel(0);
            tGameInfo = tGameInfoService.getEntity(tGameInfo);
            if (null == tGameInfo) {
                return ResultUtils.error(CodeType.CODE_TYPE_102);
            }
            entity.setGameId(entity.getParId());
            entity.setAddDate(new Date());
            tGameServersService.createEntity(entity);
        } else {
            entity.setUpdateDate(new Date());
            tGameServersService.updateEntity(entity);
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "查询单条记录")
    @GetMapping(value = "/{id}")
    public Object edit(@PathVariable(name = "id") Integer id) {

        TGameServers entity = new TGameServers();
        entity.setId(id);
        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        entity = tGameServersService.getEntity(entity);
        if (null == entity) {
            return ResultUtils.error(CodeType.CODE_TYPE_102);
        }
        return ResultUtils.success(entity);
    }

    @ApiOperation(value = "删除")
    @PostMapping(value = "/delete")
    public Object delete(Integer id, String pks[], Integer userId) {
        if (null == id && ArrayUtils.isEmpty(pks)) {
            return ResultUtils.error(CodeType.CODE_TYPE_101);
        }

        TGameServers entity = new TGameServers();
        entity.setIsDel(YesOrNo.YES_OR_NO_1.getIndex());
        entity.setDelDate(new Date());
        entity.setDelUserId(userId);
        if (null != id) {
            entity.setId(id);
        } else {
            entity.getMap().put("pks", pks);
        }
        tGameServersService.updateEntity(entity);
        return ResultUtils.success();
    }
}