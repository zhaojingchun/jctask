package com.jc.common;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingchun on 2015/4/25.
 */
@Component
public class HttpClientUtil {
    private static Integer CONNECTION_TIMEOUT = 2 * 1000;    //设置请求超时2秒钟  根据业务调整
    private static Integer SO_TIMEOUT = 2 * 1000;        //设置等待数据超时时间2秒钟 根据业务调整
    //定义了当从ClientConnectionManager中检索ManagedClientConnection实例时使用的毫秒级的超时时间
    //这个参数期望得到一个java.lang.Long类型的值。如果这个参数没有被设置，默认等于CONNECTION_TIMEOUT，因此一定要设置
    private static Long CONN_MANAGER_TIMEOUT = 500L;  //该值就是连接不够用的时候等待超时时间，一定要设置，而且不能太大  ()

    private Logger log = Logger.getLogger(HttpClientUtil.class);
    @Resource
    private String charSet="";
    public String  post (String url) {
        //创建httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httpPost = new HttpPost(url);
        //放头部信息
        httpPost.setHeader("Referer", "http://365.good7000.com/xz/cn/");
//        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, soTimeOut);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("username", "admin"));
        formparams.add(new BasicNameValuePair("password", "123456"));

        UrlEncodedFormEntity uefEntity;
        log.debug("executing request " + httpPost.getURI());
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            uefEntity = new UrlEncodedFormEntity(formparams, charSet);
            httpPost.setEntity(uefEntity);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String str = EntityUtils.toString(entity, charSet);
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

    public String getCharSet() {
        return charSet;
    }
    public void printStr(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }


//    HttpConnectionManagerParams params = new HttpConnectionManagerParams();
//    params.setConnectionTimeout(2000);
//    params.setSoTimeout(2000);
//    // 最大连接数
//    params.setMaxTotalConnections(500);
//    params.setDefaultMaxConnectionsPerHost(500);
//    params.setStaleCheckingEnabled(true);
//    connectionManager.setParams(params);
//
//    HttpClientParams httpClientParams = new HttpClientParams();
//    // 设置httpClient的连接超时，对连接管理器设置的连接超时是无用的
//    httpClientParams.setConnectionManagerTimeout(5000); //等价于4.2.3中的CONN_MANAGER_TIMEOUT
//    httpClient = new HttpClient(connectionManager);
//    httpClient.setParams(httpClientParams);
//
//    //另外设置http client的重试次数，默认是3次；当前是禁用掉（如果项目量不到，这个默认即可）
//    httpClientParams.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));

}
