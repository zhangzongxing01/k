package com.netease.wechat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.edu.commons.service.impl.BaseServiceImpl;
import com.netease.wechat.dao.WechatArticleDao;
import com.netease.wechat.domain.WechatArticle;
import com.netease.wechat.service.WechatArticleService;

@Service
public class WechatArticleServiceImpl extends BaseServiceImpl<WechatArticleDao, WechatArticle> implements WechatArticleService {

    private WechatArticleDao dao;

    public WechatArticleDao getDao() {
        return dao;
    }

    @Autowired
    public void setWechatArticleDaoDao(WechatArticleDao dao) {
        super.setBaseDao(dao);
        this.dao = dao;
    }

}
