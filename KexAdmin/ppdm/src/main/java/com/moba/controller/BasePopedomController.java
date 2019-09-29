package com.moba.controller;

import com.moba.domain.BasePopedom;
import com.moba.service.BasePopedomService;
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
@RequestMapping("/basePopedom")
@Api(value = "")
public class BasePopedomController extends BaseController {

    @Resource
    BasePopedomService basePopedomService;

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/list")
    public Object list(
            HttpServletRequest request,
            @ModelAttribute BasePopedom entity,
            @RequestParam(name = "page") Integer page) {

        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, FieldFilter.PREFIX);
        FieldFilter.setQuery(paramMap, entity);

        entity.getRow().setCount(10);
        entity.getRow().setFirst((page - 1) * (entity.getRow().getCount()));
        List<BasePopedom> entityList = basePopedomService.getEntityPaginatedList(entity);
        return ResultUtils.success(entityList);
    }

    @ApiOperation(value = "保存")
    @PostMapping(value = "/save")
    public Object save(@RequestBody BasePopedom entity) {

        if (null == entity.getId()) {
            basePopedomService.createEntity(entity);
        } else {
            basePopedomService.updateEntity(entity);
        }
        return ResultUtils.success();
    }
}
