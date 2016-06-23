package com.netease.wechat.dto;

public class WechatTextMsg extends WechatBaseMsg {

    public static final String FILED_CONTENT   = "Content";
    public static final String DEFAULT_CONTENT = "您好，您的消息我们已经收到，会尽快通过微信联系您！";

    private String             Content;

    public WechatTextMsg(String content) {
        super();
        Content = content;
    }

    public WechatTextMsg(String content, String toUserName, String fromUserName, Long createTime, String msgType,
                         Long msgId) {
        super(toUserName, fromUserName, createTime, msgType, msgId);
        Content = content;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

}
