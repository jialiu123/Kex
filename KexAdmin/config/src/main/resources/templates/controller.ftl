package ${packagePrefix}.controller;

import com.alibaba.fastjson.JSONObject;
import java.util.*;
import com.moba.utils.ResultUtils;
import com.moba.emums.CodeType;
import com.moba.emums.IsDel;
import com.moba.emums.YesOrNo;
import com.moba.utils.FieldFilter;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.validation.BindingResult;


import com.moba.service.${table.javaName}Service;
import com.moba.domain.${table.javaName};
import com.moba.controller.BaseController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author liu, jia
* @version ${generatorTime}
* @date ${generatorTime}
*/
@RestController
@RequestMapping("/${table.variableName}")
@Api(value = "")
public class ${table.javaName}Controller extends BaseController {

@Resource
${table.javaName}Service ${table.variableName}Service;

@ApiOperation(value = "获取列表")
@GetMapping(value = "/list")
public Object list(HttpServletRequest request, @ModelAttribute ${table.javaName} entity,Integer page) {

if (null == page) {
page = 1;
}

JSONObject jsonObject = new JSONObject();

Map
<String, Object> paramMap = WebUtils.getParametersStartingWith(request, FieldFilter.PREFIX);
FieldFilter.setQuery(paramMap, entity);

entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());

jsonObject.put("dataCount",  ${table.variableName}Service.getEntityCount(entity));

entity.getRow().setCount(10);
entity.getRow().setFirst((page - 1) * (entity.getRow().getCount()));
List<${table.javaName}> entityList = ${table.variableName}Service.getEntityPaginatedList(entity);
jsonObject.put("entityList", entityList);
return ResultUtils.success(jsonObject);
}

@ApiOperation(value = "保存")
@PostMapping(value = "/save")
public Object save(@RequestBody @Valid ${table.javaName} entity, BindingResult result) {

if (null == entity.getId()) {
entity.setAddDate(new Date());
${table.variableName}Service.createEntity(entity);
} else {
entity.setUpdateDate(new Date());
${table.variableName}Service.updateEntity(entity);
}
return ResultUtils.success();
}

@ApiOperation(value = "查询单条记录")
@GetMapping(value = "/{id}")
public Object edit(@PathVariable(name = "id") Integer id) {

${table.javaName} entity = new ${table.javaName}();
entity.setId(id);
entity.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
entity = ${table.variableName}Service.getEntity(entity);
if(null == entity){
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
${table.javaName} entity = new ${table.javaName}();
entity.setIsDel(YesOrNo.YES_OR_NO_1.getIndex());
entity.setDelDate(new Date());
entity.setDelUserId(userId);
if (null != id) {
entity.setId(id);
} else {
entity.getMap().put("pks", pks);
}
${table.variableName}Service.updateEntity(entity);
return ResultUtils.success();
}

}