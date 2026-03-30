package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityTest {

    // ==============================
    // UC1 – Equality Tests
    // ==============================

    @Test
    void givenSameFeet_whenCompared_shouldReturnEqual() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1, LengthUnit.FEET);
        assertEquals(q1, q2);
    }

    @Test
    void givenFeetAndInches_whenEqual_shouldReturnTrue() {
        Quantity<LengthUnit> feet = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12, LengthUnit.INCHES);
        assertEquals(feet, inches);
    }

    @Test
    void givenDifferentFeet_whenCompared_shouldReturnFalse() {
        Quantity<LengthUnit> q1 = new Quantity<>(2, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(3, LengthUnit.FEET);
        assertNotEquals(q1, q2);
    }

    @Test
    void givenNull_whenCompared_shouldReturnFalse() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        assertNotEquals(null, q1);
    }

    // ==============================
    // UC2 – Zero Tests
    // ==============================

    @Test
    void givenZeroFeet_whenCompared_shouldReturnEqual() {
        Quantity<LengthUnit> q1 = new Quantity<>(0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0, LengthUnit.FEET);
        assertEquals(q1, q2);
    }

    // ==============================
    // UC3 – Conversion Tests
    // ==============================

    @Test
    void givenInches_whenConvertedToFeet_shouldMatch() {
        Quantity<LengthUnit> inches = new Quantity<>(24, LengthUnit.INCHES);
        assertEquals(2, inches.convertTo(LengthUnit.FEET));
    }

    @Test
    void givenKg_whenConvertedToGram_shouldMatch() {
        Quantity<WeightUnit> kg = new Quantity<>(1, WeightUnit.KILOGRAM);
        assertEquals(1000, kg.convertTo(WeightUnit.GRAM));
    }

    @Test
    void givenLitre_whenConvertedToMillilitre_shouldMatch() {
        Quantity<VolumeUnit> litre = new Quantity<>(1, VolumeUnit.LITRE);
        assertEquals(1000, litre.convertTo(VolumeUnit.MILLILITRE));
    }

    // ==============================
    // UC6 – Addition Tests
    // ==============================

    @Test
    void givenFeetAndInches_whenAdded_shouldReturnFeet() {
        Quantity<LengthUnit> f = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> i = new Quantity<>(6, LengthUnit.INCHES);
        Quantity<LengthUnit> result = f.add(i, LengthUnit.FEET);
        assertEquals(new Quantity<>(1.5, LengthUnit.FEET), result);
    }

    @Test
    void givenKgAndGram_whenAdded_shouldReturnKg() {
        Quantity<WeightUnit> kg = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(500, WeightUnit.GRAM);
        Quantity<WeightUnit> result = kg.add(g, WeightUnit.KILOGRAM);
        assertEquals(new Quantity<>(1.5, WeightUnit.KILOGRAM), result);
    }

    // ==============================
    // UC12 – Subtraction Tests
    // ==============================

    @Test
    void givenFeetAndInches_whenSubtracted_shouldReturnCorrect() {
        Quantity<LengthUnit> f = new Quantity<>(2, LengthUnit.FEET);
        Quantity<LengthUnit> i = new Quantity<>(6, LengthUnit.INCHES);
        Quantity<LengthUnit> result = f.subtract(i, LengthUnit.FEET);
        assertEquals(new Quantity<>(1.5, LengthUnit.FEET), result);
    }

    // ==============================
    // UC12 – Division Tests
    // ==============================

    @Test
    void givenTwoLengths_whenDivided_shouldReturnRatio() {
        Quantity<LengthUnit> f1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> f2 = new Quantity<>(5, LengthUnit.FEET);
        assertEquals(2, f1.divide(f2, LengthUnit.FEET));
    }

    // ==============================
    // UC13 – Validation Tests
    // ==============================

    @Test
    void givenNullUnit_whenCreated_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(10, null);
        });
    }

    @Test
    void givenTemperature_whenAddAttempted_shouldThrowException() {
        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class, () -> {
            t1.add(t2, TemperatureUnit.CELSIUS);
        });
    }

    // ==============================
    // UC14 – Temperature Tests
    // ==============================

    @Test
    void givenCelsiusAndFahrenheit_whenEqual_shouldReturnTrue() {
        Quantity<TemperatureUnit> c =
                new Quantity<>(100, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> f =
                new Quantity<>(212, TemperatureUnit.FAHRENHEIT);
        assertEquals(c, f);
    }

    @Test
    void givenCelsius_whenConvertedToFahrenheit_shouldMatch() {
        Quantity<TemperatureUnit> c =
                new Quantity<>(0, TemperatureUnit.CELSIUS);
        assertEquals(32,
                c.convertTo(TemperatureUnit.FAHRENHEIT));
    }

    @Test
    void givenDifferentTemperature_whenCompared_shouldReturnFalse() {
        Quantity<TemperatureUnit> t1 =
                new Quantity<>(0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 =
                new Quantity<>(10, TemperatureUnit.CELSIUS);
        assertNotEquals(t1, t2);
    }

}