package com.netease.wechat.logic.reply;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.netease.wechat.dto.WechatBaseMsg;
import com.netease.wechat.logic.reply.strategy.ReplyArticleStrategy;
import com.netease.wechat.logic.reply.strategy.ReplyStrategy;

/**
 * 实现自动回复的策略。
 * 
 * @author zzx
 */
@Component
public class ReplyStrategyContext {

    public static final String                INTRODUCTION = "介绍";
    public static final Map<String, ReplyStrategy> strategyMap  = new HashMap<String, ReplyStrategy>();
    static {
        strategyMap.put(INTRODUCTION, new ReplyArticleStrategy());
    }
    private ReplyStrategy                          strategy;

    public ReplyStrategyContext() {

    }

    public ReplyStrategyContext(ReplyStrategy strategy) {
        this.strategy = strategy;
    }

    public WechatBaseMsg getReplyMsgByContent(String ToUserName, String FromUserName, String Content) {
        return strategy.operate(ToUserName, FromUserName, Content);
    }
}
