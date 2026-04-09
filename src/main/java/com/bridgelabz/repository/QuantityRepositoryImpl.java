package com.bridgelabz.repository;

import com.bridgelabz.entity.QuantityMeasurementEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuantityRepositoryImpl implements IQuantityRepository {

    private final List<QuantityMeasurementEntity> cache = new ArrayList<>();
    private int idCounter = 1;

    @Override
    public void save(QuantityMeasurementEntity entity) {
        entity.setId(idCounter++);
        cache.add(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return new ArrayList<>(cache);
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType) {
        return cache.stream()
                .filter(e -> e.getMeasurementType().equals(measurementType))
                .collect(Collectors.toList());
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {
        return cache.stream()
                .filter(e -> e.getOperation().equals(operation))
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalCount() {
        return cache.size();
    }

    @Override
    public void deleteAll() {
        cache.clear();
    }
}