package com.bridgelabz.service;

import com.bridgelabz.dto.QuantityRequestDTO;
import com.bridgelabz.entity.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityService {
    Object performOperation(QuantityRequestDTO request);
    List<QuantityMeasurementEntity> getHistory();
    List<QuantityMeasurementEntity> getHistoryByOperation(String operationType);
    long getOperationCount(String operationType);
}