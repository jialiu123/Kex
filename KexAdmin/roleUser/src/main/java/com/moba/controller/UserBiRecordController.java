package com.moba.controller;

import com.alibaba.fastjson.JSONObject;
import com.moba.domain.UserBiRecord;
import com.moba.emums.BiGetType;
import com.moba.emums.BiType;
import com.moba.emums.YesOrNo;
import com.moba.service.UserBiRecordService;
import com.moba.utils.FieldFilter;
import com.moba.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author ding, ning
 * @version 2019-03-06 11:07
 * @date 2019-03-06 11:07
 */
@RestController
@RequestMapping("/userBiRecord")
@Api(value = "")
public class UserBiRecordController extends BaseController {

    @Resource
    UserBiRecordService userBiRecordService;

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/list")
    public Object list(HttpServletRequest request, @ModelAttribute UserBiRecord entity, Integer page) {

        if (null == page) {
            page = 1;
        }

        JSONObject jsonObject = new JSONObject();

        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, FieldFilter.PREFIX);
        FieldFilter.setQuery(paramMap, entity);

        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        entity.getRow().setCount(10);
        entity.getRow().setFirst((page - 1) * (entity.getRow().getCount()));
        List<UserBiRecord> entityList = userBiRecordService.getEntityPaginatedList(entity);
        jsonObject.put("biTypeList", super.parseEnmuToJSONArray(BiType.class, BiType.values()));
        jsonObject.put("biGetTypeList", super.parseEnmuToJSONArray(BiGetType.class, BiGetType.values()));
        jsonObject.put("dataCount", userBiRecordService.getEntityCount(entity));
        jsonObject.put("entityList", entityList);
        return ResultUtils.success(jsonObject);
    }


}