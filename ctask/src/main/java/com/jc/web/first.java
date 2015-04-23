package com.jc.web;

import com.jc.service.FitstServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-4-22
 * Time: 下午1:06
 * To change this template use File | Settings | File Templates.
 */
public class first  extends ActionSupport{

    private FitstServiceImpl fitstService;

    public String execute() throws Exception {
        javax.servlet.ServletContext ServletContext = ServletActionContext.getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(ServletContext);
        ((FitstServiceImpl)ctx.getBean("fitstService",FitstServiceImpl.class)).helloWord();
        System.out.println("where is hui");
        return "index";    //To change body of overridden methods use File | Settings | File Templates.
    }

    public String second(){
        fitstService.helloWord();
        System.out.println("aaaaaaaaaa");
        return "index";
    }



}
