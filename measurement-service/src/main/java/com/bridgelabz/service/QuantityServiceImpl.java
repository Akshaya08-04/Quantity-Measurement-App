package com.bridgelabz.service;

import com.bridgelabz.*;
import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.dto.QuantityRequestDTO;
import com.bridgelabz.entity.QuantityMeasurementEntity;
import com.bridgelabz.exceptions.QuantityMeasurementException;
import com.bridgelabz.repository.IQuantityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityServiceImpl implements IQuantityService {

    private final IQuantityRepository repository;

    public QuantityServiceImpl(IQuantityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Object performOperation(QuantityRequestDTO request) {
        String operation = request.getOperationType().toUpperCase();

        return switch (operation) {
            case "COMPARE" -> compare(request.getFirst(), request.getSecond());
            case "CONVERT" -> convert(request.getFirst(), request.getTargetUnit());
            case "ADD" -> add(request.getFirst(), request.getSecond(), request.getTargetUnit());
            default -> throw new QuantityMeasurementException("Unsupported operation type: " + operation);
        };
    }

    private Boolean compare(QuantityDTO first, QuantityDTO second) {
        validateSecondQuantity(second);
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
            default -> throw new QuantityMeasurementException("Unsupported measurement type");
        };

        saveHistory(first, second, "COMPARE", result ? 1.0 : 0.0, "BOOLEAN");
        return result;
    }

    private Double convert(QuantityDTO first, String targetUnit) {
        if (targetUnit == null || targetUnit.isBlank()) {
            throw new QuantityMeasurementException("Target unit is required for conversion");
        }

        double result = switch (first.getMeasurementType()) {
            case "LengthUnit" -> new Quantity<>(first.getValue(), LengthUnit.valueOf(first.getUnit()))
                    .convertTo(LengthUnit.valueOf(targetUnit));
            case "WeightUnit" -> new Quantity<>(first.getValue(), WeightUnit.valueOf(first.getUnit()))
                    .convertTo(WeightUnit.valueOf(targetUnit));
            case "VolumeUnit" -> new Quantity<>(first.getValue(), VolumeUnit.valueOf(first.getUnit()))
                    .convertTo(VolumeUnit.valueOf(targetUnit));
            case "TemperatureUnit" -> new Quantity<>(first.getValue(), TemperatureUnit.valueOf(first.getUnit()))
                    .convertTo(TemperatureUnit.valueOf(targetUnit));
            default -> throw new QuantityMeasurementException("Unsupported measurement type");
        };

        saveHistory(first, null, "CONVERT", result, targetUnit);
        return result;
    }

    private Double add(QuantityDTO first, QuantityDTO second, String targetUnit) {
        validateSecondQuantity(second);
        validateSameType(first, second);

        if ("TemperatureUnit".equals(first.getMeasurementType())) {
            throw new QuantityMeasurementException("Addition is not supported for temperature");
        }

        if (targetUnit == null || targetUnit.isBlank()) {
            throw new QuantityMeasurementException("Target unit is required for addition");
        }

        double result = switch (first.getMeasurementType()) {
            case "LengthUnit" -> {
                Quantity<LengthUnit> q1 = new Quantity<>(first.getValue(), LengthUnit.valueOf(first.getUnit()));
                Quantity<LengthUnit> q2 = new Quantity<>(second.getValue(), LengthUnit.valueOf(second.getUnit()));
                double totalBase = q1.toBaseUnit() + q2.toBaseUnit();
                yield LengthUnit.valueOf(targetUnit).fromBaseUnit(totalBase);
            }
            case "WeightUnit" -> {
                Quantity<WeightUnit> q1 = new Quantity<>(first.getValue(), WeightUnit.valueOf(first.getUnit()));
                Quantity<WeightUnit> q2 = new Quantity<>(second.getValue(), WeightUnit.valueOf(second.getUnit()));
                double totalBase = q1.toBaseUnit() + q2.toBaseUnit();
                yield WeightUnit.valueOf(targetUnit).fromBaseUnit(totalBase);
            }
            case "VolumeUnit" -> {
                Quantity<VolumeUnit> q1 = new Quantity<>(first.getValue(), VolumeUnit.valueOf(first.getUnit()));
                Quantity<VolumeUnit> q2 = new Quantity<>(second.getValue(), VolumeUnit.valueOf(second.getUnit()));
                double totalBase = q1.toBaseUnit() + q2.toBaseUnit();
                yield VolumeUnit.valueOf(targetUnit).fromBaseUnit(totalBase);
            }
            default -> throw new QuantityMeasurementException("Unsupported measurement type");
        };

        saveHistory(first, second, "ADD", result, targetUnit);
        return result;
    }

    @Override
    public List<QuantityMeasurementEntity> getHistory() {
        return repository.findAll();
    }

    @Override
    public List<QuantityMeasurementEntity> getHistoryByOperation(String operationType) {
        return repository.findByOperationType(operationType.toUpperCase());
    }

    @Override
    public long getOperationCount(String operationType) {
        return repository.countByOperationType(operationType.toUpperCase());
    }

    private void validateSameType(QuantityDTO first, QuantityDTO second) {
        if (!first.getMeasurementType().equals(second.getMeasurementType())) {
            throw new QuantityMeasurementException("Measurement types must be the same");
        }
    }

    private void validateSecondQuantity(QuantityDTO second) {
        if (second == null) {
            throw new QuantityMeasurementException("Second quantity is required for this operation");
        }
    }

    private void saveHistory(QuantityDTO first, QuantityDTO second, String operationType,
                             Double resultValue, String resultUnit) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue(first.getValue());
        entity.setUnit(first.getUnit());
        entity.setMeasurementType(first.getMeasurementType());
        entity.setOperationType(operationType);
        entity.setResultValue(resultValue);
        entity.setResultUnit(resultUnit);

        if (second != null) {
            entity.setSecondValue(second.getValue());
            entity.setSecondUnit(second.getUnit());
        }

        repository.save(entity);
    }
}