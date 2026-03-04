package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityTest {

    private static final double DELTA = 0.01;

    // =====================================================
    // UC1 – Equality Tests
    // =====================================================

    @Test void testEquality_SameReference() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals(q, q);
    }

    @Test void testEquality_SameValueSameUnit() {
        assertEquals(new Quantity<>(1, LengthUnit.FEET),
                new Quantity<>(1, LengthUnit.FEET));
    }

    @Test void testEquality_OneFootEqualsTwelveInches() {
        assertEquals(new Quantity<>(1, LengthUnit.FEET),
                new Quantity<>(12, LengthUnit.INCHES));
    }

    @Test void testEquality_NotEqualDifferentValue() {
        assertNotEquals(new Quantity<>(1, LengthUnit.FEET),
                new Quantity<>(2, LengthUnit.FEET));
    }

    @Test void testEquality_Null() {
        assertNotEquals(new Quantity<>(1, LengthUnit.FEET), null);
    }

    @Test void testEquality_DifferentObjectType() {
        assertNotEquals(new Quantity<>(1, LengthUnit.FEET), "Test");
    }

    @Test void testEquality_CrossCategory() {
        assertNotEquals(new Quantity<>(1, LengthUnit.FEET),
                new Quantity<>(1, WeightUnit.KILOGRAM));
    }

    // =====================================================
    // UC3 – Conversion Tests
    // =====================================================

    @Test void testConvert_FeetToInches() {
        assertEquals(12,
                new Quantity<>(1, LengthUnit.FEET)
                        .convertTo(LengthUnit.INCHES).getValue(), DELTA);
    }

    @Test void testConvert_InchesToFeet() {
        assertEquals(2,
                new Quantity<>(24, LengthUnit.INCHES)
                        .convertTo(LengthUnit.FEET).getValue(), DELTA);
    }

    @Test void testConvert_KgToGram() {
        assertEquals(1000,
                new Quantity<>(1, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM).getValue(), DELTA);
    }

    @Test void testConvert_LitreToMilliLitre() {
        assertEquals(1000,
                new Quantity<>(1, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE).getValue(), DELTA);
    }

    @Test void testConvert_LargeNumber() {
        assertEquals(1000000,
                new Quantity<>(1000, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM).getValue(), DELTA);
    }

    // =====================================================
    // UC6 – Addition Tests
    // =====================================================

    @Test void testAdd_SameUnit() {
        assertEquals(5,
                new Quantity<>(2, LengthUnit.FEET)
                        .add(new Quantity<>(3, LengthUnit.FEET))
                        .getValue(), DELTA);
    }

    @Test void testAdd_CrossUnit() {
        assertEquals(2,
                new Quantity<>(1, LengthUnit.FEET)
                        .add(new Quantity<>(12, LengthUnit.INCHES))
                        .getValue(), DELTA);
    }

    @Test void testAdd_ExplicitTargetUnit() {
        assertEquals(15000,
                new Quantity<>(10, WeightUnit.KILOGRAM)
                        .add(new Quantity<>(5000, WeightUnit.GRAM),
                                WeightUnit.GRAM)
                        .getValue(), DELTA);
    }

    @Test void testAdd_NegativeValues() {
        assertEquals(0,
                new Quantity<>(5, LengthUnit.FEET)
                        .add(new Quantity<>(-5, LengthUnit.FEET))
                        .getValue(), DELTA);
    }

    @Test void testAdd_Rounding() {
        assertEquals(2.44,
                new Quantity<>(1.333, LengthUnit.FEET)
                        .add(new Quantity<>(1.111, LengthUnit.FEET))
                        .getValue(), DELTA);
    }

    // =====================================================
    // UC12 – Subtraction Tests
    // =====================================================

    @Test void testSubtract_SameUnit() {
        assertEquals(5,
                new Quantity<>(10, LengthUnit.FEET)
                        .subtract(new Quantity<>(5, LengthUnit.FEET))
                        .getValue(), DELTA);
    }

    @Test void testSubtract_CrossUnit() {
        assertEquals(9.5,
                new Quantity<>(10, LengthUnit.FEET)
                        .subtract(new Quantity<>(6, LengthUnit.INCHES))
                        .getValue(), DELTA);
    }

    @Test void testSubtract_ZeroResult() {
        assertEquals(0,
                new Quantity<>(10, LengthUnit.FEET)
                        .subtract(new Quantity<>(120, LengthUnit.INCHES))
                        .getValue(), DELTA);
    }

    @Test void testSubtract_NegativeResult() {
        assertEquals(-5,
                new Quantity<>(5, LengthUnit.FEET)
                        .subtract(new Quantity<>(10, LengthUnit.FEET))
                        .getValue(), DELTA);
    }

    @Test void testSubtract_ExplicitTarget() {
        assertEquals(3000,
                new Quantity<>(5, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(2, VolumeUnit.LITRE),
                                VolumeUnit.MILLILITRE)
                        .getValue(), DELTA);
    }

    // =====================================================
    // UC12 – Division Tests
    // =====================================================

    @Test void testDivide_SameUnit() {
        assertEquals(5,
                new Quantity<>(10, LengthUnit.FEET)
                        .divide(new Quantity<>(2, LengthUnit.FEET)),
                DELTA);
    }

    @Test void testDivide_CrossUnit() {
        assertEquals(1,
                new Quantity<>(24, LengthUnit.INCHES)
                        .divide(new Quantity<>(2, LengthUnit.FEET)),
                DELTA);
    }

    @Test void testDivide_FractionResult() {
        assertEquals(0.5,
                new Quantity<>(5, VolumeUnit.LITRE)
                        .divide(new Quantity<>(10, VolumeUnit.LITRE)),
                DELTA);
    }

    @Test void testDivide_ByZero() {
        assertThrows(ArithmeticException.class,
                () -> new Quantity<>(10, LengthUnit.FEET)
                        .divide(new Quantity<>(0, LengthUnit.FEET)));
    }

    // =====================================================
    // UC13 – Centralized Validation
    // =====================================================

    @Test void testAdd_NullOperand() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1, LengthUnit.FEET).add(null));
    }

    @Test void testSubtract_NullOperand() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1, LengthUnit.FEET).subtract(null));
    }

    @Test void testDivide_NullOperand() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1, LengthUnit.FEET).divide(null));
    }

    @Test void testCrossCategory_Add() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1, LengthUnit.FEET)
                        .add((Quantity) new Quantity<>(1, WeightUnit.KILOGRAM)));
    }

    @Test void testCrossCategory_Subtract() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1, LengthUnit.FEET)
                        .subtract((Quantity) new Quantity<>(1, VolumeUnit.LITRE)));
    }

    @Test void testCrossCategory_Divide() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1, LengthUnit.FEET)
                        .divide((Quantity) new Quantity<>(1, WeightUnit.KILOGRAM)));
    }

    @Test void testConvert_NullTargetUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1, LengthUnit.FEET).convertTo(null));
    }

    // =====================================================
    // Immutability Tests
    // =====================================================

    @Test void testImmutability_Add() {
        Quantity<LengthUnit> q = new Quantity<>(5, LengthUnit.FEET);
        q.add(new Quantity<>(5, LengthUnit.FEET));
        assertEquals(5, q.getValue(), DELTA);
    }

    @Test void testImmutability_Subtract() {
        Quantity<LengthUnit> q = new Quantity<>(5, LengthUnit.FEET);
        q.subtract(new Quantity<>(2, LengthUnit.FEET));
        assertEquals(5, q.getValue(), DELTA);
    }

    @Test void testImmutability_Divide() {
        Quantity<LengthUnit> q = new Quantity<>(10, LengthUnit.FEET);
        q.divide(new Quantity<>(2, LengthUnit.FEET));
        assertEquals(10, q.getValue(), DELTA);
    }

    // =====================================================
    // HashCode & toString
    // =====================================================

    @Test void testHashCode_Consistency() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);
        assertEquals(q1.hashCode(), q2.hashCode());
    }

    @Test void testToString_NotNull() {
        assertNotNull(new Quantity<>(1, LengthUnit.FEET).toString());
    }

    // =====================================================
    // Chain Operations
    // =====================================================

    @Test void testChainedOperations() {
        double result = new Quantity<>(10, LengthUnit.FEET)
                .add(new Quantity<>(2, LengthUnit.FEET))
                .subtract(new Quantity<>(1, LengthUnit.FEET))
                .divide(new Quantity<>(11, LengthUnit.FEET));
        assertEquals(1, result, DELTA);
    }
}