package com.bridgelabz.service;

import com.bridgelabz.IMeasurable;
import com.bridgelabz.Quantity;
import com.bridgelabz.entity.QuantityMeasurementEntity;
import com.bridgelabz.exceptions.QuantityMeasurementException;
import com.bridgelabz.repository.IQuantityRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final IQuantityRepository repository;
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public QuantityMeasurementServiceImpl(IQuantityRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends Enum<T> & IMeasurable> boolean compare(Quantity<T> first, Quantity<T> second) {
        if (first == null || second == null) {
            throw new QuantityMeasurementException("Quantities cannot be null");
        }

        boolean result = first.equals(second);

        repository.save(new QuantityMeasurementEntity(
                idGenerator.getAndIncrement(),
                first.getValue(),
                first.getUnit().name(),
                "COMPARE"
        ));

        return result;
    }

    @Override
    public <T extends Enum<T> & IMeasurable> Quantity<T> convert(Quantity<T> quantity, T targetUnit) {
        if (quantity == null || targetUnit == null) {
            throw new QuantityMeasurementException("Quantity and target unit cannot be null");
        }

        double convertedValue = quantity.convertTo(targetUnit);
        Quantity<T> converted = new Quantity<>(convertedValue, targetUnit);

        repository.save(new QuantityMeasurementEntity(
                idGenerator.getAndIncrement(),
                converted.getValue(),
                converted.getUnit().name(),
                "CONVERT"
        ));

        return converted;
    }

    @Override
    public <T extends Enum<T> & IMeasurable> Quantity<T> add(Quantity<T> first, Quantity<T> second) {
        if (first == null || second == null) {
            throw new QuantityMeasurementException("Quantities cannot be null");
        }

        Quantity<T> result = first.add(second, first.getUnit());

        repository.save(new QuantityMeasurementEntity(
                idGenerator.getAndIncrement(),
                result.getValue(),
                result.getUnit().name(),
                "ADD"
        ));

        return result;
    }

    @Override
    public <T extends Enum<T> & IMeasurable> Quantity<T> subtract(Quantity<T> first, Quantity<T> second) {
        if (first == null || second == null) {
            throw new QuantityMeasurementException("Quantities cannot be null");
        }

        Quantity<T> result = first.subtract(second, first.getUnit());

        repository.save(new QuantityMeasurementEntity(
                idGenerator.getAndIncrement(),
                result.getValue(),
                result.getUnit().name(),
                "SUBTRACT"
        ));

        return result;
    }

    @Override
    public List<?> getAllRecords() {
        return repository.findAll();
    }
}