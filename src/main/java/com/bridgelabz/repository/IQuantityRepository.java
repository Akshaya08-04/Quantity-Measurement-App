package com.bridgelabz.repository;

import com.bridgelabz.entity.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityRepository {
    void save(QuantityMeasurementEntity entity);
    List<QuantityMeasurementEntity> findAll();
}