package com.hu.base.util.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * @Author hutiantian
 * @Date 2019/1/30 15:50:00
 */
public class MyJob2 implements Job {
    public void execute(JobExecutionContext context){
        System.out.println("I am job two ");
    }
}
