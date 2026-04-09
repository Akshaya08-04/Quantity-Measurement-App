package com.bridgelabz.service;

import com.bridgelabz.Quantity;
import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.entity.QuantityMeasurementEntity;
import com.bridgelabz.repository.IQuantityRepository;
import com.bridgelabz.LengthUnit;
import com.bridgelabz.WeightUnit;
import com.bridgelabz.VolumeUnit;
import com.bridgelabz.TemperatureUnit;

import java.util.List;

public class QuantityServiceImpl {

    private final IQuantityRepository repository;

    public QuantityServiceImpl(IQuantityRepository repository) {
        this.repository = repository;
    }

    public boolean compare(QuantityDTO first, QuantityDTO second) {
        validateSameType(first, second);

        boolean result = switch (first.getMeasurementType()) {
            case "LengthUnit" -> new Quantity<>(first.getValue(), LengthUnit.valueOf(first.getUnit()))
                    .equals(new Quantity<>(second.getValue(), LengthUnit.valueOf(second.getUnit())));
            case "WeightUnit" -> new Quantity<>(first.getValue(), WeightUnit.valueOf(first.getUnit()))
                    .equals(new Quantity<>(second.getValue(), WeightUnit.valueOf(second.getUnit())));
            case "VolumeUnit" -> new Quantity<>(first.getValue(), VolumeUnit.valueOf(first.getUnit()))
                    .equals(new Quantity<>(second.getValue(), VolumeUnit.valueOf(second.getUnit())));
            case "TemperatureUnit" -> new Quantity<>(first.getValue(), TemperatureUnit.valueOf(first.getUnit()))
                    .equals(new Quantity<>(second.getValue(), TemperatureUnit.valueOf(second.getUnit())));
            default -> throw new IllegalArgumentException("Unsupported measurement type");
        };

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setThisValue(first.getValue());
        entity.setThisUnit(first.getUnit());
        entity.setThatValue(second.getValue());
        entity.setThatUnit(second.getUnit());
        entity.setMeasurementType(first.getMeasurementType());
        entity.setOperation("COMPARE");
        entity.setResultMessage(result ? "Equal" : "Not Equal");

        repository.save(entity);
        return result;
    }

    public double convert(QuantityDTO source, String targetUnit) {
        double result = switch (source.getMeasurementType()) {
            case "LengthUnit" -> new Quantity<>(source.getValue(), LengthUnit.valueOf(source.getUnit()))
                    .convertTo(LengthUnit.valueOf(targetUnit));
            case "WeightUnit" -> new Quantity<>(source.getValue(), WeightUnit.valueOf(source.getUnit()))
                    .convertTo(WeightUnit.valueOf(targetUnit));
            case "VolumeUnit" -> new Quantity<>(source.getValue(), VolumeUnit.valueOf(source.getUnit()))
                    .convertTo(VolumeUnit.valueOf(targetUnit));
            case "TemperatureUnit" -> new Quantity<>(source.getValue(), TemperatureUnit.valueOf(source.getUnit()))
                    .convertTo(TemperatureUnit.valueOf(targetUnit));
            default -> throw new IllegalArgumentException("Unsupported measurement type");
        };

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setThisValue(source.getValue());
        entity.setThisUnit(source.getUnit());
        entity.setThatValue(0);
        entity.setThatUnit(targetUnit);
        entity.setMeasurementType(source.getMeasurementType());
        entity.setOperation("CONVERT");
        entity.setResultValue(result);
        entity.setResultUnit(targetUnit);
        entity.setResultMessage("Converted Successfully");

        repository.save(entity);
        return result;
    }

    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return repository.getAllMeasurements();
    }

    public List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType) {
        return repository.getMeasurementsByType(measurementType);
    }

    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {
        return repository.getMeasurementsByOperation(operation);
    }

    public int getTotalCount() {
        return repository.getTotalCount();
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    private void validateSameType(QuantityDTO first, QuantityDTO second) {
        if (!first.getMeasurementType().equals(second.getMeasurementType())) {
            throw new IllegalArgumentException("Measurement types do not match");
        }
    }
}