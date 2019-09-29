package com.moba.service;

import com.moba.domain.NewsContent;
import com.moba.mapper.NewsContentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author li, bai,qiang
 * @version 2019-02-26 16:55
 * @date 2019-02-26 16:55
 */
@Service
@Transactional
public class NewsContentService {

    @Resource
    private NewsContentMapper newsContentMapper;

    public NewsContent getEntity(NewsContent t) {
        return this.newsContentMapper.selectEntity(t);
    }

    public Integer getEntityCount(NewsContent t) {
        return this.newsContentMapper.selectEntityCount(t);
    }

    public List<NewsContent> getEntityList(NewsContent t) {
        return this.newsContentMapper.selectEntityList(t);
    }

    public Integer createEntity(NewsContent t) {
        this.newsContentMapper.insertEntity(t);
        return t.getId();
    }

    public int updateEntity(NewsContent t) {
        return this.newsContentMapper.updateEntity(t);
    }

    public int removeEntity(NewsContent t) {
        return this.newsContentMapper.deleteEntity(t);
    }

    public List<NewsContent> getEntityPaginatedList(NewsContent t) {
        return this.newsContentMapper.selectEntityPaginatedList(t);
    }

}
