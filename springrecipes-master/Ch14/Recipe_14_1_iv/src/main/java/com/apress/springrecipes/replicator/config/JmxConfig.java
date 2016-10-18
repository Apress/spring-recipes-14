package com.apress.springrecipes.replicator.config;

import com.apress.springrecipes.replicator.FileReplicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.assembler.MBeanInfoAssembler;
import org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by marten on 21-05-14.
 */
@Configuration
public class JmxConfig {

    @Autowired
    private FileReplicator fileReplicator;

    @Bean
    public MBeanExporter mbeanExporter() {
        MBeanExporter mbeanExporter = new MBeanExporter();
        mbeanExporter.setBeans(beansToExport());
        mbeanExporter.setAssembler(assembler());
        return mbeanExporter;
    }

    private Map<String, Object> beansToExport() {
        Map<String, Object> beansToExport = new HashMap<>();
        beansToExport.put("bean:name=documentReplicator", fileReplicator);
        return beansToExport;
    }

    @Bean
    public MBeanInfoAssembler assembler() {
        MethodNameBasedMBeanInfoAssembler assembler = new MethodNameBasedMBeanInfoAssembler();
        assembler.setManagedMethods(new String[] {"getSrcDir","setSrcDir","getDestDir","setDestDir","replicate"});
        return assembler;
    }


}

