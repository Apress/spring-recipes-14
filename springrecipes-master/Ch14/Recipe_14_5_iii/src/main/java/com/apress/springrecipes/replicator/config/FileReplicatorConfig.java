package com.apress.springrecipes.replicator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import com.apress.springrecipes.replicator.FileCopier;
import com.apress.springrecipes.replicator.FileCopierImpl;
import com.apress.springrecipes.replicator.FileReplicator;
import com.apress.springrecipes.replicator.FileReplicatorImpl;

import java.io.File;


@Configuration
public class FileReplicatorConfig {
    
    @Value("#{systemProperties['user.home']}/docs")
    private String srcDir;
    @Value("#{systemProperties['user.home']}/docs_backup")    
    private String destDir;    

    @Bean
    public FileCopier fileCopier() { 
	FileCopier fCop = new FileCopierImpl();
	return fCop;
    }

    @Bean           
    public FileReplicator documentReplicator() {
	FileReplicator fRep = new FileReplicatorImpl();       
	verifyDirectoriesExist();
	fRep.setSrcDir(srcDir);
	fRep.setDestDir(destDir);
        fRep.setFileCopier(fileCopier());
	return fRep;
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
