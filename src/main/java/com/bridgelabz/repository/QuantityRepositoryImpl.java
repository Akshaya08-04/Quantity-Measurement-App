package com.bridgelabz.repository;

import com.bridgelabz.entity.QuantityMeasurementEntity;
import java.util.ArrayList;
import java.util.List;

public class QuantityRepositoryImpl implements IQuantityRepository {
    private final List<QuantityMeasurementEntity> records = new ArrayList<>();

    @Override
    public void save(QuantityMeasurementEntity entity) {
        records.add(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {
        return records;
    }
}