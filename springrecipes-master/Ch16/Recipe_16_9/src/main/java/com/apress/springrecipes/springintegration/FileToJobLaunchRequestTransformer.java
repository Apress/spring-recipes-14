package com.apress.springrecipes.springintegration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.integration.launch.JobLaunchRequest;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

import java.io.File;

/**
 * Created by marten on 15-08-14.
 */
public class FileToJobLaunchRequestTransformer {

    private final Job job;
    private final String fileParameterName;

    public FileToJobLaunchRequestTransformer(Job job, String fileParameterName) {
        this.job=job;
        this.fileParameterName=fileParameterName;
    }

    @Transformer
    public JobLaunchRequest transform(Message<File> message) throws Exception {
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addString(fileParameterName, message.getPayload().getAbsolutePath());
        return new JobLaunchRequest(job, builder.toJobParameters());
    }
}
