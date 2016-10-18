package com.apress.springrecipes.springbatch.solution2;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;


public class HoroscopeDecider implements JobExecutionDecider {
    private boolean mercuryIsInRetrograde = Math.random() > .5;

    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        if (mercuryIsInRetrograde) {
            return FlowExecutionStatus.FAILED;
        }

        return FlowExecutionStatus.COMPLETED;
    }
}
