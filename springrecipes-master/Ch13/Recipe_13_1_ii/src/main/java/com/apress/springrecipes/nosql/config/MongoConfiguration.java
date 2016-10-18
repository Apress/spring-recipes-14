package com.apress.springrecipes.nosql.config;

import com.apress.springrecipes.nosql.MongoDBVehicleRepository;
import com.apress.springrecipes.nosql.VehicleRepository;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

/**
 * Created by marten on 22-09-14.
 */
@Configuration
public class MongoConfiguration {

    public static final String DB_NAME = "vehicledb";

    @Bean
    public Mongo mongo() throws UnknownHostException {
        return new MongoClient();
    }

    @Bean
    public VehicleRepository vehicleRepository(Mongo mongo) {
        return new MongoDBVehicleRepository(mongo, DB_NAME, " vehicles");
    }

}
