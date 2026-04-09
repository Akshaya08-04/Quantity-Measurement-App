package com.bridgelabz;

import com.bridgelabz.entity.QuantityMeasurementEntity;
import com.bridgelabz.repository.QuantityDatabaseRepositoryImpl;
import com.bridgelabz.util.DatabaseInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuantityDatabaseRepositoryTest {

    private QuantityDatabaseRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        DatabaseInitializer.initializeSchema();
        repository = new QuantityDatabaseRepositoryImpl();
        repository.deleteAll();
    }

    @Test
    void testSaveEntity() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setThisValue(1.0);
        entity.setThisUnit("FEET");
        entity.setThatValue(12.0);
        entity.setThatUnit("INCHES");
        entity.setMeasurementType("LengthUnit");
        entity.setOperation("COMPARE");
        entity.setResultMessage("Equal");

        QuantityMeasurementEntity saved = repository.save(entity);

        assertNotNull(saved.getId());
    }

    @Test
    void testGetAll() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setThisValue(1);
        entity.setThisUnit("FEET");
        entity.setThatValue(12);
        entity.setThatUnit("INCHES");
        entity.setMeasurementType("LengthUnit");
        entity.setOperation("COMPARE");

        repository.save(entity);

        List<QuantityMeasurementEntity> list = repository.getAllMeasurements();

        assertEquals(1, list.size());
    }
}