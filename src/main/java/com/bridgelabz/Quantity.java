package com.bridgelabz;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private static final double EPSILON = 0.0001;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    private void validateOperation(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cross-category operation not allowed");

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid numeric value");
    }

    private double roundToTwoDecimal(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    // ================== EQUALITY ==================
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Quantity<?> other)) return false;

        if (!this.unit.getClass().equals(other.unit.getClass()))
            return false;

        double difference =
                Math.abs(this.toBaseUnit() - other.toBaseUnit());

        return difference < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundToTwoDecimal(toBaseUnit()));
    }

    // ================== CONVERSION ==================
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = toBaseUnit();
        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(roundToTwoDecimal(convertedValue), targetUnit);
    }

    // ================== ADDITION ==================
    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateOperation(other);

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double resultBase =
                this.toBaseUnit() + other.toBaseUnit();

        double resultValue =
                targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(roundToTwoDecimal(resultValue), targetUnit);
    }

    // ================== SUBTRACTION ==================
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateOperation(other);

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double resultBase =
                this.toBaseUnit() - other.toBaseUnit();

        double resultValue =
                targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(roundToTwoDecimal(resultValue), targetUnit);
    }

    // ================== DIVISION ==================
    public double divide(Quantity<U> other) {

        validateOperation(other);

        double base2 = other.toBaseUnit();

        if (base2 == 0)
            throw new ArithmeticException("Division by zero");

        return this.toBaseUnit() / base2;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}
