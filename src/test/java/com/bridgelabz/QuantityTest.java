package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityTest {

    // -----------------------------
    // LENGTH TESTS
    // -----------------------------

    @Test
    void testLengthEquality_FeetAndInches() {
        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testLengthConversion_FeetToInches() {
        Quantity<LengthUnit> q =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                q.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 0.01);
    }

    @Test
    void testLengthAddition_FeetPlusInches() {
        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                q1.add(q2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 0.01);
    }

    // -----------------------------
    // WEIGHT TESTS
    // -----------------------------

    @Test
    void testWeightEquality_KgAndGram() {
        Quantity<WeightUnit> w1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testWeightConversion_KgToGram() {
        Quantity<WeightUnit> w =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                w.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), 0.01);
    }

    @Test
    void testWeightAddition_KgPlusGram() {
        Quantity<WeightUnit> w1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                w1.add(w2, WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue(), 0.01);
    }

    // -----------------------------
    // CROSS CATEGORY PREVENTION
    // -----------------------------

    @Test
    void testCrossCategoryEquality_ShouldReturnFalse() {
        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // -----------------------------
    // VALIDATION TESTS
    // -----------------------------

    @Test
    void testConstructor_NullUnit_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(1.0, null));
    }

    @Test
    void testConstructor_InvalidValue_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    // -----------------------------
    // HASHCODE CONSISTENCY
    // -----------------------------

    @Test
    void testHashCode_EqualObjects_ShouldBeEqual() {
        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(q1.hashCode(), q2.hashCode());
    }
}