package com.apress.springrecipes.vehicle.config;

import com.apress.springrecipes.vehicle.JdbcVehicleDao;
import com.apress.springrecipes.vehicle.VehicleDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

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
        return new JndiDataSourceLookup().getDataSource("jdbc/VehicleDS");
    }
}
