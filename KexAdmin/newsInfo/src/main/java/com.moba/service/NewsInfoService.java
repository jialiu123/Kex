package com.moba.service;

import com.moba.domain.NewsContent;
import com.moba.domain.NewsFile;
import com.moba.domain.NewsInfo;
import com.moba.mapper.NewsContentMapper;
import com.moba.mapper.NewsFileMapper;
import com.moba.mapper.NewsInfoMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author li, bai, qiang
 * @version 2019-02-26 14:53
 * @date 2019-02-26 14:53
 */
@Service
@Transactional
public class NewsInfoService {

    @Resource
    private NewsInfoMapper newsInfoMapper;

    @Resource
    private NewsContentMapper newsContentMapper;

    @Resource
    private NewsFileMapper newsFileMapper;

    public NewsInfo getEntity(NewsInfo t) {


        NewsInfo newsInfoResult = this.newsInfoMapper.selectEntity(t);
        if (null != newsInfoResult) {
            NewsContent newsContent = new NewsContent();
            newsContent.setId(newsInfoResult.getId());
            newsContent = this.newsContentMapper.selectEntity(newsContent);

            NewsFile newsFile = new NewsFile();
            newsFile.setLinkId(newsInfoResult.getId());
            List<NewsFile> fileList = this.newsFileMapper.selectEntityList(newsFile);
            newsInfoResult.setNewsContent(newsContent.getContent());
            newsInfoResult.setNewsFileList(fileList);
        }

        return newsInfoResult;
    }

    public Integer getEntityCount(NewsInfo t) {
        return this.newsInfoMapper.selectEntityCount(t);
    }

    public List<NewsInfo> getEntityList(NewsInfo t) {
        return this.newsInfoMapper.selectEntityList(t);
    }

    public Integer createEntity(NewsInfo t) {
        int id = this.newsInfoMapper.insertEntity(t);

        if (null != t.getNewsContent()) {
            NewsContent newsContent = new NewsContent();
            newsContent.setId(t.getId());
            newsContent.setContent(t.getNewsContent().toString());
            this.newsContentMapper.insertEntity(newsContent);
        }
        if (null != t.getNewsFileList()) {
            for (int i = 0; i < t.getNewsFileList().size(); i++) {
                NewsFile newsFile = t.getNewsFileList().get(i);
                newsFile.setLinkId(t.getId());
                this.newsFileMapper.insertEntity(newsFile);
            }
        }
        return id;
    }

    public int updateEntity(NewsInfo t) {
        if (null != t.getMap().get("update_newsInfo")) {
            String content_news_update = t.getNewsContent();
            //先查询一下是不是之前就没有插入新闻内容
            NewsContent newsConentSelect = new NewsContent();
            newsConentSelect.setId(t.getId());
            newsConentSelect = this.newsContentMapper.selectEntity(newsConentSelect);
            if (null != newsConentSelect) {
                // 说明之前有插入过
                if (StringUtils.isNotBlank(content_news_update)) {
                    NewsContent newsContent = new NewsContent();
                    newsContent.setId(t.getId());
                    newsContent.setContent(content_news_update);
                    this.newsContentMapper.updateEntity(newsContent);
                }
            } else {
                // 说明之前就没有插入过
                if (StringUtils.isNotBlank(content_news_update)) {
                    NewsContent newsContent = new NewsContent();
                    newsContent.setId(t.getId());
                    newsContent.setContent(content_news_update);
                    this.newsContentMapper.insertEntity(newsContent);
                }
            }

            // 先查询一下是不是之前就没有插入文件
            NewsFile file = new NewsFile();
            file.setLinkId(t.getId());

            List<NewsFile> oldFileList = this.newsFileMapper.selectEntityList(file);

            if (null != oldFileList && oldFileList.size() > 0) {
                // 说明之前有插入过
                for (NewsFile temp : oldFileList) {
                    //先删除已存在的记录
                    this.newsFileMapper.deleteEntity(temp);
                }

            }

            if (null != t.getNewsFileList() && t.getNewsFileList().size() > 0) {

                for (NewsFile temp : t.getNewsFileList()) {
                    //先删除已存在的记录
                    this.newsFileMapper.insertEntity(temp);
                }
            }
        }
        return this.newsInfoMapper.updateEntity(t);
    }

    public int removeEntity(NewsInfo t) {
        return this.newsInfoMapper.deleteEntity(t);
    }

    public List<NewsInfo> getEntityPaginatedList(NewsInfo t) {

        List<NewsInfo> newsInfoList = this.newsInfoMapper.selectEntityPaginatedList(t);
        if (newsInfoList != null && newsInfoList.size() > 0) {
            for (int i = 0; i < newsInfoList.size(); i++) {
                NewsContent content = new NewsContent();
                content.setId(newsInfoList.get(i).getId());
                content = this.newsContentMapper.selectEntity(content);
                if (null != content) {
                    newsInfoList.get(i).setNewsContent(content.getContent());
                }
                NewsFile file = new NewsFile();
                file.setLinkId(newsInfoList.get(i).getId());
                List<NewsFile> list = this.newsFileMapper.selectEntityList(file);
                if (list != null && list.size() > 0) {
                    newsInfoList.get(i).setNewsFileList(list);
                }
            }
        }
        return newsInfoList;

    }

}
