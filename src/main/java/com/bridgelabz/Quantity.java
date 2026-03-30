package com.bridgelabz;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double toBase() {
        return unit.toBaseUnit(value);
    }

    public double convertTo(U targetUnit) {
        double base = toBase();
        return targetUnit.fromBaseUnit(base);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        checkArithmetic();
        double sum = this.toBase() + other.toBase();
        return new Quantity<>(targetUnit.fromBaseUnit(sum), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        checkArithmetic();
        double diff = this.toBase() - other.toBase();
        return new Quantity<>(targetUnit.fromBaseUnit(diff), targetUnit);
    }

    public double divide(Quantity<U> other, U targetUnit) {
        checkArithmetic();
        double val1 = this.convertTo(targetUnit);
        double val2 = other.convertTo(targetUnit);
        return val1 / val2;
    }

    private void checkArithmetic() {
        if (!(unit instanceof SupportsArithmetic)) {
            throw new UnsupportedOperationException("Arithmetic not supported for this unit");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;

        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBase());
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}