package com.netease.wechat.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.netease.wechat.dto.Article;
import com.netease.wechat.dto.WechatBaseMsg;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class WechatCommonUtils {

    /**
     * 将解析结果存储在HashMap中
     * 
     * @param request
     * @return
     */
    public static Map<String, String> putRequestXML2Map(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            @SuppressWarnings("unchecked")
            List<Element> elementList = root.elements();
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
            }
            return map;
        } catch (Exception e) {
            return map;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static String messageToXml(WechatBaseMsg wechatBaseMsg) {
        xstream.alias("xml", wechatBaseMsg.getClass());
        if (WechatBaseMsg.WECHAT_MESSAGE_TYPE_NEWS.equals(wechatBaseMsg.getMsgType())) {
            xstream.alias("item", new Article().getClass());
        }
        return xstream.toXML(wechatBaseMsg);
    }

    /**
     * 扩展xstream，使其支持CDATA块
     * 
     * @date 2013-05-19
     */
    private static XStream xstream = new XStream(new XppDriver() {

                                       public HierarchicalStreamWriter createWriter(Writer out) {
                                           return new PrettyPrintWriter(out) {

                                               // 对所有xml节点的转换都增加CDATA标记
                                               boolean cdata = true;

                                               @SuppressWarnings("unchecked")
                                               public void startNode(String name, Class clazz) {
                                                   super.startNode(name, clazz);
                                               }

                                               protected void writeText(QuickWriter writer, String text) {
                                                   if (cdata) {
                                                       writer.write("<![CDATA[");
                                                       writer.write(text);
                                                       writer.write("]]>");
                                                   } else {
                                                       writer.write(text);
                                                   }
                                               }
                                           };
                                       }
                                   });

}
