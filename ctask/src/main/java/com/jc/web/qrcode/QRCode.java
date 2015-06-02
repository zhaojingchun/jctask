package com.jc.web.qrcode;

import com.jc.common.MyFileUtils;
import com.jc.common.NumberUtils;
import com.jc.service.qrcode.QRcodeUtil;
import com.jc.web.JCAction;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jingchun on 2015/5/31.
 */
public class QRCode  extends JCAction {
    private String content;
    private File upload;
    private Map<String,Object> jsonMap = new HashMap<String,Object>();

    /**
     * 返回到二维码jsp页
     * @return
     */
    public String qcJsp(){
        return "qc";
    }

    /**
     * 创建二维码图片
     */
    public void createQC(){
        String path = getFileName();
        File file = new File(path);
        QRcodeUtil.encoderQRCode(content,path);
        HttpServletResponse response = ServletActionContext.getResponse();
        String resStr = "";
        if(file.exists())resStr = file.getName();
        try {
            response.setContentType("text/html");
            response.getWriter().write(resStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析二维码
     * @return
     */
    public String decodeQC(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(upload);
            String resStr = QRcodeUtil.decoderQRCode(fis);
            jsonMap.put("data",resStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "jsonRes";
    }

    /**
     * 获取文件名
     * @return
     */
    public String getFileName(){
        String fileName = getRootPath()+"html"+ File.separator+NumberUtils.getRandom(2)+System.currentTimeMillis()+".png";
        MyFileUtils.creatFile(fileName);
       return fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public Map<String, Object> getJsonMap() {
        return jsonMap;
    }

    public void setJsonMap(Map<String, Object> jsonMap) {
        this.jsonMap = jsonMap;
    }
}
