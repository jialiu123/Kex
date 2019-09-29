package com.moba.controller;

import com.moba.emums.CodeType;
import com.moba.utils.ResultUtils;
import com.moba.utils.code.Generator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

/**
 * 类名称： 反向生成
 * @author liujia
 *
 */
@RestController
@RequestMapping("getCode")
public class GetCodeController {
    /**
     * @return
     * @throws Exception
     */
    @GetMapping(value = "")
    public Object getCode(
            @PathParam("tableName") String tableName, HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        String[] tableNames = tableName.split(",");

        try {
            Generator.run(request, response, tableNames);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.error(CodeType.CODE_TYPE_100);
        }

        return ResultUtils.success();
    }
}
