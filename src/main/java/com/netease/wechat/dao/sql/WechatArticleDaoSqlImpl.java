package com.netease.wechat.dao.sql;


import org.springframework.stereotype.Component;

import com.netease.edu.commons.dao.annotation.DomainMetadata;
import com.netease.edu.commons.dao.sql.BaseDaoSqlImpl;
import com.netease.wechat.dao.WechatArticleDao;
import com.netease.wechat.domain.WechatArticle;


@Component
@DomainMetadata(domainClass= WechatArticle.class)
public class WechatArticleDaoSqlImpl extends BaseDaoSqlImpl<WechatArticle> implements WechatArticleDao {
    
    
}
