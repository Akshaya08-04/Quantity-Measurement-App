package com.bridgelabz;

public enum TemperatureUnit implements IMeasurable {
    CELSIUS,
    FAHRENHEIT,
    KELVIN;

    @Override
    public double toBaseUnit(double value) {
        return switch (this) {
            case CELSIUS -> value;
            case FAHRENHEIT -> (value - 32) * 5 / 9;
            case KELVIN -> value - 273.15;
        };
    }

    @Override
    public double fromBaseUnit(double baseValue) {
        return switch (this) {
            case CELSIUS -> baseValue;
            case FAHRENHEIT -> (baseValue * 9 / 5) + 32;
            case KELVIN -> baseValue + 273.15;
        };
    }
}