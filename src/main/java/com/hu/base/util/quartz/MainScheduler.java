package com.hu.base.util.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Author hutiantian
 * @Date 2019/1/30 15:50:18
 */
public class MainScheduler {
    //创建调度器
    public static Scheduler getScheduler() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        return schedulerFactory.getScheduler();
    }


    public static void schedulerJob() throws SchedulerException{
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(MyJob2.class).withIdentity("job1", "group1").build();
//        JobDetail jobDetail1 = JobBuilder.newJob(MyJob1.class).withIdentity("job2", "group1").build();
        //创建触发器 每3秒钟执行一次
        Trigger cronTrigger=TriggerBuilder.newTrigger().withIdentity("trigger1", "trigger-group").
                withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?")).build();
        Scheduler scheduler = getScheduler();
        //将任务及其触发器放入调度器
        scheduler.scheduleJob(jobDetail, cronTrigger);
//        scheduler.scheduleJob(jobDetail1, trigger);
        //调度器开始调度任务
        scheduler.start();

    }

    public static void main(String[] args) throws SchedulerException {
        MainScheduler mainScheduler = new MainScheduler();
        mainScheduler.schedulerJob();
    }

}
