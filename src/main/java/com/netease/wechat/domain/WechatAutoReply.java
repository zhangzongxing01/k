package com.netease.wechat.domain;

import com.netease.edu.commons.domain.BaseDomain;

public class WechatAutoReply extends BaseDomain {

    private static final long   serialVersionUID = 1L;
    public static final Integer TYPE_ARTICLE     = 1;
    public static final Integer TYPE_TEXT        = 2;
    private String              key;
    private Integer             type;
    private String              value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
