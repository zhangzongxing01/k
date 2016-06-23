package com.netease.wechat.domain;

import javax.annotation.Resource;

import org.junit.Test;

import com.netease.common.BaseTestCase;
import com.netease.wechat.dao.WechatArticleDao;


public class WechatArticleTest extends BaseTestCase{
    @Resource
    private WechatArticleDao wechatArticleDao;
    @Test
    public void testWechatArticleDao(){
        WechatArticle wechatArticle= wechatArticleDao.getById(1L);
        System.out.println(wechatArticle);
    }
}
