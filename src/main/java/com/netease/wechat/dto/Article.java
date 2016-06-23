package com.netease.wechat.dto;

import net.sf.cglib.beans.BeanCopier;

import org.apache.commons.lang.Validate;

import com.netease.wechat.domain.WechatArticle;

public class Article {

    // 图文消息名称
    private String Title;
    // 图文消息描述
    private String Description;
    // 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
    private String PicUrl;
    // 点击图文消息跳转链接
    private String Url;

    public static WechatArticle convert(Article domain) {
        Validate.notNull(domain);
        WechatArticle dto = new WechatArticle();
        BeanCopier copier = BeanCopier.create(Article.class, WechatArticle.class, false);
        copier.copy(domain, dto, null);
        return dto;
    }

    public static Article convert(WechatArticle dto) {
        Validate.notNull(dto);
        Article domain = new Article();
        BeanCopier copier = BeanCopier.create(WechatArticle.class, Article.class, false);
        copier.copy(dto, domain, null);
        return domain;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return null == Description ? "" : Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return null == PicUrl ? "" : PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return null == Url ? "" : Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

}
