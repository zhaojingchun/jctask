package com.jc.service.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

/**
 * Created by jingchun on 2015/5/7.
 */
public class JobListener extends JobListenerSupport {

    @Override
    public String getName() {
        return "jobListener";
    }

    public void jobToBeExecuted(JobExecutionContext context) {
        String name = context.getClass().getSimpleName();
        String instName = context.getJobDetail().getName();
//        String groupName = context.getJobDetail().getGroup();
        System.out.println("开始执行SimpleJob");
    }

    public void jobWasExecuted(JobExecutionContext context,
                               JobExecutionException jobException) {
        System.out.println("SimpleJob执行完成");
    }
}
