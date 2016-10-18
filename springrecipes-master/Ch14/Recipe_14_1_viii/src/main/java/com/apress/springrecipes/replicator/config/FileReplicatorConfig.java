package com.apress.springrecipes.replicator.config;

import com.apress.springrecipes.replicator.FileCopier;
import com.apress.springrecipes.replicator.FileCopierJMXImpl;
import com.apress.springrecipes.replicator.FileReplicatorJMXImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;


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
    public FileReplicatorJMXImpl documentReplicator() {
        FileReplicatorJMXImpl fRep = new FileReplicatorJMXImpl();
        fRep.setSrcDir(srcDir);
        fRep.setDestDir(destDir);
        fRep.setFileCopier(fileCopier());
        return fRep;
    }

    @PostConstruct
    public void verifyDirectoriesExist() {
        File src = new File(srcDir);
        File dest = new File(destDir);
        if (!src.exists())
            src.mkdirs();
        if (!dest.exists())
            dest.mkdirs();
    }
}
