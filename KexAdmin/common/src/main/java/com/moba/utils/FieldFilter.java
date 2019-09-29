package com.moba.utils;

import com.moba.domain.BaseEntity;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 属性过滤器，用于动态创建Specification
 *
 * @version 2013年12月12日 下午9:26:15
 */
public class FieldFilter {
    public static final String PREFIX = "f_";

    // public static Map<String, FieldFilter> parse(Map<String, Object> fieldParams) {
    //
    // Map<String, FieldFilter> filters = Maps.newHashMap();
    // for (Entry<String, Object> entry : fieldParams.entrySet()) {
    // String key = entry.getKey();
    // Object fieldValue = entry.getValue();
    //
    // if (null == fieldValue || (fieldValue instanceof String && StringUtils.isBlank((String) fieldValue))) {
    // continue;
    // }
    //
    // // 拆分operator_clazz_fieldName
    // String[] names = StringUtils.splitByWholeSeparator(key, SEPARATOR, 3);// 防止fieldName中有"_"
    // if (names.length != 3) {
    // throw new IllegalArgumentException(key + " 不是一个合法的字段名称");
    // }
    // OperatorType operator = OperatorType.valueOf(names[0].toUpperCase());
    // FieldType filedType = FieldType.valueOf(names[1].toUpperCase());
    // String fieldName = names[2];
    //
    // FieldFilter filter = new FieldFilter(operator, filedType, fieldName, fieldValue);
    // filters.put(key, filter);
    // }
    // return filters;
    // }

    public static void setQuery(Map<String, Object> fieldParams, BaseEntity baseEntity) {

        for (Entry<String, Object> entry : fieldParams.entrySet()) {
            String key = entry.getKey();
            Object fieldValue = entry.getValue();
            baseEntity.getMap().put(key, fieldValue);
        }
    }

    public static String encodeParamsToQueryString(Map<String, Object> map, String prefix) {
        if (null == map || map.size() == 0) {
            return null;
        }
        if (null == prefix) {
            prefix = "";
        }

        StringBuilder builder = new StringBuilder();
        Iterator<Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            builder.append(prefix).append(entry.getKey()).append('=').append(entry.getValue());
            if (it.hasNext()) {
                builder.append('&');
            }
        }
        return builder.toString();
    }

}
