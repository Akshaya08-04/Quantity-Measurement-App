package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityWeightTest {

    @Test
    public void given1KgAnd1000Gram_ShouldBeEqual() {

        QuantityWeight kg =
                new QuantityWeight(1, WeightUnit.KILOGRAM);

        QuantityWeight gram =
                new QuantityWeight(1000, WeightUnit.GRAM);

        assertEquals(kg, gram);
    }

    @Test
    public void given1Kg_WhenConvertedToGram_ShouldReturn1000() {

        QuantityWeight kg =
                new QuantityWeight(1, WeightUnit.KILOGRAM);

        QuantityWeight result =
                kg.convertTo(WeightUnit.GRAM);

        assertEquals(1000, result.getValue(), 0.0001);
    }

    @Test
    public void given1KgAnd500Gram_WhenAdded_ShouldReturn1Point5Kg() {

        QuantityWeight w1 =
                new QuantityWeight(1, WeightUnit.KILOGRAM);

        QuantityWeight w2 =
                new QuantityWeight(500, WeightUnit.GRAM);

        QuantityWeight result = w1.add(w2);

        assertEquals(1.5, result.getValue(), 0.0001);
    }

    @Test
    public void given1KgAnd2Pound_WhenAddedInGram_ShouldReturnCorrectValue() {

        QuantityWeight w1 =
                new QuantityWeight(1, WeightUnit.KILOGRAM);

        QuantityWeight w2 =
                new QuantityWeight(2, WeightUnit.POUND);

        QuantityWeight result =
                w1.add(w2, WeightUnit.GRAM);

        assertEquals(1907.184, result.getValue(), 0.01);
    }
}
