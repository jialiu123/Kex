package com.moba.controller;

import com.alibaba.fastjson.JSONObject;
import com.moba.domain.OrderInfo;
import com.moba.emums.CodeType;
import com.moba.emums.orderInfo.OrderState;
import com.moba.emums.orderInfo.OrderType;
import com.moba.emums.orderInfo.PayType;
import com.moba.service.OrderInfoService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author li, bai, qiang
 * @version 2019-03-04 14:10
 * @date 2019-03-04 14:10
 */

@RestController
@RequestMapping("/orderInfo")
@Api(value = "")
public class OrderInfoController extends BaseController {

    @Resource
    OrderInfoService orderInfoService;

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/list")
    public Object list(HttpServletRequest request,
                       @ModelAttribute OrderInfo entity,
                       Integer page) {

        if (null == page) {
            page = 1;
        }

        JSONObject jsonObject = new JSONObject();
        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, FieldFilter.PREFIX);
        FieldFilter.setQuery(paramMap, entity);

        jsonObject.put("dataCount", orderInfoService.getEntityCount(entity));
        entity.getRow().setCount(10);
        entity.getRow().setFirst((page - 1) * (entity.getRow().getCount()));
        List<OrderInfo> entityList = orderInfoService.getEntityPaginatedList(entity);
        jsonObject.put("entityList", entityList);

        jsonObject.put("orderStateList", super.parseEnmuToJSONArray(OrderState.class, OrderState.values()));
        jsonObject.put("orderTypeList", super.parseEnmuToJSONArray(OrderType.class, OrderType.values()));
        jsonObject.put("payTypeList", super.parseEnmuToJSONArray(PayType.class, PayType.values()));

        return ResultUtils.success(jsonObject);
    }

    @ApiOperation(value = "订单详情列表")
    @GetMapping(value = "/getOrderDetail")
    public Object getOrderDetail(Integer id) {

        OrderInfo entity = new OrderInfo();
        JSONObject jsonObject = new JSONObject();
        entity.setId(id);
        entity = orderInfoService.getEntity(entity);
        jsonObject.put("entity", entity);
        jsonObject.put("orderStateList", super.parseEnmuToJSONArray(OrderState.class, OrderState.values()));
        jsonObject.put("orderTypeList", super.parseEnmuToJSONArray(OrderType.class, OrderType.values()));
        jsonObject.put("payTypeList", super.parseEnmuToJSONArray(PayType.class, PayType.values()));
        if (null == entity) {
            return ResultUtils.error(CodeType.CODE_TYPE_102);
        }
        return ResultUtils.success(jsonObject);
    }


    @ApiOperation(value = "订单状态改变")
    @PostMapping(value = "/updateOrder")
    public Object updateOrder(@RequestBody OrderInfo entity) {

        if (null == entity.getId()) {
            return ResultUtils.error(CodeType.CODE_TYPE_102);
        }

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(entity.getId());
        orderInfo = orderInfoService.getEntity(orderInfo);
        if (null == orderInfo) {
            return ResultUtils.error(CodeType.CODE_TYPE_101);
        }

        entity.setUpdateDate(new Date());
        //当点取消状态时，前一个状态应为预定状态
        if (entity.getOrderState().intValue() == OrderState.ORDER_STATE_X10.getIndex()) {
            entity.getMap().put("orderStateFrontState", OrderState.ORDER_STATE_0.getIndex());
        }
        //当商家确认订单后进入发货状态，前一个状态应为等待发货状态
        if (entity.getOrderState().intValue() == OrderState.ORDER_STATE_20.getIndex()) {
            entity.setFahuoDate(new Date());
            entity.getMap().put("orderStateFrontState", OrderState.ORDER_STATE_10.getIndex());
        }
        //当买家确认商品后进入确认状态，前一个状态应为已支付状态
        if (entity.getOrderState().intValue() == OrderState.ORDER_STATE_40.getIndex()) {
            entity.setQrshDate(new Date());
            entity.getMap().put("orderStateFrontState", OrderState.ORDER_STATE_20.getIndex());
        }
        int count = orderInfoService.updateEntity(entity);
        if (count <= 0) {
            return ResultUtils.error(CodeType.CODE_TYPE_100);
        }
        return ResultUtils.success();
    }


    @ApiOperation(value = "保存")
    @PostMapping(value = "/save")
    public Object save(@RequestBody @Valid OrderInfo entity, BindingResult result) {

        if (null == entity.getId()) {
            entity.setAddDate(new Date());
            orderInfoService.createEntity(entity);
        } else {
            entity.setUpdateDate(new Date());
            entity.getMap().put("update_newsInfo", true);
            orderInfoService.updateEntity(entity);
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "查询单条记录")
    @GetMapping(value = "/{id}")
    public Object edit(@PathVariable(name = "id") Integer id) {

        OrderInfo entity = new OrderInfo();
        entity.setId(id);
        entity = orderInfoService.getEntity(entity);
        if (null == entity) {
            return ResultUtils.error(CodeType.CODE_TYPE_102);
        }
        return ResultUtils.success(entity);
    }

}