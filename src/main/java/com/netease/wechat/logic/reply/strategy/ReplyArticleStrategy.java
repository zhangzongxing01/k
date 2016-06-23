package com.netease.wechat.logic.reply.strategy;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.netease.wechat.dto.Article;
import com.netease.wechat.dto.WechatBaseMsg;
import com.netease.wechat.dto.WechatNewsMsg;
import com.netease.wechat.service.WechatAutoReplyService;
@Component
public class ReplyArticleStrategy implements ReplyStrategy {

    @Resource
    private WechatAutoReplyService wechatAutoReplyService;

    @Override
    public WechatBaseMsg operate(String ToUserName, String FromUserName, String Content) {
        List<Article> articleList = wechatAutoReplyService.getArticlesByKey(Content);
        WechatNewsMsg wechatNewsMsg=new WechatNewsMsg();
        wechatNewsMsg.setToUserName(ToUserName);
        wechatNewsMsg.setFromUserName(FromUserName);
        wechatNewsMsg.setCreateTime(System.currentTimeMillis());
        wechatNewsMsg.setArticleCount(articleList.size());
        wechatNewsMsg.setMsgType(WechatBaseMsg.WECHAT_MESSAGE_TYPE_NEWS);
        wechatNewsMsg.setArticles(articleList);
        return wechatNewsMsg;
    }

}
