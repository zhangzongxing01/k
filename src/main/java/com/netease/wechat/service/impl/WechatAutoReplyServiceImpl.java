package com.netease.wechat.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.edu.commons.service.impl.BaseServiceImpl;
import com.netease.wechat.dao.WechatArticleDao;
import com.netease.wechat.dao.WechatAutoReplyDao;
import com.netease.wechat.domain.WechatArticle;
import com.netease.wechat.domain.WechatAutoReply;
import com.netease.wechat.dto.Article;
import com.netease.wechat.service.WechatAutoReplyService;

@Service
public class WechatAutoReplyServiceImpl extends BaseServiceImpl<WechatAutoReplyDao, WechatAutoReply> implements WechatAutoReplyService {

    private WechatAutoReplyDao dao;
    @Resource
    private WechatArticleDao   wechatArticleDao;

    public WechatAutoReplyDao getDao() {
        return dao;
    }

    @Autowired
    public void setWechatAutoReplyDao(WechatAutoReplyDao dao) {
        super.setBaseDao(dao);
        this.dao = dao;
    }

    @Override
    public WechatAutoReply getByKey(String k) {
        return dao.getByKey(k);
    }

    @Override
    public List<Article> getArticlesByKey(String articleIdsString) {
        List<Article> articles = new ArrayList<Article>();
        try {
            String[] articleIds = articleIdsString.split("\\,");
            List<Long> articleIdList = new ArrayList<Long>();
            for (String articleId : articleIds) {
                articleIdList.add(Long.valueOf(articleId));
            }
            List<WechatArticle> wechatArticleList = wechatArticleDao.getByIdList(articleIdList);
            if (CollectionUtils.isEmpty(wechatArticleList)) {
                return articles;
            }
            for (WechatArticle wechatArticle : wechatArticleList) {
                articles.add(Article.convert(wechatArticle));
            }
        } catch (Exception e) {
            return articles;
        }

        return articles;
    }
}
