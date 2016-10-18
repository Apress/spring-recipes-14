package com.apress.springrecipes.shop;

import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

import java.util.Date;

import org.springframework.beans.factory.BeanNameAware;

public class Cashier implements BeanNameAware {

    private String beanName;
    private String path;
    private BufferedWriter writer;

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void openFile() throws IOException {
	File targetDir = new File(path);
	if (!targetDir.exists()) { 
	    targetDir.mkdir();
	}
        File checkoutFile = new File(path, beanName + ".txt");
	if(!checkoutFile.exists()) {
	    checkoutFile.createNewFile();
	}
        writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(checkoutFile, true)));
    }

    public void checkout(ShoppingCart cart) throws IOException {
        writer.write(new Date() + "\t" +cart.getItems() + "\r\n");
        writer.flush();
    }

    public void closeFile() throws IOException {
        writer.close();
    }

        

}
