package com.moba.controller;

import com.alibaba.fastjson.JSONObject;
import com.moba.domain.Msg;
import com.moba.emums.CodeType;
import com.moba.emums.InfoState;
import com.moba.emums.MsgType;
import com.moba.emums.YesOrNo;
import com.moba.service.MsgService;
import com.moba.utils.FieldFilter;
import com.moba.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author li, bai,qiang
 * @version 2019-03-07 16:15
 * @date 2019-03-07 16:15
 */
@RestController
@RequestMapping("/msg")
@Api(value = "")
public class MsgController extends BaseController {

    @Resource
    MsgService msgService;

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/list")
    public Object list(HttpServletRequest request, @ModelAttribute Msg entity, Integer page) {

        if (null == page) {
            page = 1;
        }

        JSONObject jsonObject = new JSONObject();

        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, FieldFilter.PREFIX);
        FieldFilter.setQuery(paramMap, entity);

        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());

        jsonObject.put("dataCount", msgService.getEntityCount(entity));

        entity.getRow().setCount(10);
        entity.getRow().setFirst((page - 1) * (entity.getRow().getCount()));
        List<Msg> entityList = msgService.getEntityPaginatedList(entity);
        jsonObject.put("entityList", entityList);
        jsonObject.put("infoStateList", super.parseEnmuToJSONArray(InfoState.class, InfoState.values()));
        jsonObject.put("msgTypeList", super.parseEnmuToJSONArray(MsgType.class, MsgType.values()));
        return ResultUtils.success(jsonObject);
    }

    @ApiOperation(value = "获取各个状态的信息条数")
    @GetMapping(value = "/getCount")
    public Object getCount(@RequestParam(name = "userId") Integer userId) {

        Msg entity = new Msg();
        entity.setUserId(userId);
        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        entity.setInfoState(InfoState.INFO_STATE_1.getIndex());
        int unReadCount = msgService.getEntityCount(entity);

        entity.setInfoState(InfoState.INFO_STATE_2.getIndex());
        int readCount = msgService.getEntityCount(entity);

        entity.setInfoState(InfoState.INFO_STATE_3.getIndex());
        int recycleCount = msgService.getEntityCount(entity);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("unReadCount", unReadCount);
        jsonObject.put("readCount", readCount);
        jsonObject.put("recycleCount", recycleCount);

        return ResultUtils.success(jsonObject);
    }

    /**
     * msgService.send(entity);用来测试发送信息
     */
    @ApiOperation(value = "保存")
    @PostMapping(value = "/save")
    public Object save(@RequestBody Msg entity) {

        if (null == entity.getId()) {

            msgService.send(entity);
        } else {
            Msg msg = new Msg();
            msg.setId(entity.getId());
            msg.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
            int count = msgService.getEntityCount(msg);
            if (count == 0) {
                return ResultUtils.error(CodeType.CODE_TYPE_102);
            }
            entity.setUpdateDate(new Date());
            msgService.updateEntity(entity);
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "查询单条记录")
    @GetMapping(value = "/{id}")
    public Object edit(@PathVariable(name = "id") Integer id) {

        Msg entity = new Msg();
        entity.setId(id);
        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        entity = msgService.getEntity(entity);
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
        Msg entity = new Msg();
        entity.setIsDel(YesOrNo.YES_OR_NO_1.getIndex());
        entity.setDelDate(new Date());
        entity.setDelUserId(userId);
        if (null != id) {
            entity.setId(id);
        } else {
            entity.getMap().put("pks", pks);
        }
        msgService.updateEntity(entity);
        return ResultUtils.success();
    }

}