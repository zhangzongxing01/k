package com.netease.wechat.dao.sql;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.netease.edu.commons.dao.annotation.DomainMetadata;
import com.netease.edu.commons.dao.sql.BaseDaoSqlImpl;
import com.netease.wechat.dao.WechatAutoReplyDao;
import com.netease.wechat.domain.WechatAutoReply;

@Component
@DomainMetadata(domainClass = WechatAutoReply.class)
public class WechatAutoReplyDaoSqlImpl extends BaseDaoSqlImpl<WechatAutoReply> implements WechatAutoReplyDao {
    @Override
    public WechatAutoReply getByKey(String k) {
        List<WechatAutoReply> wechatAutoReplyList= getByCondition("key = ? ", k);
        if(CollectionUtils.isNotEmpty(wechatAutoReplyList)){
            return wechatAutoReplyList.get(0);
        }
        return null;
    }
}
