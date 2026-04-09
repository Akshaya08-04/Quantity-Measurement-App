package com.bridgelabz.repository;

import com.bridgelabz.entity.QuantityMeasurementEntity;
import com.bridgelabz.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityDatabaseRepositoryImpl implements IQuantityRepository {

    @Override
    public void save(QuantityMeasurementEntity entity) {
        String sql = "INSERT INTO quantity_measurement_history " +
                "(this_value, this_unit, that_value, that_unit, measurement_type, operation, result_value, result_unit, result_message) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDouble(1, entity.getThisValue());
            preparedStatement.setString(2, entity.getThisUnit());
            preparedStatement.setDouble(3, entity.getThatValue());
            preparedStatement.setString(4, entity.getThatUnit());
            preparedStatement.setString(5, entity.getMeasurementType());
            preparedStatement.setString(6, entity.getOperation());

            if (entity.getResultValue() != null) {
                preparedStatement.setDouble(7, entity.getResultValue());
            } else {
                preparedStatement.setNull(7, Types.DOUBLE);
            }

            preparedStatement.setString(8, entity.getResultUnit());
            preparedStatement.setString(9, entity.getResultMessage());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving quantity measurement", e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        String sql = "SELECT * FROM quantity_measurement_history";
        return getMeasurementsFromQuery(sql, null);
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType) {
        String sql = "SELECT * FROM quantity_measurement_history WHERE measurement_type = ?";
        return getMeasurementsFromQuery(sql, measurementType);
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {
        String sql = "SELECT * FROM quantity_measurement_history WHERE operation = ?";
        return getMeasurementsFromQuery(sql, operation);
    }

    @Override
    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM quantity_measurement_history";

        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error getting total count", e);
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM quantity_measurement_history";

        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting all measurements", e);
        }
    }

    private List<QuantityMeasurementEntity> getMeasurementsFromQuery(String sql, String param) {
        List<QuantityMeasurementEntity> list = new ArrayList<>();

        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            if (param != null) {
                preparedStatement.setString(1, param);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
                    entity.setId(resultSet.getInt("id"));
                    entity.setThisValue(resultSet.getDouble("this_value"));
                    entity.setThisUnit(resultSet.getString("this_unit"));
                    entity.setThatValue(resultSet.getDouble("that_value"));
                    entity.setThatUnit(resultSet.getString("that_unit"));
                    entity.setMeasurementType(resultSet.getString("measurement_type"));
                    entity.setOperation(resultSet.getString("operation"));

                    double resultValue = resultSet.getDouble("result_value");
                    if (!resultSet.wasNull()) {
                        entity.setResultValue(resultValue);
                    }

                    entity.setResultUnit(resultSet.getString("result_unit"));
                    entity.setResultMessage(resultSet.getString("result_message"));

                    list.add(entity);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching measurements", e);
        }

        return list;
    }
}