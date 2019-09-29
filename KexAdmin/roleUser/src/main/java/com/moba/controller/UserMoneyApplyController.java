package com.moba.controller;

import com.alibaba.fastjson.JSONObject;
import com.moba.domain.UserMoneyApply;
import com.moba.emums.AuditState;
import com.moba.emums.BiType;
import com.moba.emums.CodeType;
import com.moba.emums.YesOrNo;
import com.moba.emums.userMoneyApply.CashType;
import com.moba.service.UserMoneyApplyService;
import com.moba.utils.FieldFilter;
import com.moba.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author li, bai, qiang
 * @version 2019-03-06 11:20
 * @date 2019-03-06 11:20
 */
@RestController
@RequestMapping("/userMoneyApply")
@Api(value = "")
public class UserMoneyApplyController extends BaseUserController {

    @Resource
    UserMoneyApplyService userMoneyApplyService;

    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/list")
    public Object list(HttpServletRequest request, @ModelAttribute UserMoneyApply entity, Integer page) {

        if (null == page) {
            page = 1;
        }

        JSONObject jsonObject = new JSONObject();

        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, FieldFilter.PREFIX);
        FieldFilter.setQuery(paramMap, entity);
        entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        jsonObject.put("dataCount", userMoneyApplyService.getEntityCount(entity));

        entity.getRow().setCount(10);
        entity.getRow().setFirst((page - 1) * (entity.getRow().getCount()));
        List<UserMoneyApply> entityList = userMoneyApplyService.getEntityPaginatedList(entity);
        jsonObject.put("entityList", entityList);
        jsonObject.put("auditStateList", super.parseEnmuToJSONArray(AuditState.class, AuditState.values()));
        jsonObject.put("applyType", super.parseEnmuToJSONArray(CashType.class, CashType.values()));
        return ResultUtils.success(jsonObject);
    }

    @ApiOperation(value = "提现")
    @GetMapping(value = "/add")
    public Object add(@RequestParam(name = "userId") Integer userId) {

        if (null == super.getMoneyApply(userId)) {
            return ResultUtils.error(CodeType.CODE_TYPE_102);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cashRate", super.getMoneyApply(userId).get("rate"));
        jsonObject.put("cashCountBefore", super.getMoneyApply(userId).get("biDianzi"));
        return ResultUtils.success(jsonObject);
    }

    @ApiOperation(value = "审核")
    @PostMapping(value = "/audit")
    public Object audit(@RequestBody UserMoneyApply entity) {

        UserMoneyApply userMoneyApply = new UserMoneyApply();
        userMoneyApply.setId(entity.getId());
        userMoneyApply = userMoneyApplyService.getEntity(userMoneyApply);
        if (userMoneyApply == null) {
            return ResultUtils.error(CodeType.CODE_TYPE_102);
        }

        entity.setAuditDate(new Date());
        int count = userMoneyApplyService.updateEntity(entity);
        if (count == 0) {
            return ResultUtils.error(CodeType.CODE_TYPE_102);
        }
        return ResultUtils.success();
    }

    /**
     * @author li, bai, qiang
     * @date 2019-03-07 9:47
     * 提现页面点击“提现”传入UserMoneyApply对象调用此接口，来保存此条提现记录
     */

    @ApiOperation(value = "保存")
    @PostMapping(value = "/save")
    public Object save(@RequestBody UserMoneyApply entity) {

        BigDecimal biDianzi = super.getMoneyApply(entity.getUserId()).get("biDianzi");
        entity.setCashCountBefore(biDianzi);

        if (biDianzi.compareTo(entity.getCashCount()) == -1) {
            return ResultUtils.error(CodeType.CODE_TYPE_101.getIndex(), "金额不足");
        }
        if (entity.getCashCount().compareTo(new BigDecimal(0)) == 0) {
            return ResultUtils.error(CodeType.CODE_TYPE_101.getIndex(), "金额不能为0");
        }
        if (null != entity.getId()) {
            return ResultUtils.error(CodeType.CODE_TYPE_101);
        }
        BigDecimal rate = super.getMoneyApply(entity.getUserId()).get("rate");
        BigDecimal cashRate = entity.getCashCount().divide(rate).setScale(2, BigDecimal.ROUND_DOWN);

        entity.setCashRate(cashRate);
        entity.setCashPay(entity.getCashCount().subtract(cashRate));
        entity.setCashCountAfter(entity.getCashCountBefore().subtract(entity.getCashCount()));
        entity.setAddDate(new Date());
        entity.setInfoState(AuditState.AUDIT_STATE_0.getIndex());
        entity.getMap().put("biOutAndDoThing", true);
        entity.getMap().put("biType", BiType.BI_TYPE_100.getIndex());
        entity.setCashType(CashType.AUDIT_STATE_10.getIndex());
        int count = userMoneyApplyService.createEntity(entity);
        if (count == 0) {
            return ResultUtils.error(CodeType.CODE_TYPE_101);
        }
        return ResultUtils.success();
    }

}