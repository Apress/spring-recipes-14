package com.apress.springrecipes.vehicle;

import java.util.List;


public interface VehicleDao {
    public void insert(Vehicle vehicle);

    public void update(Vehicle vehicle);

    public void delete(Vehicle vehicle);

    public Vehicle findByVehicleNo(String vehicleNo);


}
