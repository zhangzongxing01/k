package com.netease.wechat.logic.reply.strategy;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.netease.wechat.dto.WechatBaseMsg;
import com.netease.wechat.dto.WechatTextMsg;
import com.netease.wechat.service.WechatAutoReplyService;
@Component
public class ReplyTextStrategy implements ReplyStrategy {

    @Resource
    private WechatAutoReplyService wechatAutoReplyService;

    @Override
    public WechatBaseMsg operate(String ToUserName, String FromUserName, String content) {
        Long CreateTime = System.currentTimeMillis();
        WechatBaseMsg wechatBaseMsg = new WechatTextMsg(content, ToUserName, FromUserName, CreateTime,
                                                        WechatBaseMsg.WECHAT_MESSAGE_TYPE_TEXT, null);
        return wechatBaseMsg;
    }

}
