package com.apress.springrecipes.executors;

import java.util.Date;
import org.apache.commons.lang.exception.ExceptionUtils;

public class DemonstrationRunnable implements Runnable {
  public void run() {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          System.out.println(
                ExceptionUtils.getFullStackTrace(e));
        }
        System.out.println(Thread.currentThread().getName());
        System.out.printf("Hello at %s \n", new Date());
  }
}