package com.jc.web.qrcode;

import com.jc.common.HttpClientUtil;
import com.jc.common.MyFileUtils;
import com.jc.service.qrcode.QRcodeUtil;
import com.jc.web.JCAction;
import org.apache.struts2.ServletActionContext;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by jingchun on 2015/5/31.
 */
public class QRCode  extends JCAction {
    private String content;
    private File upload;
    private String uploadContentType;
    private String uploadFileName;

    public String qcJsp(){
        return "qc";
    }

    public void createQC(){
        String path = getFileName();
        QRcodeUtil.encoderQRCode(content,path );
        HttpServletResponse response = ServletActionContext.getResponse();
        int no = path.indexOf("html");
        String resStr =  "";
        if(no>0){
            resStr = path.substring(no+5);
        }

        try {
            response.getWriter().write(resStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void decodeQC(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(upload);
            String resStr =   QRcodeUtil.decoderQRCode(fis);
            HttpServletResponse response = ServletActionContext.getResponse();
            try {
                response.getWriter().write(resStr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFileName(){
        String fileName = getRootPath()  + "html"+ File.separator+System.currentTimeMillis()+".png";
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

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}
