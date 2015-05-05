package com.jc.web.upload;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jingchun on 2015/5/4.
 */
public class FileUploadAction  extends ActionSupport {
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    private String result;
    private Map<String,Object> jsonMap = new HashMap<String,Object>();

    public String uploadImage(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(upload);
            BufferedImage bufferedImg = ImageIO.read(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String path = ServletActionContext.getServletContext().getRealPath("/images");
        File pathFile = new File(path);
        if(!pathFile.exists()){
            pathFile.mkdirs();
        }
        try {
            FileUtils.copyFile(upload,new File(path,uploadFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonMap.put("status","sucess");
        return "jsonRes";
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Map<String, Object> getJsonMap() {
        return jsonMap;
    }

    public void setJsonMap(Map<String, Object> jsonMap) {
        this.jsonMap = jsonMap;
    }
}
