package com.bridgelabz;

public enum WeightUnit implements IMeasurable, SupportsArithmetic {

    KILOGRAM(1000.0),
    GRAM(1.0),
    POUND(453.592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    @Override
    public double toBaseUnit(double value) {
        return value * factor;
    }

    @Override
    public double fromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    @Override
    public String getUnitName() {
        return name();
    }
}