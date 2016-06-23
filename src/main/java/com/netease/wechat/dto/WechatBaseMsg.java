package com.netease.wechat.dto;

public class WechatBaseMsg {

    public static final String WECHAT_MESSAGE_TYPE_TEXT     = "text";
    public static final String WECHAT_MESSAGE_TYPE_MUSIC    = "music";
    public static final String WECHAT_MESSAGE_TYPE_NEWS     = "news";
    public static final String WECHAT_MESSAGE_TYPE_VOICE    = "voice";
    public static final String WECHAT_MESSAGE_TYPE_IMAGE    = "image";
    public static final String WECHAT_MESSAGE_TYPE_VIDEO    = "video";

    public static final String WECHAT_MESSAGE_TYPE_LINK     = "link";
    public static final String WECHAT_MESSAGE_TYPE_LOCATION = "location";

    public static final String WECHAT_MESSAGE_TYPE_EVENT    = "event";
    public static final String EVENT_TYPE_SUBSCRIBE         = "subscribe";
    public static final String EVENT_TYPE_UNSUBSCRIBE       = "unsubscribe";

    public static final String FILED_TOUSERNAME             = "ToUserName";
    public static final String FILED_FROMUSERNAME           = "FromUserName";
    public static final String FILED_MSGTYPE                = "MsgType";

    private String             ToUserName;
    private String             FromUserName;
    private Long               CreateTime;                                   // 消息创建时间 （整型 秒）
    private String             MsgType;
    private Long               MsgId;

    public WechatBaseMsg() {
        super();
    }

    public WechatBaseMsg(String toUserName, String fromUserName, Long createTime, String msgType, Long msgId) {
        super();
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        MsgId = msgId;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public Long getMsgId() {
        return MsgId;
    }

    public void setMsgId(Long msgId) {
        MsgId = msgId;
    }

}
