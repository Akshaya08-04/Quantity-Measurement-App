package com.bridgelabz.repository;

import com.bridgelabz.entity.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityRepository {
    void save(QuantityMeasurementEntity entity);
    List<QuantityMeasurementEntity> getAllMeasurements();
    List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType);
    List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation);
    int getTotalCount();
    void deleteAll();
}