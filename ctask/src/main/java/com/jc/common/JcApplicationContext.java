package com.jc.common;


import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-4-30
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
@Component("jcApplicationContext")
public class JcApplicationContext implements ServletContextAware {

    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext=servletContext;
    }
    public String getRootPath() {
        return servletContext.getRealPath("/");
    }

}
