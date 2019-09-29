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
@RequestMapping("/emums")
@Api(value = "")
public class EmumsController extends BaseController {

    @Resource
    BaseDataService baseDataService;

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/getLanguageTypeList")
    public Object getLanguageTypeList() {
        return ResultUtils.success(super.parseEnmuToJSONArray(LanguageType.class, LanguageType.values()));
    }

}