package com.apress.springrecipes.vehicle;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcVehicleDao implements VehicleDao {

    private final DataSource dataSource;

    public JdbcVehicleDao(DataSource dataSource) {
        this.dataSource=dataSource;
    }


    public void insert(Vehicle vehicle) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
        jdbcTemplate.update(new InsertVehicleStatementCreator(vehicle));
    }

    public Vehicle findByVehicleNo(String vehicleNo) {
        String sql = "SELECT * FROM VEHICLE WHERE VEHICLE_NO = ?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, vehicleNo);

            Vehicle vehicle = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                vehicle = new Vehicle(rs.getString("VEHICLE_NO"),
                        rs.getString("COLOR"), rs.getInt("WHEEL"),
                        rs.getInt("SEAT"));
            }
            rs.close();
            ps.close();
            return vehicle;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    public void update(Vehicle vehicle) {
        String sql = "UPDATE VEHICLE SET COLOR=?,WHEEL=?,SEAT=? WHERE VEHICLE_NO=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, vehicle.getColor());
            ps.setInt(2, vehicle.getWheel());
            ps.setInt(3, vehicle.getSeat());
            ps.setString(4, vehicle.getVehicleNo());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }

    }

    public void delete(Vehicle vehicle) {
        String sql = "DELETE FROM VEHICLE WHERE VEHICLE_NO=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, vehicle.getVehicleNo());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }

    }
}
