package com.bridgelabz;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS,
    FAHRENHEIT;

    @Override
    public double toBaseUnit(double value) {
        if (this == CELSIUS) return value;
        return (value - 32) * 5 / 9;
    }

    @Override
    public double fromBaseUnit(double baseValue) {
        if (this == CELSIUS) return baseValue;
        return (baseValue * 9 / 5) + 32;
    }

    @Override
    public String getUnitName() {
        return name();
    }
}
