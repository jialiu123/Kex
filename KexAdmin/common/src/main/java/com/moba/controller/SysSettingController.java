package com.moba.controller;

import com.alibaba.fastjson.JSONObject;
import com.moba.domain.SysSetting;
import com.moba.service.SysSettingService;
import com.moba.utils.FieldFilter;
import com.moba.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author ding, ning
 * @version 2019-03-11 09:19
 * @date 2019-03-11 09:19
 */
@RestController
@RequestMapping("/sysSetting")
@Api(value = "")
public class SysSettingController extends BaseController {

    @Resource
    SysSettingService sysSettingService;

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/list")
    public Object list(HttpServletRequest request, @ModelAttribute SysSetting entity, Integer page) {

        if (null == page) {
            page = 1;
        }

        JSONObject jsonObject = new JSONObject();

        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, FieldFilter.PREFIX);
        FieldFilter.setQuery(paramMap, entity);


        jsonObject.put("dataCount", sysSettingService.getEntityCount(entity));

        entity.getRow().setCount(10);
        entity.getRow().setFirst((page - 1) * (entity.getRow().getCount()));
        List<SysSetting> entityList = sysSettingService.getEntityPaginatedList(entity);
        jsonObject.put("entityList", entityList);
        return ResultUtils.success(jsonObject);
    }

    @ApiOperation(value = "保存")
    @PostMapping(value = "/save")
    public Object save(@RequestBody @Valid SysSetting entity, BindingResult result) {

        sysSettingService.updateEntity(entity);
        return ResultUtils.success();
    }


}