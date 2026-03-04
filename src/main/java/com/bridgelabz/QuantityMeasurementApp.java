package com.bridgelabz;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable> void demonstrateEquality(
            Quantity<U> q1, Quantity<U> q2) {
        System.out.println(q1 + " equals " + q2 + " ? " + q1.equals(q2));
    }

    public static <U extends IMeasurable> void demonstrateConversion(
            Quantity<U> quantity, U targetUnit) {
        System.out.println("Converted: " + quantity.convertTo(targetUnit));
    }

    public static <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        System.out.println("Sum: " + q1.add(q2, targetUnit));
    }

    public static <U extends IMeasurable> void demonstrateSubtraction(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        System.out.println("Difference: " + q1.subtract(q2, targetUnit));
    }

    public static <U extends IMeasurable> void demonstrateDivision(
            Quantity<U> q1, Quantity<U> q2) {
        System.out.println("Ratio: " + q1.divide(q2));
    }

    public static void main(String[] args) {

        System.out.println("===== UC12 DEMO =====");

        Quantity<LengthUnit> l1 =
                new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 =
                new Quantity<>(6.0, LengthUnit.INCHES);

        demonstrateSubtraction(l1, l2, LengthUnit.FEET);
        demonstrateDivision(l1, l2);

        Quantity<WeightUnit> w1 =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        demonstrateSubtraction(w1, w2, WeightUnit.KILOGRAM);
        demonstrateDivision(w1, w2);

        Quantity<VolumeUnit> v1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 =
                new Quantity<>(2.0, VolumeUnit.LITRE);

        demonstrateSubtraction(v1, v2, VolumeUnit.LITRE);
        demonstrateDivision(v1, v2);
    }
}