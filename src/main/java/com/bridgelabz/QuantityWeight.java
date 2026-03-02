package com.bridgelabz;

public class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    private static final double EPSILON = 0.0001;

    public QuantityWeight(double value, WeightUnit unit) {

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

    public WeightUnit getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        QuantityWeight other = (QuantityWeight) obj;

        double difference =
                Math.abs(this.toBaseUnit() - other.toBaseUnit());

        return difference < EPSILON;
    }

    @Override
    public int hashCode() {
        long baseBits =
                Double.doubleToLongBits(toBaseUnit());
        return (int) (baseBits ^ (baseBits >>> 32));
    }

    public QuantityWeight convertTo(WeightUnit target) {

        if (target == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = toBaseUnit();

        double convertedValue =
                target.convertFromBaseUnit(baseValue);

        return new QuantityWeight(convertedValue, target);
    }

    // Default addition (result in same unit as first object)
    public QuantityWeight add(QuantityWeight other) {

        if (other == null)
            throw new IllegalArgumentException("Weight cannot be null");

        return add(other, this.unit);
    }

    // Addition with target unit
    public QuantityWeight add(QuantityWeight other,
                              WeightUnit targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Weight cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sumBase =
                this.toBaseUnit() + other.toBaseUnit();

        double resultValue =
                targetUnit.convertFromBaseUnit(sumBase);

        return new QuantityWeight(resultValue, targetUnit);
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}