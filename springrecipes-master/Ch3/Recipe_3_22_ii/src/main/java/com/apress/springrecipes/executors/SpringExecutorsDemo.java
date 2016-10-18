package com.apress.springrecipes.executors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
 
public class SpringExecutorsDemo {
           public static void main(String[] args) {
	       ApplicationContext context =
		   new GenericXmlApplicationContext("appContext.xml");
                SpringExecutorsDemo demo = context.getBean(
                     "springExecutorsDemo", SpringExecutorsDemo.class);
                demo.submitJobs();
           }

           @Autowired
           private SimpleAsyncTaskExecutor asyncTaskExecutor;

           @Autowired
           private SyncTaskExecutor syncTaskExecutor;

           @Autowired
           private TaskExecutorAdapter taskExecutorAdapter;

         /*  No need, since the scheduling and submission is already configured,
      	    and done in the application context via Factory bean
           @Autowired
	   private ScheduledExecutorFactoryBean concurrentTaskScheduler;
	 */
           @Autowired
           private ThreadPoolTaskExecutor threadPoolTaskExecutor;

           @Autowired
           private DemonstrationRunnable task;

           public void submitJobs() {
	       syncTaskExecutor.execute(task);
	       taskExecutorAdapter.submit(task);
	       asyncTaskExecutor.submit(task);          
                      
                      /* will do 100 at a time, 
                             then queue the rest, ie,
                             should take round 5 seconds total 
                         */
	       for (int i = 0; i < 500; i++)
		   threadPoolTaskExecutor.submit(task);
           } 

}