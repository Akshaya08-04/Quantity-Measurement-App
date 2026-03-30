package com.bridgelabz.controller;

import com.bridgelabz.IMeasurable;
import com.bridgelabz.Quantity;
import com.bridgelabz.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public <T extends Enum<T> & IMeasurable> boolean compareQuantities(Quantity<T> q1, Quantity<T> q2) {
        return service.compare(q1, q2);
    }

    public <T extends Enum<T> & IMeasurable> Quantity<T> convertQuantity(Quantity<T> quantity, T targetUnit) {
        return service.convert(quantity, targetUnit);
    }

    public <T extends Enum<T> & IMeasurable> Quantity<T> addQuantities(Quantity<T> q1, Quantity<T> q2) {
        return service.add(q1, q2);
    }

    public <T extends Enum<T> & IMeasurable> Quantity<T> subtractQuantities(Quantity<T> q1, Quantity<T> q2) {
        return service.subtract(q1, q2);
    }

    public void displayAllRecords() {
        System.out.println(service.getAllRecords());
    }
}
