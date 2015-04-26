package com.jc.web;

import com.jc.dao.FirstDao;
import com.jc.domain.Team;
import com.jc.service.FitstServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-4-22
 * Time: 下午1:06
 * To change this template use File | Settings | File Templates.
 */
public class first  extends ActionSupport{

    private FitstServiceImpl fitstService;

    private FirstDao firstDao;

    public String execute() throws Exception {
        javax.servlet.ServletContext ServletContext = ServletActionContext.getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(ServletContext);
        ((FitstServiceImpl)ctx.getBean("fitstService",FitstServiceImpl.class)).helloWord();
        System.out.println("where is hui");
        return "index";    //To change body of overridden methods use File | Settings | File Templates.
    }

    public String second(){
        try{
            Logger.getLogger("test1.test2").debug("test2");
            fitstService.helloWord();
            System.out.println("aaaaaaaaaa");
//            Map<String ,Object> param = new HashMap<String,Object>();
//            param.put("id",999) ;
//            Team team = firstDao.getTeamByid(param);
//            System.out.print(team.toString());
        }catch (Exception e){
            Logger.getLogger("test1.test2").error(e.getMessage(),e);
        }

        return "index";
    }

    public FitstServiceImpl getFitstService() {
        return fitstService;
    }

    public void setFitstService(FitstServiceImpl fitstService) {
        this.fitstService = fitstService;
    }

    public FirstDao getFirstDao() {
        return firstDao;
    }

    public void setFirstDao(FirstDao firstDao) {
        this.firstDao = firstDao;
    }
}
