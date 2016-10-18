package com.apress.springrecipes.nosql;

import com.mongodb.MongoClient;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * Created by marten on 22-09-14.
 */
public class Main {

    public static final String DB_NAME = "vehicledb";

    public static void main(String[] args) throws Exception {
        // Default monogclient for localhost and port 27017
        MongoClient mongo = new MongoClient();

        VehicleRepository repository = new MongoDBVehicleRepository(mongo, DB_NAME, "vehicles");

        System.out.println("Number of Vehicles: " + repository.count());

        repository.save(new Vehicle("TEM0001", "RED", 4, 4));
        repository.save(new Vehicle("TEM0002", "RED", 4, 4));

        System.out.println("Number of Vehicles: " + repository.count());

        Vehicle v = repository.findByVehicleNo("TEM0001");

        System.out.println(ToStringBuilder.reflectionToString(v, ToStringStyle.SHORT_PREFIX_STYLE));

        List<Vehicle> vehicleList = repository.findAll();

        System.out.println("Number of Vehicles: " + vehicleList.size());

        for (Vehicle vehicle : vehicleList) {
            repository.delete(vehicle);
        }

        System.out.println("Number of Vehicles: " + repository.count());

        // Cleanup and close
        mongo.getDB(DB_NAME).dropDatabase();
        mongo.close();


    }
}
