package com.bridgelabz.model;

import com.bridgelabz.IMeasurable;

public class QuantityModel<T extends Enum<T> & IMeasurable> {
    private double value;
    private T unit;

    public QuantityModel(double value, T unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public T getUnit() {
        return unit;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setUnit(T unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "QuantityModel{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }
}
