package com.apress.springrecipes.caching;

import com.apress.springrecipes.caching.config.CustomerConfiguration;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.util.StopWatch;

/**
 * Created by marten on 15-08-14.
 */
public class Main {

    public static final void main(String[] args) throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(CustomerConfiguration.class);
        CustomerRepository customerRepository = context.getBean(CustomerRepository.class);
        StopWatch sw = new StopWatch("Cache Evict and Put");


        sw.start("Get 'Unknown Customer'");
        Customer customer = customerRepository.find(1L);
        System.out.println("Get 'Unknown Customer' (result) : " + ToStringBuilder.reflectionToString(customer, ToStringStyle.SHORT_PREFIX_STYLE));
        sw.stop();

        sw.start("Create New Customer");
        customer = customerRepository.create("Marten Deinum");
        System.out.println("Create new Customer (result) : " + ToStringBuilder.reflectionToString(customer, ToStringStyle.SHORT_PREFIX_STYLE));
        sw.stop();

        long customerId = customer.getId();

        sw.start("Get 'New Customer 1'");
        customer = customerRepository.find(customerId);
        System.out.println("Get 'New Customer 1' (result) : " + ToStringBuilder.reflectionToString(customer, ToStringStyle.SHORT_PREFIX_STYLE));
        sw.stop();

        sw.start("Get 'New Customer 2'");
        customer = customerRepository.find(customerId);
        System.out.println("Get 'New Customer 2' (result) : " + ToStringBuilder.reflectionToString(customer, ToStringStyle.SHORT_PREFIX_STYLE));
        sw.stop();


        sw.start("Update Customer");
        customer.setName("Josh Long");
        customerRepository.update(customer);
        sw.stop();

        sw.start("Get 'Updated Customer 1'");
        customer = customerRepository.find(customerId);
        System.out.println("Get 'Updated Customer 1' (result) : " + ToStringBuilder.reflectionToString(customer, ToStringStyle.SHORT_PREFIX_STYLE));
        sw.stop();

        sw.start("Get 'Updated Customer 2'");
        customer = customerRepository.find(customerId);
        System.out.println("Get 'Updated Customer 2' (result) : " + ToStringBuilder.reflectionToString(customer, ToStringStyle.SHORT_PREFIX_STYLE));
        sw.stop();

        sw.start("Remove Customer");
        customerRepository.remove(customer.getId());
        sw.stop();

        sw.start("Get 'Deleted Customer 1'");
        customer = customerRepository.find(customerId);
        System.out.println("Get 'Deleted Customer 1' (result) : " + ToStringBuilder.reflectionToString(customer, ToStringStyle.SHORT_PREFIX_STYLE));
        sw.stop();

        sw.start("Get 'Deleted Customer 2'");
        customer = customerRepository.find(customerId);
        System.out.println("Get 'Deleted Customer 2' (result) : " + ToStringBuilder.reflectionToString(customer, ToStringStyle.SHORT_PREFIX_STYLE));
        sw.stop();


        System.out.println();
        System.out.println(sw.prettyPrint());

        ((AbstractApplicationContext) context).close();
    }
}
