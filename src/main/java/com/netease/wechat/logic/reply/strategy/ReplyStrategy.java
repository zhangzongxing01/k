package com.netease.wechat.logic.reply.strategy;


import com.netease.wechat.dto.WechatBaseMsg;


public interface ReplyStrategy {
    public WechatBaseMsg operate(String ToUserName, String FromUserName,String Content);
}
