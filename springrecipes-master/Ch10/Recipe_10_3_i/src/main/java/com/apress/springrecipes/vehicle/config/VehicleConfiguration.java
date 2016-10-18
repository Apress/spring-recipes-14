package com.apress.springrecipes.vehicle.config;

import com.apress.springrecipes.vehicle.JdbcVehicleDao;
import com.apress.springrecipes.vehicle.VehicleDao;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.derby.jdbc.ClientDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by marten on 27-05-14.
 */
@Configuration
public class VehicleConfiguration {

    @Bean
    public VehicleDao vehicleDao() {
        return new JdbcVehicleDao(jdbcTemplate());
    }


    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(ClientDriver.class.getName());
        dataSource.setUrl("jdbc:derby://localhost:1527/vehicle;create=true");
        dataSource.setUsername("app");
        dataSource.setPassword("app");
        dataSource.setInitialSize(2);
        dataSource.setMaxTotal(5);
        return dataSource;

    }
}
