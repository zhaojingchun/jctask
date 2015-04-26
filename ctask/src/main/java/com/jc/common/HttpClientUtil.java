package com.jc.common;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingchun on 2015/4/25.
 */
public class HttpClientUtil {
    private Logger log = Logger.getLogger(HttpClientUtil.class);
    public String  post (String url) {
        //创建httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httpPost = new HttpPost(url);
        //放头部信息
        httpPost.setHeader("Referer", "http://365.good7000.com/xz/cn/");
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("username", "admin"));
        formparams.add(new BasicNameValuePair("password", "123456"));

        UrlEncodedFormEntity uefEntity;
        log.debug("executing request " + httpPost.getURI());
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httpPost.setEntity(uefEntity);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String str = EntityUtils.toString(entity, "UTF-8");
                log.debug("--------------------------------------");
                log.debug("Response content: " + str);
                log.debug("--------------------------------------");
                return str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return "";
    }

    public byte[]  get (String url) {
        //创建httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建httppost
        HttpGet httpGet = new HttpGet(url);
        //放头部信息
        httpGet.setHeader("Referer", "http://365.good7000.com/xz/cn/");
        log.debug("executing request " + httpGet.getURI());
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                byte[] bytes = EntityUtils.toByteArray(entity);
                log.debug("--------------------------------------");
//                log.debug("Response content: " + bytes);
                log.debug("--------------------------------------");
                return bytes;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public void printStr(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
    }


}
