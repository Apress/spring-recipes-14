package com.apress.springrecipes.springintegration;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.integration.annotation.Splitter;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Collection;


public class CustomerBatchFileSplitter {

    @Splitter
    public Collection<String> splitAFile(File file) throws Throwable {
        System.out.println("Reading " + file.getAbsolutePath() + "...");

        Reader reader = new FileReader(file);
        Collection<String> lines = IOUtils.readLines(reader);
        IOUtils.closeQuietly(reader);
        System.out.println("Split " + StringUtils.join(lines.iterator(), " / "));

        return lines;
    }
}
