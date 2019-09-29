package com.moba.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.moba.domain.BaseClass;
import com.moba.domain.BaseData;
import com.moba.domain.TGameInfo;
import com.moba.emums.IsDel;
import com.moba.emums.YesOrNo;
import com.moba.mapper.TGameInfoMapper;
import com.moba.service.BaseClassService;
import com.moba.service.BaseDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author liujia
 * @desc 基础的类
 */
@Component
public class BaseController {

    @Resource
    BaseDataService baseDataService;

    @Resource
    BaseClassService baseClassService;

    @Resource
    TGameInfoMapper tGameInfoMapper;


    public static DecimalFormat dfFormat = new DecimalFormat("0.00");

    public static DecimalFormat dfFormat0 = new DecimalFormat("0");

    public static SimpleDateFormat sdFormatYm = new SimpleDateFormat("yyyy-MM");

    public static SimpleDateFormat sdFormatYmd = new SimpleDateFormat("yyyy-MM-dd");

    public static SimpleDateFormat sdFormatYmdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    public String getCtxPath(HttpServletRequest request) {
        return this.getCtxPath(request, true);
    }

    public String getCtxPath(HttpServletRequest request, boolean is_add_port) {

        StringBuffer ctx = new StringBuffer();
        ctx.append(request.getScheme()).append("://").append(request.getServerName());
        if (is_add_port) {
            ctx.append(":").append(request.getServerPort());
        }
        ctx.append(request.getContextPath());

        return ctx.toString();
    }

    /**
     * 枚举转换成为json
     */
    public JSONArray parseEnmuToJSONArray(Class<? extends Enum> enumClass, Enum<?>[] obj) {
        SerializeConfig config = new SerializeConfig();
        config.configEnumAsJavaBean(enumClass);
        return JSONArray.parseArray(JSON.toJSONString(obj, config, SerializerFeature.WriteMapNullValue));
    }

    /**
     * 枚举list转换成为json
     */
    public JSONArray parseEnmuListToJSONArray(Class enumClass, List<?> obj) {
        SerializeConfig config = new SerializeConfig();
        config.configEnumAsJavaBean(enumClass);
        return JSONArray.parseArray(JSON.toJSONString(obj, config, SerializerFeature.WriteMapNullValue));
    }

    /**
     * 通过基础数据类型，查找对应的基础数据类型的集合
     */
    public List<BaseData> getBaseDataList(Integer type) {
        BaseData baseData = new BaseData();
        baseData.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        baseData.setType(type);
        return this.baseDataService.getEntityList(baseData);
    }

    /**
     * 通过基础数据类型，查找对应的基础数据
     */
    public BaseData getBaseData(Integer type) {
        BaseData baseData = new BaseData();
        baseData.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        baseData.setType(type);
        return this.baseDataService.getEntity(baseData);
    }

    /**
     * 通过基础类别，查找对应的基础类别数据集合
     */
    public List<BaseClass> getBaseClassesList(Integer clsScope) {
        BaseClass baseClass = new BaseClass();
        baseClass.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        baseClass.setClsScope(clsScope);
        List<BaseClass> list = this.baseClassService.getEntityList(baseClass);
        return list;
    }

    public JSONArray getBaseClassesListChoose(Integer clsScope) {
        BaseClass baseClass = new BaseClass();
        baseClass.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        baseClass.setClsScope(clsScope);
        List<BaseClass> list = this.baseClassService.getEntityList(baseClass);
        JSONArray oneList = new JSONArray();
        JSONObject obj = null;
        for (BaseClass cur : list) {
            if (cur.getClsLevel().intValue() == 1) {
                obj = new JSONObject();
                obj.put("label", cur.getClsName());
                obj.put("value", cur.getClsId());
                oneList.add(obj);
            }
        }
        for (Object item : oneList) {
            JSONObject itemObj = (JSONObject) item;
            System.out.println(itemObj.get("label"));
            JSONArray childrenList = new JSONArray();

            for (BaseClass cur : list) {
                if (cur.getParId().intValue() == (Integer) itemObj.get("value")) {
                    JSONObject children = new JSONObject();
                    children.put("label", cur.getClsName());
                    children.put("value", cur.getClsId());
                    childrenList.add(children);
                }
            }
            itemObj.put("children", childrenList);
        }
        return oneList;
    }

    /**
     * 通过基础数据类型，查找对应的基础数据类型的集合
     */
    public BaseClass getBaseClass(Integer clsId) {
        BaseClass baseClass = new BaseClass();
        baseClass.setClsId(clsId);
        baseClass.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        return this.baseClassService.getEntity(baseClass);
    }

    /**
     * 获取游戏列表
     */
    public List<TGameInfo> getTGameInfoList() {
        TGameInfo tGameInfo = new TGameInfo();
        tGameInfo.setIsDel(IsDel.IS_DEL_0.getIndex());
        return tGameInfoMapper.selectEntityList(tGameInfo);
    }

}
