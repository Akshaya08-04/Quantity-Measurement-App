package com.bridgelabz;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<LengthUnit> l1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6, LengthUnit.INCHES);

        System.out.println("Addition: " +
                l1.add(l2, LengthUnit.FEET));

        System.out.println("Subtraction: " +
                l1.subtract(l2, LengthUnit.FEET));

        System.out.println("Division: " +
                l1.divide(l2, LengthUnit.FEET));

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(212, TemperatureUnit.FAHRENHEIT);

        System.out.println("Temperature Equal: " +
                t1.equals(t2));
    }
}