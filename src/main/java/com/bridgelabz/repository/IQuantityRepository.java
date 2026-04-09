package com.bridgelabz.repository;

import com.bridgelabz.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuantityRepository extends JpaRepository<QuantityMeasurementEntity, Long> {
    List<QuantityMeasurementEntity> findByOperationType(String operationType);
    long countByOperationType(String operationType);
}