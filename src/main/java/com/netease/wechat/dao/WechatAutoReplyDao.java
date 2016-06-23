package com.netease.wechat.dao;

import com.netease.edu.commons.dao.BaseDao;
import com.netease.wechat.domain.WechatAutoReply;

public interface WechatAutoReplyDao extends BaseDao<WechatAutoReply> {

    WechatAutoReply getByKey(String k);
    
}
