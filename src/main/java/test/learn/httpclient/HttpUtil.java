package test.learn.httpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * User: ykxu Date: 12-9-3 Time: 下午2:07
 */
public class HttpUtil {

    private static final Logger logger = Logger.getLogger(HttpUtil.class);

    public static int checkURL(String url) {

        HttpClient httpclient = new DefaultHttpClient();

        try {
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpget);
            StatusLine code = response.getStatusLine();
            return code.getStatusCode();
        } catch (Exception e) {
            logger.error(e, e);
        } finally {
            // 释放连接
            httpclient.getConnectionManager().shutdown();
        }

        return 0;
    }

    // 去掉所有ssl验证
    public static String getURL(String url, String charsetName, Map<String, String> header) {
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 3000);
        String content = "";
        try {
            httpclient = WebClientDevWrapper.wrapClient(httpclient);
            HttpGet httpget = new HttpGet(url);
            if (header != null && !header.isEmpty()) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpget.setHeader(entry.getKey(), entry.getValue());
                }
            }
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            StringBuilder sb = new StringBuilder();
            BufferedReader red = new BufferedReader(new InputStreamReader(entity.getContent(), charsetName));
            String line = red.readLine();
            while (line != null) {
                sb.append(line);
                line = red.readLine();
            }
            EntityUtils.consume(entity);
            content = sb.toString();
        } catch (Exception e) {
            logger.error(url, e);
        } finally {
            // 释放连接
            httpclient.getConnectionManager().shutdown();
        }

        return content;
    }

    public static String postUrl(HttpClient httpclient, String url, String charsetName, Map<String, String> header,
                                 Map<String, String> params) {
        HttpPost httpost = new HttpPost(url);
        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
        String content = "";
        try {
            httpclient = WebClientDevWrapper.wrapClient(httpclient);

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            if (params != null) {
                Set<String> keySet = params.keySet();
                for (String key : keySet) {
                    nvps.add(new BasicNameValuePair(key, params.get(key)));
                }
            }
            if (header != null && !header.isEmpty()) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            httpost.setEntity(new UrlEncodedFormEntity(nvps, charsetName));
            HttpResponse response = httpclient.execute(httpost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                StringBuilder sb = new StringBuilder();
                BufferedReader red = new BufferedReader(new InputStreamReader(entity.getContent(), charsetName));
                String line = red.readLine();
                while (line  != null) {
                    sb.append(line);
                    line = red.readLine();
                }
                EntityUtils.consume(entity);
                content = sb.toString();
            } 
//            else {
//                logger.error("get  from K12PersonalizedPushQuestionDep error, "+"params:"+params+",status is:"
//                             + response.getStatusLine().getStatusCode());
//            }

        } catch (Exception e) {
            logger.error(e, e);
        } finally {
            // 释放连接
            httpost.releaseConnection();
        }

        return content;
    }


}
