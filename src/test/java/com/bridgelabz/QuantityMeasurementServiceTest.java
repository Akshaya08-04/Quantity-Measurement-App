package com.bridgelabz;

import com.bridgelabz.repository.IQuantityRepository;
import com.bridgelabz.repository.QuantityRepositoryImpl;
import com.bridgelabz.service.IQuantityMeasurementService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementServiceTest {

    @Test
    void givenFeetAndInches_whenComparedUsingService_shouldReturnTrue() {
        IQuantityRepository repository = new QuantityRepositoryImpl();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);

        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);

        assertTrue(service.compare(q1, q2));
    }

    @Test
    void givenFeet_whenConvertedToInchesUsingService_shouldReturn12() {
        IQuantityRepository repository = new QuantityRepositoryImpl();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);

        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> result = service.convert(q1, LengthUnit.INCHES);

        assertEquals(12, result.getValue());
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void givenFeetAndInches_whenAddedUsingService_shouldReturnCorrectResult() {
        IQuantityRepository repository = new QuantityRepositoryImpl();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);

        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result = service.add(q1, q2);

        assertEquals(new Quantity<>(2, LengthUnit.FEET), result);
    }

    @Test
    void givenFeetAndInches_whenSubtractedUsingService_shouldReturnCorrectResult() {
        IQuantityRepository repository = new QuantityRepositoryImpl();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);

        Quantity<LengthUnit> q1 = new Quantity<>(2, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result = service.subtract(q1, q2);

        assertEquals(new Quantity<>(1, LengthUnit.FEET), result);
    }

    @Test
    void givenNullQuantity_whenComparedUsingService_shouldThrowException() {
        IQuantityRepository repository = new QuantityRepositoryImpl();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);

        assertThrows(RuntimeException.class, () -> service.compare(null, null));
    }
}