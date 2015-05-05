package com.jc.web.upload;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;

/**
 * Created by jingchun on 2015/5/4.
 */
public class FileUploadAction  extends ActionSupport {
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    private String result;

    public String uploadOneFile(){
        String path = ServletActionContext.getServletContext().getRealPath("/images");
        File pathFile = new File(path);
        if(!pathFile.exists()){
            pathFile.mkdirs();
        }
        try {
            FileUtils.copyFile(upload,new File(path,uploadFileName));
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
        return "sucess";
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
}
