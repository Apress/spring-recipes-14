package com.apress.springrecipes.nosql.config;

import com.apress.springrecipes.nosql.MongoDBVehicleRepository;
import com.apress.springrecipes.nosql.VehicleRepository;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;

/**
 * Created by marten on 22-09-14.
 */
@Configuration
public class MongoConfiguration {

    public static final String DB_NAME = "vehicledb";

    @Bean
    public MongoTemplate mongo() throws Exception {
        return new MongoTemplate(mongoFactoryBean().getObject(), DB_NAME);
    }

    @Bean
    public MongoFactoryBean mongoFactoryBean() {
        return new MongoFactoryBean();
    }


    @Bean
    public VehicleRepository vehicleRepository(MongoTemplate mongo) {
        return new MongoDBVehicleRepository(mongo, "vehicles");
    }

}
