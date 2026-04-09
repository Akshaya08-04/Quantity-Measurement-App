package com.bridgelabz.controller;

import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.entity.QuantityMeasurementEntity;
import com.bridgelabz.service.QuantityServiceImpl;

import java.util.List;

public class QuantityMeasurementController {

    private final QuantityServiceImpl service;

    public QuantityMeasurementController(QuantityServiceImpl service) {
        this.service = service;
    }

    public boolean performComparison(QuantityDTO first, QuantityDTO second) {
        return service.compare(first, second);
    }

    public double performConversion(QuantityDTO source, String targetUnit) {
        return service.convert(source, targetUnit);
    }

    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return service.getAllMeasurements();
    }

    public List<QuantityMeasurementEntity> getMeasurementsByType(String type) {
        return service.getMeasurementsByType(type);
    }

    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {
        return service.getMeasurementsByOperation(operation);
    }

    public int getTotalCount() {
        return service.getTotalCount();
    }

    public void deleteAllMeasurements() {
        service.deleteAll();
    }
}