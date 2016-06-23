package com.netease.wechat.service;

import java.util.List;

import com.netease.edu.commons.service.BaseService;
import com.netease.wechat.domain.WechatAutoReply;
import com.netease.wechat.dto.Article;

public interface WechatAutoReplyService extends BaseService<WechatAutoReply> {

    WechatAutoReply getByKey(String k);

    List<Article> getArticlesByKey(String k);

}
