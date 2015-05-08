package com.jc.service.listener;

import com.jc.service.task.TaskService;
import com.jc.service.task.impl.TaskServiceImpl;
import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Created by jingchun on 2015/5/7.
 */
public class TaskStartUpListener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(TaskStartUpListener.class.getName());
    private static final String serviceStr = "taskService";
    @Override
    public void contextInitialized(ServletContextEvent sc) {
        try {
            List<String> ipList = getHostAddress();
            WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc.getServletContext());
            Map<String,String> startUpMap = ctx.getBean("autoStartUpMap", Map.class);
            TaskService taskService =ctx.getBean(serviceStr, TaskServiceImpl.class);;
            ctx.getBean("autoStartUpMap", Map.class);
            Iterator<Map.Entry<String,String>> iterator = startUpMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String,String> entry = iterator.next();
                String key = entry.getKey();
                if(ipList.contains(key)){
                    taskService.schedulersStartUp(entry.getValue());
                }
            }
        } catch (Exception e) {
            log.info("Method:");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    /**
     * 获取本机ip list
     * @return
     */
    public List<String> getHostAddress(){
        List<String> list = new ArrayList<String>();
        try{
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements())
            {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements())
                {
                    ip = (InetAddress) addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address && !ip.getHostAddress().equals("127.0.0.1"))
                    {
                        log.info("Method:TaskStartUpListener.getHostAddress-->本机IP:"+ip.getHostAddress());
                        list.add(ip.getHostAddress());
                    }
                }
            }
        } catch (Exception e) {
            log.error("Method:TaskStartUpListener.getHostAddress-->获取本机IP异常!:" + e.getMessage(), e);
        }
        return  list;
    }

    public void getHostip() throws UnknownHostException {
        InetAddress addr =  InetAddress.getLocalHost();
        String ip = addr.getLocalHost().getHostAddress().toString(); //本机ip
        String address1 =addr.getHostName().toString();//获得本机名称
    }

}
