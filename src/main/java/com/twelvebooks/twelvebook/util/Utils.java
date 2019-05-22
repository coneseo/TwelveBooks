package com.twelvebooks.twelvebook.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {

    public static String getHttpPOST2String(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        HttpClient httpclient = HttpClients.custom().build();
        String responseBody = null;
        try {
            HttpPost post = new HttpPost(url);
            if (params != null && !params.isEmpty()) {

                List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
                for (String key : params.keySet()) {
                    parameters.add(new BasicNameValuePair(key, params.get(key)));
                }

                UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
                post.setEntity(reqEntity);
            }

            if (headers != null && !headers.isEmpty()) {
                for (String key : headers.keySet()) {
                    post.addHeader(key, headers.get(key));
                }
            }

            HttpResponse response = httpclient.execute(post);
            responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
        } finally {
            ((CloseableHttpClient) httpclient).close();
        }

        return responseBody;
    }
}
