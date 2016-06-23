package test.learn.httpclient;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestHttpclient {

    private static final Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();

    public static void main(String[] args) {
        HttpClient httpclient = new DefaultHttpClient();
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/json");
        Map<String, String> params = new HashMap<String, String>();
        params.put("old", g.toJson(new Object[0]));
        params.put("input", "八拜之交");
        String result=HttpUtil.postUrl(httpclient, "http://192.168.1.125:12200/api/idiom-solitaire", "utf-8", header, params);
        System.out.println(result);
    }
}
