package com.netease.wechat.web.bean;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.common.tools.HttpResponstUtils;
import com.netease.wechat.constants.WechatContants;
import com.netease.wechat.dao.WechatArticleDao;
import com.netease.wechat.logic.WechatCoreLogic;
import com.netease.wechat.service.WechatAutoReplyService;

@Controller
public class WechatCoreBean {

    @Resource
    private WechatCoreLogic        wechatCoreLogic;
    @Resource
    private WechatArticleDao       wechatArticleDao;
    @Resource
    private WechatAutoReplyService wechatAutoReplyService;

    @RequestMapping(value = "/w/core/checkSignature.do")
    public void checkSignatureAndGetSentMessage(HttpServletRequest request, HttpServletResponse response,
                                                @RequestParam(required = false) String signature,
                                                @RequestParam(required = false) String timestamp,
                                                @RequestParam(required = false) String nonce,
                                                @RequestParam(required = false) String echostr) throws IOException,
                                                                                               DocumentException {
        System.out.println("开始验证");
        String method = request.getMethod();
        if (WechatContants.HTTP_METHOD_GET.equalsIgnoreCase(method)) {// 如果是get方法，则验证
            if (wechatCoreLogic.checkSignature(signature, timestamp, nonce)) {
                HttpResponstUtils.writeResponse(response, echostr);// 将echostr返回
            }
        } else if (WechatContants.HTTP_METHOD_POST.equalsIgnoreCase(method)) {// 如果是post方法则是收发消息。
            wechatCoreLogic.processRequest(request);
        }
    }
    @ResponseBody
    @RequestMapping(value = "/test.do")
    public String  test(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(wechatArticleDao.getById(1L));
        System.out.println(wechatAutoReplyService.getById(1L));
        return wechatArticleDao.getById(1L).toString();
    }
}
