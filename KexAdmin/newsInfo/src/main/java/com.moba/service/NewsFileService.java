package com.moba.service;

import com.moba.domain.NewsFile;
import com.moba.mapper.NewsFileMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author li, bai,qiang
 * @version 2019-02-27 14:05
 * @date 2019-02-27 14:05
 */
@Service
@Transactional
public class NewsFileService {

    @Resource
    private NewsFileMapper newsFileMapper;

    public NewsFile getEntity(NewsFile t) {
        return this.newsFileMapper.selectEntity(t);
    }

    public Integer getEntityCount(NewsFile t) {
        return this.newsFileMapper.selectEntityCount(t);
    }

    public List<NewsFile> getEntityList(NewsFile t) {
        return this.newsFileMapper.selectEntityList(t);
    }

    public Integer createEntity(NewsFile t) {
        this.newsFileMapper.insertEntity(t);
        return t.getId();
    }

    public int updateEntity(NewsFile t) {
        return this.newsFileMapper.updateEntity(t);
    }

    public int removeEntity(NewsFile t) {
        return this.newsFileMapper.deleteEntity(t);
    }

    public List<NewsFile> getEntityPaginatedList(NewsFile t) {
        return this.newsFileMapper.selectEntityPaginatedList(t);
    }

}
