package com.netease.wechat.logic.getmsg.strategy;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.netease.wechat.dto.WechatBaseMsg;
import com.netease.wechat.dto.WechatTextMsg;
import com.netease.wechat.logic.reply.strategy.ReplyStrategy;
import com.netease.wechat.logic.reply.strategy.ReplyTextStrategy;
@Component
public class GetMsgDefaultStrategy implements GetMsgStrategy {

    @Override
    public WechatBaseMsg operate(Map<String, String> params) {
        String userOpenId = params.get(WechatTextMsg.FILED_FROMUSERNAME);
        String officalAccount = params.get(WechatTextMsg.FILED_TOUSERNAME);
        ReplyStrategy replyStrategy = new ReplyTextStrategy();
        String contents = WechatTextMsg.DEFAULT_CONTENT;
        return replyStrategy.operate(userOpenId, officalAccount, contents);
    }

}
