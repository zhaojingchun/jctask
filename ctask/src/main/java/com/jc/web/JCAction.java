package com.jc.web;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;

/**
 * Created by jingchun on 2015/5/31.
 */
abstract  public class JCAction extends ActionSupport {
    public String getRootPath(){
        ServletContext sc =  ServletActionContext.getServletContext();
        return  sc.getRealPath("/");
    }

}
