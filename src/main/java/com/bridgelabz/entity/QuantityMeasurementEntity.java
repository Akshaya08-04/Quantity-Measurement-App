
package com.bridgelabz.entity;

public class QuantityMeasurementEntity {
    private int id;
    private double value;
    private String unit;
    private String operation;

    public QuantityMeasurementEntity(int id, double value, String unit, String operation) {
        this.id = id;
        this.value = value;
        this.unit = unit;
        this.operation = operation;
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        return "QuantityMeasurementEntity{" +
                "id=" + id +
                ", value=" + value +
                ", unit='" + unit + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}
