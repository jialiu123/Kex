package com.moba.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moba.domain.BaseClass;
import com.moba.emums.IsDel;
import com.moba.emums.YesOrNo;
import com.moba.mapper.BaseClassMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author ding, ning
 * @version 2019-03-01 14:57
 * @date 2019-03-01 14:57
 */
@Service
@Transactional
public class BaseClassService {

    @Resource
    private BaseClassMapper baseClassMapper;

    public BaseClass getEntity(BaseClass t) {
        return this.baseClassMapper.selectEntity(t);
    }

    public Integer getEntityCount(BaseClass t) {
        return this.baseClassMapper.selectEntityCount(t);
    }

    public List<BaseClass> getEntityList(BaseClass t) {
        return this.baseClassMapper.selectEntityList(t);
    }

    public Integer createEntity(BaseClass t) {
        BaseClass baseClass = new BaseClass();
        baseClass.setClsId(t.getParId());
        baseClass = this.baseClassMapper.selectEntity(baseClass);
        t.setRootId(baseClass.getRootId());
        return this.baseClassMapper.insertEntity(t);
    }

    public int updateEntity(BaseClass t) {
        return this.baseClassMapper.updateEntity(t);
    }

    public int removeEntity(BaseClass t) {
        return this.baseClassMapper.deleteEntity(t);
    }

    public List<BaseClass> getEntityPaginatedList(BaseClass t) {
        return this.baseClassMapper.selectEntityPaginatedList(t);
    }

    /**
     * @author liu, jia
     * @desc 取父节点list
     */
    public List<BaseClass> proGetSysModelParentList(BaseClass t) {
        // 子查父
        List<BaseClass> parentList = new ArrayList<BaseClass>();
        List<BaseClass> proGetSysModelParentList = this.getParentList(t.getClsId(), parentList);
        return proGetSysModelParentList;
    }

    /**
     * @author liu, jia
     * @desc 取子节点list
     */
    public List<BaseClass> proGetBaseClassSonList(BaseClass t) {
        // 父查子
        List<BaseClass> sonList = new ArrayList<BaseClass>();
        List<BaseClass> proGetBaseClassSonList = this.getSonList(true, t.getClsId(), sonList, t.getClsScope());
        return proGetBaseClassSonList;
    }


    public List<BaseClass> getParentList(Integer parId, List<BaseClass> parentList) {

        BaseClass BaseClass = new BaseClass();
        BaseClass.setClsId(parId);
        BaseClass.setIsDel(0);
        BaseClass = this.baseClassMapper.selectEntity(BaseClass);
        if (null != BaseClass) {
            parentList.add(0, BaseClass);
            this.getParentList(BaseClass.getParId(), parentList);
        }
        return parentList;
    }

    public List<BaseClass> getSonList(Boolean is_first, Integer parId, List<BaseClass> sonList, Integer clsScope) {

        if (is_first) {
            BaseClass temp = new BaseClass();
            temp.setClsId(parId);
            temp.setIsDel(0);
            temp = this.baseClassMapper.selectEntity(temp);
            if (null != temp) {
                sonList.add(temp);
            }
        }

        BaseClass BaseClass = new BaseClass();
        BaseClass.setParId(parId);
        BaseClass.setIsDel(IsDel.IS_DEL_0.getIndex());
        BaseClass.setClsScope(clsScope);
        List<BaseClass> BaseClassList = this.baseClassMapper.selectEntityList(BaseClass);
        if (null != BaseClassList && BaseClassList.size() > 0) {
            for (BaseClass temp : BaseClassList) {
                sonList.add(temp);
                this.getSonList(false, temp.getClsId(), sonList, temp.getClsScope());
            }
        }
        return sonList;
    }


    public JSONObject getSonListForTree(Integer parId, JSONObject jsonObjectRoot, Integer clsScope) {

        BaseClass BaseClass = new BaseClass();
        BaseClass.setParId(parId);
        BaseClass.setIsDel(IsDel.IS_DEL_0.getIndex());
        BaseClass.setClsScope(clsScope);
        List<BaseClass> BaseClassList = this.baseClassMapper.selectEntityList(BaseClass);
        JSONArray seriesListSon = new JSONArray();
        if (null != BaseClassList && BaseClassList.size() > 0) {
            for (BaseClass temp : BaseClassList) {
                JSONObject jsonObject = new JSONObject();
                temp.setParName(getBaseClass(temp.getParId()).getClsName());
                jsonObject.put("entity", temp);
                jsonObject.put("title", temp.getClsName());
                jsonObject.put("expand", true);
                this.getSonListForTree(temp.getClsId(), jsonObject, temp.getClsScope());
                seriesListSon.add(jsonObject);
            }
            jsonObjectRoot.put("children", seriesListSon);
        }
        return jsonObjectRoot;
    }

    /**
     * 通过基础数据类型，查找对应的基础数据类型的集合
     */
    public BaseClass getBaseClass(Integer clsId) {
        BaseClass baseClass = new BaseClass();
        baseClass.setClsId(clsId);
        baseClass.setIsDel(YesOrNo.YES_OR_NO_0.getIndex());
        return this.baseClassMapper.selectEntity(baseClass);
    }


}
