package com.apress.springrecipes.replicator;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context =
                new AnnotationConfigApplicationContext("com.apress.springrecipes.replicator.config");

        FileReplicator documentReplicator = context.getBean(FileReplicator.class);

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("fileReplicator", documentReplicator);

        JobDetail job = JobBuilder.newJob(FileReplicationJob.class)
                .withIdentity("documentReplicationJob")
                .storeDurably()
                .usingJobData(jobDataMap)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("documentReplicationTrigger")
                .startAt(new Date(System.currentTimeMillis() + 5000))
                .forJob(job)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/60 * * * * ?"))
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);

    }
}