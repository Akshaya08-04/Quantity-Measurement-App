package com.bridgelabz.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quantity_measurements")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;

    private String unit;

    private String measurementType;

    private String operationType;

    private Double secondValue;

    private String secondUnit;

    private Double resultValue;

    private String resultUnit;

    public QuantityMeasurementEntity() {
    }

    public QuantityMeasurementEntity(Long id, Double value, String unit, String measurementType,
                                     String operationType, Double secondValue, String secondUnit,
                                     Double resultValue, String resultUnit) {
        this.id = id;
        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
        this.operationType = operationType;
        this.secondValue = secondValue;
        this.secondUnit = secondUnit;
        this.resultValue = resultValue;
        this.resultUnit = resultUnit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Double getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(Double secondValue) {
        this.secondValue = secondValue;
    }

    public String getSecondUnit() {
        return secondUnit;
    }

    public void setSecondUnit(String secondUnit) {
        this.secondUnit = secondUnit;
    }

    public Double getResultValue() {
        return resultValue;
    }

    public void setResultValue(Double resultValue) {
        this.resultValue = resultValue;
    }

    public String getResultUnit() {
        return resultUnit;
    }

    public void setResultUnit(String resultUnit) {
        this.resultUnit = resultUnit;
    }
}