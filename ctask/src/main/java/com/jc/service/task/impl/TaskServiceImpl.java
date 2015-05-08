package com.jc.service.task.impl;

import com.jc.service.task.TaskService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.xwork.StringUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaojingchun
 * Date: 15-5-8
 * Time: 下午3:54
 * To change this template use File | Settings | File Templates.
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {
    @Resource
    private Scheduler deltePathFilesScheduler;
    public void  schedulersStartUp(String value){
        List<String> list = Arrays.asList(StringUtils.split(value,","));
        for(String str : list){
            try {
                Object schedulerObj = (Scheduler)PropertyUtils.getProperty(this,str);
                if(null!=schedulerObj){
                    Scheduler scheduler = (Scheduler) schedulerObj;
                    scheduler.start();
                }
            }  catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Scheduler getDeltePathFilesScheduler() {
        return deltePathFilesScheduler;
    }

    public void setDeltePathFilesScheduler(Scheduler deltePathFilesScheduler) {
        this.deltePathFilesScheduler = deltePathFilesScheduler;
    }
}
