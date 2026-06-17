package com.bridgelabz;

public enum VolumeUnit implements IMeasurable, SupportsArithmetic {

    LITRE(1000.0),
    MILLILITRE(1.0),
    GALLON(3785.41);

    private final double factor;

    VolumeUnit(double factor) {
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