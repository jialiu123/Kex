package com.moba.utils;

import com.moba.domain.Result;
import com.moba.emums.CodeType;

public class ResultUtils {

    public static Result<Object> success(Object object, Result<Object> result) {
        result.setData(object);
        return result;
    }

    public static Result<Object> success(Object object) {
        Result<Object> result = new Result<Object>();
        result.setCode(CodeType.CODE_TYPE_200.getIndex());
        result.setMsg(CodeType.CODE_TYPE_200.getName());
        result.setData(object);
        return result;
    }

    public static Result<Object> success() {
        return success(null);
    }

    public static Result<Object> error(Integer code, String msg) {
        Result<Object> result = new Result<Object>();
        result.setCode(code);
        result.setMsg(msg);
        System.out.println("code:" + code + ",msg:" + msg);
        return result;
    }

    public static Result<Object> error(CodeType codeType) {
        Result<Object> result = new Result<Object>();
        result.setCode(codeType.getIndex());
        result.setMsg(codeType.getName());
        System.out.println("code:" + codeType.getIndex() + ",msg:" + codeType.getName());
        return result;
    }
}
