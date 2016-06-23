package com.netease.wechat.logic.getmsg.strategy;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.netease.wechat.domain.WechatAutoReply;
import com.netease.wechat.dto.WechatBaseMsg;
import com.netease.wechat.dto.WechatTextMsg;
import com.netease.wechat.logic.reply.strategy.ReplyArticleStrategy;
import com.netease.wechat.logic.reply.strategy.ReplyStrategy;
import com.netease.wechat.logic.reply.strategy.ReplyTextStrategy;
import com.netease.wechat.service.WechatAutoReplyService;

/**
 * 处理用户发来的文本。
 * 
 * @author Administrator
 */
@Component
public class GetMsgTextStrategy implements GetMsgStrategy {

    @Resource
    private WechatAutoReplyService wechatAutoReplyService;

    @Override
    public WechatBaseMsg operate(Map<String, String> params) {
        String userOpenId = params.get(WechatTextMsg.FILED_FROMUSERNAME);
        String officalAccount = params.get(WechatTextMsg.FILED_TOUSERNAME);
        String content = params.get(WechatTextMsg.FILED_CONTENT);
        WechatAutoReply wechatAutoReply = wechatAutoReplyService.getByKey(content);
        ReplyStrategy replyStrategy = null;
        String contents = null;

        if (wechatAutoReply != null && WechatAutoReply.TYPE_ARTICLE.equals(wechatAutoReply.getType())) {
            // 返回类型是news类型
            replyStrategy = new ReplyArticleStrategy();
            contents = wechatAutoReply.getValue();
        } else if (wechatAutoReply != null && WechatAutoReply.TYPE_TEXT.equals(wechatAutoReply.getType())) {
            // 返回类型是text类型
            replyStrategy = new ReplyTextStrategy();
            contents = wechatAutoReply.getValue();
        } else {
            //其他
            replyStrategy = new ReplyTextStrategy();
            contents = WechatTextMsg.DEFAULT_CONTENT;
        }
        return replyStrategy.operate(userOpenId, officalAccount, contents);
    }
}
