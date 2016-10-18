package com.apress.springrecipes.replicator.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

import javax.management.MBeanServerConnection;
import java.net.MalformedURLException;

/**
 * Created by marten on 26-05-14.
 */
@Configuration
public class JmxClientConfiguration {

    @Bean
    public FactoryBean<MBeanServerConnection> mbeanServerConnection() throws MalformedURLException {
        MBeanServerConnectionFactoryBean mBeanServerConnectionFactoryBean = new MBeanServerConnectionFactoryBean();
        mBeanServerConnectionFactoryBean.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/replicator");
        return mBeanServerConnectionFactoryBean;
    }
}
