package com.apress.springrecipes.nosql;

import com.mongodb.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marten on 22-09-14.
 */
public class MongoDBVehicleRepository implements VehicleRepository {

    private final Mongo mongo;
    private final String collectionName;
    private final String databaseName;

    public MongoDBVehicleRepository(Mongo mongo, String databaseName, String collectionName) {
        this.mongo = mongo;
        this.databaseName=databaseName;
        this.collectionName = collectionName;
    }

    @Override
    public long count() {
        return getCollection().count();
    }

    @Override
    public void save(Vehicle vehicle) {
        BasicDBObject query = new BasicDBObject("vehicleNo", vehicle.getVehicleNo());
        DBObject dbVehicle = transform(vehicle);

        DBObject fromDB = getCollection().findAndModify(query, dbVehicle);
        if (fromDB == null) {
            getCollection().insert(dbVehicle);
        }
    }

    @Override
    public void delete(Vehicle vehicle) {
        BasicDBObject query = new BasicDBObject("vehicleNo", vehicle.getVehicleNo());
        getCollection().remove(query);
    }

    @Override
    public List<Vehicle> findAll() {
        DBCursor cursor = getCollection().find(null);
        List<Vehicle> vehicles = new ArrayList<>(cursor.size());
        for (DBObject dbObject : cursor) {
            vehicles.add(transform(dbObject));
        }
        return vehicles;
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        BasicDBObject query = new BasicDBObject("vehicleNo", vehicleNo);
        DBObject dbVehicle = getCollection().findOne(query);
        return transform(dbVehicle);
    }

    private DBCollection getCollection() {
        return mongo.getDB(databaseName).getCollection(collectionName);
    }

    /**
     * Transform a {@code DBObject} into a {@code Vehicle}
     *
     * @param dbVehicle the dbobject
     * @return newly constructed Vehicle instance
     */

    private Vehicle transform(DBObject dbVehicle) {
        return new Vehicle(
                (String) dbVehicle.get("vehicleNo"),
                (String) dbVehicle.get("color"),
                (int) dbVehicle.get("wheel"),
                (int) dbVehicle.get("seat"));
    }

    /**
     * Transform a {@code Vehicle} into a {@DBObject} for storage or querying.
     *
     * @param vehicle the vehicle
     * @return DBObject based on the Vehicle properties
     */
    private DBObject transform(Vehicle vehicle) {
        BasicDBObject dbVehicle = new BasicDBObject("vehicleNo", vehicle.getVehicleNo())
                .append("color", vehicle.getColor())
                .append("wheel", vehicle.getWheel())
                .append("seat", vehicle.getSeat());
        return dbVehicle;
    }
}
