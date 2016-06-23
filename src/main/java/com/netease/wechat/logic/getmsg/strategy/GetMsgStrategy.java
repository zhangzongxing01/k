package com.netease.wechat.logic.getmsg.strategy;

import java.util.Map;

import com.netease.wechat.dto.WechatBaseMsg;


public interface GetMsgStrategy {
    public WechatBaseMsg operate(Map<String,String> Content);
}
