package com.apress.springrecipes.replicator.config;

import com.apress.springrecipes.replicator.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.export.NotificationListenerBean;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;

import javax.management.NotificationListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class FileReplicatorConfig {
    
    @Value("#{systemProperties['user.home']}/docs")
    private String srcDir;
    @Value("#{systemProperties['user.home']}/docs_backup")    
    private String destDir;    

    @Bean
    public FileCopier fileCopier() { 
	FileCopier fCop = new FileCopierJMXImpl();
	return fCop;
    }

    @Bean           
    public FileReplicator documentReplicator() {
	FileReplicator fRep = new FileReplicatorJMXImpl();       
	verifyDirectoriesExist();
	fRep.setSrcDir(srcDir);
	fRep.setDestDir(destDir);
        fRep.setFileCopier(fileCopier());
	return fRep;
    }

    @Bean
    public AnnotationMBeanExporter mbeanExporter() {
        AnnotationMBeanExporter mbeanExporter = new AnnotationMBeanExporter();
        mbeanExporter.setDefaultDomain("bean");
        mbeanExporter.setNotificationListenerMappings(notificationMappings());
        return mbeanExporter;
    }

    public Map<String, NotificationListener> notificationMappings() {
        Map<String, NotificationListener> mappings = new HashMap<>();
        mappings.put("documentReplicator", new ReplicationNotificationListener());
        return mappings;
    }

    private void verifyDirectoriesExist() {
        File src = new File(srcDir);
        File dest = new File(destDir);
        if (!src.exists())
	    src.mkdirs();
        if (!dest.exists())
            dest.mkdirs();
    }    
}
