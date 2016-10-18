package com.apress.springrecipes.vehicle;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcVehicleDao extends NamedParameterJdbcDaoSupport implements VehicleDao {


    @Override
    public void insert(final Vehicle vehicle) {
        String sql = "INSERT INTO VEHICLE (VEHICLE_NO, COLOR, WHEEL, SEAT) VALUES (:vehicleNo, :color, :wheel, :seat)";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("vehicleNo", vehicle.getVehicleNo());
        parameters.put("color", vehicle.getColor());
        parameters.put("wheel", vehicle.getWheel());
        parameters.put("seat", vehicle.getSeat());

        SqlParameterSource parameterSource = new MapSqlParameterSource(parameters);

        getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }

    @Override
    public void insertBatch(final List<Vehicle> vehicles) {
        String sql = "INSERT INTO VEHICLE (VEHICLE_NO, COLOR, WHEEL, SEAT) VALUES (?, ?, ?, ?)";
        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {

            public int getBatchSize() {
                return vehicles.size();
            }

            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Vehicle vehicle = vehicles.get(i);
                ps.setString(1, vehicle.getVehicleNo());
                ps.setString(2, vehicle.getColor());
                ps.setInt(3, vehicle.getWheel());
                ps.setInt(4, vehicle.getSeat());
            }
        });
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        String sql = "SELECT * FROM VEHICLE WHERE VEHICLE_NO = ?";
        return getJdbcTemplate().queryForObject(sql, BeanPropertyRowMapper.newInstance(Vehicle.class), vehicleNo);
    }

    @Override
    public void update(Vehicle vehicle) {
        String sql = "UPDATE VEHICLE SET COLOR=?,WHEEL=?,SEAT=? WHERE VEHICLE_NO=?";
        getJdbcTemplate().update(sql, vehicle.getColor(), vehicle.getWheel(), vehicle.getSeat(), vehicle.getVehicleNo());
    }

    @Override
    public void delete(Vehicle vehicle) {
        String sql = "DELETE FROM VEHICLE WHERE VEHICLE_NO=?";
        getJdbcTemplate().update(sql, vehicle.getVehicleNo());
    }

    @Override
    public List<Vehicle> findAll() {
        String sql = "SELECT * FROM VEHICLE";
        return getJdbcTemplate().query(sql, BeanPropertyRowMapper.newInstance(Vehicle.class));
    }
}
