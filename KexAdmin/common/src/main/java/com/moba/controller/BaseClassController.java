package com.moba.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moba.domain.BaseClass;
import com.moba.emums.CodeType;
import com.moba.emums.LanguageType;
import com.moba.emums.YesOrNo;
import com.moba.service.BaseClassService;
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
 * @version 2019-03-01 14:57
 * @date 2019-03-01 14:57
 */
@RestController
@RequestMapping("/baseClass")
@Api(value = "")
public class BaseClassController extends BaseController {

    @Resource
    BaseClassService baseClassService;

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/list")
    public Object list(HttpServletRequest request, @ModelAttribute BaseClass entity, Integer page) {

        if (null == page) {
            page = 1;
        }

        JSONObject jsonObject = new JSONObject();

        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, FieldFilter.PREFIX);
        FieldFilter.setQuery(paramMap, entity);

        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());

        jsonObject.put("dataCount", baseClassService.getEntityCount(entity));
        jsonObject.put("languageTypeList", super.parseEnmuToJSONArray(LanguageType.class, LanguageType.values()));
        entity.getRow().setCount(10);
        entity.getRow().setFirst((page - 1) * (entity.getRow().getCount()));
        List<BaseClass> entityList = baseClassService.getEntityPaginatedList(entity);
        jsonObject.put("entityList", entityList);
        return ResultUtils.success(jsonObject);
    }


    @ApiOperation(value = "获取树结构的列表")
    @GetMapping(value = "/treeList")
    public Object treeList(HttpServletRequest request, @ModelAttribute BaseClass entity) {

        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        entity.setClsLevel(0);
        entity = baseClassService.getEntity(entity);

        if (null == entity) {
            return ResultUtils.error(CodeType.CODE_TYPE_102);
        }

        JSONObject jsonObject = new JSONObject();


        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjectRoot = new JSONObject();
        jsonObjectRoot.put("entity", entity);
        jsonObjectRoot.put("title", entity.getClsName());
        jsonObjectRoot.put("expand", true);
        jsonObjectRoot = this.baseClassService.getSonListForTree(entity.getClsId(), jsonObjectRoot, entity.getClsScope());

        jsonArray.add(jsonObjectRoot);

        jsonObject.put("treeData", jsonArray);
        jsonObject.put("languageTypeList", super.parseEnmuToJSONArray(LanguageType.class, LanguageType.values()));

        return ResultUtils.success(jsonObject);
    }


    @ApiOperation(value = "保存")
    @PostMapping(value = "/save")
    public Object save(@RequestBody @Valid BaseClass entity, BindingResult result) {

        if (null == entity.getClsId()) {
            entity.setAddDate(new Date());
            baseClassService.createEntity(entity);
        } else {
            entity.setUpdateDate(new Date());
            baseClassService.updateEntity(entity);
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "查询单条记录")
    @GetMapping(value = "/{clsId}")
    public Object edit(@PathVariable(name = "clsId") Integer clsId) {

        BaseClass entity = new BaseClass();
        entity.setClsId(clsId);
        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        entity = baseClassService.getEntity(entity);
        if (null == entity) {
            return ResultUtils.error(CodeType.CODE_TYPE_102);
        }
        return ResultUtils.success(entity);
    }

    @ApiOperation(value = "删除")
    @PostMapping(value = "/delete")
    public Object delete(Integer clsId, String pks[], Integer userId) {
        if (null == clsId && ArrayUtils.isEmpty(pks)) {
            return ResultUtils.error(CodeType.CODE_TYPE_101);
        }
        BaseClass entity = new BaseClass();
        entity.setIsDel(YesOrNo.YES_OR_NO_1.getIndex());
        entity.setDelDate(new Date());
        entity.setDelUserId(userId);
        if (null != clsId) {
            entity.setClsId(clsId);
        } else {
            entity.getMap().put("pks", pks);
        }
        baseClassService.updateEntity(entity);
        return ResultUtils.success();
    }

}