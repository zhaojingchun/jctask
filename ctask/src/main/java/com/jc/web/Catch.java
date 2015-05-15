package com.jc.web;

import com.jc.common.HttpClientUtil;
import com.jc.common.MyFileUtils;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by jingchun on 2015/4/25.
 */
public class Catch  extends ActionSupport {
    private static final Logger log = Logger.getLogger(Catch.class.getName());
    private String catchUrl;
    private HttpClientUtil httpClientUtil;
    public void catchPage(){
        log.debug("catchUrl : " + catchUrl);
        try{
            if(StringUtils.isNotBlank(catchUrl)){
                HttpServletResponse response = ServletActionContext.getResponse();
                String resStr =  httpClientUtil.post(catchUrl);
                response.getWriter().write(createFile(resStr));
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }

    public void catchFile(){
        log.debug("catchUrl : " + catchUrl);
        try{
            if(StringUtils.isNotBlank(catchUrl)){
                HttpServletResponse response = ServletActionContext.getResponse();
                byte[] bytes =  httpClientUtil.get(catchUrl);
                response.getWriter().write(createFile(bytes));
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }

    public String createFile(String fileStr){
        String fileName = "catch"+(int)(Math.random()*1000)+".html";
        ServletContext sc =  ServletActionContext.getServletContext();
        String path = sc.getRealPath("/");
        MyFileUtils.create(path + File.separator + "html", fileName, fileStr);
        return fileName;
    }

    public String createFile(byte[] bytes){
        long t = System.currentTimeMillis();
        String fileName = getFileName(catchUrl);
        ServletContext sc =  ServletActionContext.getServletContext();
        String path = sc.getRealPath("/");
        MyFileUtils.create(path + File.separator + "html", fileName, bytes);
        log.info("Catch.createFile 耗时 : "+(System.currentTimeMillis()-t)+" ms");
        return fileName;
    }

    public String getFileName(String url){
        if(StringUtils.isNotBlank(url)){
            int idx = url.lastIndexOf("/");
            if(idx>0){
                return url.substring(idx+1,url.length());
            }
        }
        return null;
    }

    public static void main(String[] args){
        try{
            Catch catch1 = new Catch();
            catch1.getFileName("http://djfk/jflkdsa/q.ss");
        }catch (Exception e){

        }
    }

    public String getCatchUrl() {
        return catchUrl;
    }

    public void setCatchUrl(String catchUrl) {
        this.catchUrl = catchUrl;
    }

    public HttpClientUtil getHttpClientUtil() {
        return httpClientUtil;
    }

    public void setHttpClientUtil(HttpClientUtil httpClientUtil) {
        this.httpClientUtil = httpClientUtil;
    }
}
