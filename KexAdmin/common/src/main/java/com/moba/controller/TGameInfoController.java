package com.moba.controller;

import com.alibaba.fastjson.JSONObject;
import com.moba.annotation.ParamValid;
import com.moba.domain.TGameInfo;
import com.moba.domain.TGameServers;
import com.moba.emums.*;
import com.moba.service.BaseClassService;
import com.moba.service.BaseDataService;
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
@RequestMapping("/tGameInfo")
@Api(value = "")
public class TGameInfoController extends BaseController {

    @Resource
    TGameInfoService tGameInfoService;

    @Resource
    BaseDataService baseDataService;

    @Resource
    BaseClassService baseClassService;
    @Resource
    TGameServersService tGameServersService;

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/list")
    public Object list(HttpServletRequest request, @ModelAttribute TGameInfo entity, Integer page) {

        if (null == page) {
            page = 1;
        }

        JSONObject jsonObject = new JSONObject();
        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, FieldFilter.PREFIX);
        FieldFilter.setQuery(paramMap, entity);
        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());


        jsonObject.put("dataCount", tGameInfoService.getEntityCount(entity));

        entity.getRow().setCount(10);
        entity.getRow().setFirst((page - 1) * (entity.getRow().getCount()));
        List<TGameInfo> entityList = tGameInfoService.getEntityPaginatedList(entity);
        jsonObject.put("entityList", entityList);


        jsonObject.put("gameClassList", super.getBaseClassesList(ClsScope.CLS_SCORE_0.getIndex()));
        jsonObject.put("gamePlatformList", super.getBaseDataList(BaseDataType.BaseDataType_40.getIndex()));
        jsonObject.put("languageTypeList", super.parseEnmuToJSONArray(LanguageType.class, LanguageType.values()));
        return ResultUtils.success(jsonObject);
    }


    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/getGameServerList")
    public Object getGameServerList(@RequestParam Integer gameId) {
        TGameServers tGameServers = new TGameServers();
        tGameServers.setGameId(gameId);
        tGameServers.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        List<TGameServers> tGameServersList = tGameServersService.getEntityList(tGameServers);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tGameServersList", tGameServersList);
        return ResultUtils.success(jsonObject);
    }

    @ParamValid
    @ApiOperation(value = "保存")
    @PostMapping(value = "/save")
    public Object save(@RequestBody @Valid TGameInfo entity, BindingResult result) {

        if (null == entity.getId()) {
            entity.setAddDate(new Date());
            tGameInfoService.createEntity(entity);
        } else {
            entity.setUpdateDate(new Date());
            tGameInfoService.updateEntity(entity);
        }
        return ResultUtils.success();
    }


    @ApiOperation(value = "查询单条记录")
    @GetMapping(value = "/{id}")
    public Object edit(@PathVariable(name = "id") Integer id) {

        TGameInfo entity = new TGameInfo();
        entity.setId(id);
        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        entity = tGameInfoService.getEntity(entity);
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
        TGameInfo entity = new TGameInfo();
        entity.setIsDel(YesOrNo.YES_OR_NO_1.getIndex());
        entity.setDelDate(new Date());
        entity.setDelUserId(userId);
        if (null != id) {
            entity.setId(id);
        } else {
            entity.getMap().put("pks", pks);
        }

        tGameInfoService.updateEntity(entity);
        return ResultUtils.success();
    }

}