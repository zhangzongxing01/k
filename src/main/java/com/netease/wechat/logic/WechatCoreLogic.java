package com.netease.wechat.logic;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Component;

import com.netease.common.tools.SHA1Engine;
import com.netease.common.tools.StringUtils;
import com.netease.wechat.constants.WechatContants;
import com.netease.wechat.domain.WechatAutoReply;
import com.netease.wechat.dto.WechatBaseMsg;
import com.netease.wechat.dto.WechatTextMsg;
import com.netease.wechat.logic.getmsg.GetMsgStrategyContext;
import com.netease.wechat.logic.getmsg.strategy.GetMsgDefaultStrategy;
import com.netease.wechat.logic.getmsg.strategy.GetMsgStrategy;
import com.netease.wechat.logic.getmsg.strategy.GetMsgTextStrategy;
import com.netease.wechat.logic.reply.strategy.ReplyTextStrategy;
import com.netease.wechat.service.WechatAutoReplyService;
import com.netease.wechat.tools.WechatCommonUtils;

@Component
public class WechatCoreLogic {

    @Resource
    private WechatAutoReplyService wechatAutoReplyService;

    /**
     * 验证签名
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public boolean checkSignature(String signature, String timestamp, String nonce) {
        if (org.apache.commons.lang.StringUtils.isEmpty(signature)
            || org.apache.commons.lang.StringUtils.isEmpty(timestamp)
            || org.apache.commons.lang.StringUtils.isEmpty(nonce)) {
            return false;
        }
        String[] arr = new String[] { WechatContants.WECHAT_TOKEN, timestamp, nonce };
        Arrays.sort(arr);
        String mySignature = SHA1Engine.encrypt(StringUtils.buildString(arr));
        if (mySignature.equals(signature)) {
            return true;
        }
        return false;
    }

    /**
     * 处理用户发送的消息和事件，并返回给用户
     * 
     * @throws IOException
     * @throws DocumentException
     */
    public String processRequest(HttpServletRequest request) {
        WechatBaseMsg wechatBaseMsg = null;
        // 从request中获取消息，从XML转换成Map
        Map<String, String> params = WechatCommonUtils.putRequestXML2Map(request);
        String msgType = params.get(WechatBaseMsg.FILED_MSGTYPE);
        GetMsgStrategy strategy = null;
        if (WechatBaseMsg.WECHAT_MESSAGE_TYPE_EVENT.equals(msgType)) {

        } else if (WechatBaseMsg.WECHAT_MESSAGE_TYPE_TEXT.equals(msgType)) {
            strategy = new GetMsgTextStrategy();
        } else {
            strategy = new GetMsgDefaultStrategy();
        }
        GetMsgStrategyContext strategyContext = new GetMsgStrategyContext(strategy);
        wechatBaseMsg = strategyContext.getReplyMsgByContent(params);
        return WechatCommonUtils.messageToXml(wechatBaseMsg);
    }

}
