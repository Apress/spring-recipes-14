package com.apress.springrecipes.springbatch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Throwable {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.apress.springrecipes.springbatch.config");

        JobLauncher jobLauncher = context.getBean("jobLauncher", JobLauncher.class);
        Job job = context.getBean("insertIntoDbFromCsvJob", Job.class);
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("date", new Date());
        JobParameters jobParameters = jobParametersBuilder.toJobParameters();
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        BatchStatus batchStatus = jobExecution.getStatus();

        while (batchStatus.isRunning()) {
            System.out.println("Still running...");
            Thread.sleep(1000);
        }
        System.out.println("Exit status: " + jobExecution.getExitStatus().getExitCode());

        JobInstance jobInstance = jobExecution.getJobInstance();
        System.out.println("job instance Id: " + jobInstance.getId());


    }
}
