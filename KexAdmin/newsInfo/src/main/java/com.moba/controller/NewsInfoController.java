package com.moba.controller;

import com.alibaba.fastjson.JSONObject;
import com.moba.domain.NewsInfo;
import com.moba.emums.CodeType;
import com.moba.emums.LanguageType;
import com.moba.emums.YesOrNo;
import com.moba.service.NewsInfoService;
import com.moba.utils.FieldFilter;
import com.moba.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.util.UUID;

/**
 * @author li, bai, qiang
 * @version 2019-02-26 14:53
 * @date 2019-02-26 14:53
 */
@RestController
@RequestMapping("/newsInfo")
@Api(value = "")
public class NewsInfoController extends BaseController {

    @Resource
    NewsInfoService newsInfoService;


    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/list")
    public Object list(
            HttpServletRequest request,
            @ModelAttribute NewsInfo entity,
            @RequestParam(name = "page") Integer page) {

        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, FieldFilter.PREFIX);
        FieldFilter.setQuery(paramMap, entity);
        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        entity.setLocaleName(entity.getLocaleName());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dataCount", newsInfoService.getEntityCount(entity));

        jsonObject.put("languageTypeList", super.parseEnmuToJSONArray(LanguageType.class, LanguageType.values()));

        entity.getRow().setCount(10);
        entity.getRow().setFirst((page - 1) * (entity.getRow().getCount()));
        List<NewsInfo> entityList = newsInfoService.getEntityPaginatedList(entity);
        jsonObject.put("entityList", entityList);
        return ResultUtils.success(jsonObject);
    }

    @ApiOperation(value = "获取单条新闻")
    @GetMapping(value = "/getSingleNewsByModId")
    public Object getSingleNewsByModId(@RequestParam(name = "modId") String modId) {

        if (StringUtils.isBlank(modId)) {
            return ResultUtils.error(CodeType.CODE_TYPE_101);
        }
        NewsInfo entity = new NewsInfo();
        entity.setModId(modId);
        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        entity = newsInfoService.getEntity(entity);
        if (null == entity) {
            return ResultUtils.error(CodeType.CODE_TYPE_102);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("entity", entity);
        jsonObject.put("languageTypeList", super.parseEnmuToJSONArray(LanguageType.class, LanguageType.values()));
        return ResultUtils.success(jsonObject);
    }


    @ApiOperation(value = "保存")
    @PostMapping(value = "/save")
    public Object save(@RequestBody @Valid NewsInfo entity, BindingResult result) {


        if (null == entity.getId()) {
            entity.setUuid(UUID.randomUUID().toString());
            entity.setAddDate(new Date());
            newsInfoService.createEntity(entity);
        } else {
            entity.setUpdateDate(new Date());
            entity.getMap().put("update_newsInfo", true);
            newsInfoService.updateEntity(entity);
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "查询单条记录")
    @GetMapping(value = "/{id}")
    public Object edit(@PathVariable("id") String id) {
        if (StringUtils.isBlank(id)) {
            return ResultUtils.error(CodeType.CODE_TYPE_101);
        }
        NewsInfo entity = new NewsInfo();
        entity.setId(Integer.valueOf(id));
        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        entity = newsInfoService.getEntity(entity);
        return ResultUtils.success(entity);
    }

    @ApiOperation(value = "根据id删除")
    @PostMapping(value = "/delete")
    public Object delete(Integer id, Integer delUserId, String[] pks) {

        if (null == id && (null == pks || pks.length == 0)) {
            return ResultUtils.error(CodeType.CODE_TYPE_101);
        }
        NewsInfo entity = new NewsInfo();
        entity.setIsDel(YesOrNo.YES_OR_NO_1.getIndex());
        entity.setDelDate(new Date());
        entity.setDelUserId(delUserId);
        if (null != id) {
            entity.setId(id);
        } else {
            entity.getMap().put("pks", pks);
        }
        newsInfoService.updateEntity(entity);
        return ResultUtils.success();

    }


}