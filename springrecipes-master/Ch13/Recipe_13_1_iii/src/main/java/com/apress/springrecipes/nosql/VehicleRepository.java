package com.apress.springrecipes.nosql;


import java.util.List;

public interface VehicleRepository {

    public long count();

    public void save(Vehicle vehicle);

    public void delete(Vehicle vehicle);

    public List<Vehicle> findAll();

    public Vehicle findByVehicleNo(String vehicleNo);

}
