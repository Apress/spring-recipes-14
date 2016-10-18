package com.apress.springrecipes.replicator.config;

import com.apress.springrecipes.replicator.FileReplicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by marten on 26-05-14.
 */
@Configuration
@EnableScheduling
public class SchedulingConfiguration implements SchedulingConfigurer {

    @Autowired
    private FileReplicator fileReplicator;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(scheduler());
        taskRegistrar.addFixedDelayTask(new Runnable(){
            @Override
            public void run() {
                try {
                    fileReplicator.replicate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 60000);
    }

    @Bean(destroyMethod = "shutdown")
    public Executor scheduler() {
        return Executors.newScheduledThreadPool(10);
    }

}
