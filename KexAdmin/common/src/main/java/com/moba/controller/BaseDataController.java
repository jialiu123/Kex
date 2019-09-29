package com.moba.controller;

import com.alibaba.fastjson.JSONObject;
import com.moba.domain.BaseData;
import com.moba.emums.CodeType;
import com.moba.emums.LanguageType;
import com.moba.emums.YesOrNo;
import com.moba.service.BaseDataService;
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
 * @author ding, ning
 * @version 2019-02-28 17:24
 * @date 2019-02-28 17:24
 */
@RestController
@RequestMapping("/baseData")
@Api(value = "")
public class BaseDataController extends BaseController {

    @Resource
    BaseDataService baseDataService;

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/list")
    public Object list(HttpServletRequest request, @ModelAttribute BaseData entity, Integer page) {

        if (null == page) {
            page = 1;
        }

        JSONObject jsonObject = new JSONObject();

        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, FieldFilter.PREFIX);
        FieldFilter.setQuery(paramMap, entity);

        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());

        jsonObject.put("dataCount", baseDataService.getEntityCount(entity));
        jsonObject.put("languageTypeList", super.parseEnmuToJSONArray(LanguageType.class, LanguageType.values()));
        entity.getRow().setCount(10);
        entity.getRow().setFirst((page - 1) * (entity.getRow().getCount()));
        List<BaseData> entityList = baseDataService.getEntityPaginatedList(entity);
        jsonObject.put("entityList", entityList);
        return ResultUtils.success(jsonObject);
    }


    @ApiOperation(value = "保存")
    @PostMapping(value = "/save")

    public Object save(@RequestBody @Valid BaseData entity, BindingResult result) {

        if (null == entity.getId()) {
            entity.setAddDate(new Date());
            baseDataService.createEntity(entity);
        } else {
            entity.setUpdateDate(new Date());
            baseDataService.updateEntity(entity);
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "查询单条记录")
    @GetMapping(value = "/{id}")
    public Object edit(@PathVariable(name = "id") Integer id) {

        BaseData entity = new BaseData();
        entity.setId(id);
        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        entity = baseDataService.getEntity(entity);
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
        BaseData entity = new BaseData();
        entity.setIsDel(YesOrNo.YES_OR_NO_1.getIndex());
        entity.setDelDate(new Date());
        entity.setDelUserId(userId);
        if (null != id) {
            entity.setId(id);
        } else {
            entity.getMap().put("pks", pks);
        }
        baseDataService.updateEntity(entity);
        return ResultUtils.success();
    }

}