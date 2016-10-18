package com.apress.springrecipes.vehicle.config;

import com.apress.springrecipes.vehicle.JdbcVehicleDao;
import com.apress.springrecipes.vehicle.VehicleDao;
import org.apache.derby.jdbc.ClientDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * Created by marten on 27-05-14.
 */
@Configuration
public class VehicleConfiguration {

    @Bean
    public VehicleDao vehicleDao() {
        return new JdbcVehicleDao(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(ClientDriver.class);
        dataSource.setUrl("jdbc:derby://localhost:1527/vehicle;create=true");
        dataSource.setUsername("app");
        dataSource.setPassword("app");
        return dataSource;

    }
}
