package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityTest {

    private static final double DELTA = 0.01;

    // =========================
    // SUBTRACTION TESTS
    // =========================

    @Test
    void testSubtraction_SameUnit_Feet() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.subtract(q2, LengthUnit.FEET);

        assertEquals(5.0, result.getValue(), DELTA);
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2, LengthUnit.FEET);

        assertEquals(9.5, result.getValue(), DELTA);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2, LengthUnit.INCHES);

        assertEquals(114.0, result.getValue(), DELTA);
    }

    @Test
    void testSubtraction_ResultingInNegative() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.subtract(q2, LengthUnit.FEET);

        assertEquals(-5.0, result.getValue(), DELTA);
    }

    @Test
    void testSubtraction_ResultingInZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(120.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2, LengthUnit.FEET);

        assertEquals(0.0, result.getValue(), DELTA);
    }

    @Test
    void testSubtraction_CrossCategory_ShouldThrow() {
        Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> length.subtract((Quantity) weight, LengthUnit.FEET));
    }

    @Test
    void testSubtraction_NullOperand_ShouldThrow() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.subtract(null, LengthUnit.FEET));
    }

    @Test
    void testSubtraction_Immutability() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        q1.subtract(q2, LengthUnit.FEET);

        assertEquals(10.0, q1.getValue(), DELTA);
    }

    @Test
    void testSubtraction_NonCommutative() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result1 = q1.subtract(q2, LengthUnit.FEET);
        Quantity<LengthUnit> result2 = q2.subtract(q1, LengthUnit.FEET);

        assertNotEquals(result1.getValue(), result2.getValue());
    }

    // =========================
    // DIVISION TESTS
    // =========================

    @Test
    void testDivision_SameUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(5.0, q1.divide(q2), DELTA);
    }

    @Test
    void testDivision_CrossUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(1.0, q1.divide(q2), DELTA);
    }

    @Test
    void testDivision_RatioGreaterThanOne() {
        Quantity<WeightUnit> q1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertEquals(2.0, q1.divide(q2), DELTA);
    }

    @Test
    void testDivision_RatioLessThanOne() {
        Quantity<VolumeUnit> q1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(10.0, VolumeUnit.LITRE);

        assertEquals(0.5, q1.divide(q2), DELTA);
    }

    @Test
    void testDivision_RatioEqualToOne() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1.0, VolumeUnit.LITRE);

        assertEquals(1.0, q1.divide(q2), DELTA);
    }

    @Test
    void testDivision_ByZero_ShouldThrow() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class,
                () -> q1.divide(q2));
    }

    @Test
    void testDivision_CrossCategory_ShouldThrow() {
        Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> length.divide((Quantity) weight));
    }

    @Test
    void testDivision_NullOperand_ShouldThrow() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.divide(null));
    }

    @Test
    void testDivision_NonCommutative() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        double result1 = q1.divide(q2);
        double result2 = q2.divide(q1);

        assertNotEquals(result1, result2);
    }

    @Test
    void testDivision_Immutability() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        q1.divide(q2);

        assertEquals(10.0, q1.getValue(), DELTA);
    }
}