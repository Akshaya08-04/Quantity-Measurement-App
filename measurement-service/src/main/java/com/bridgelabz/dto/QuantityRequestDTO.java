package com.bridgelabz.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class QuantityRequestDTO {

    @NotNull(message = "First quantity cannot be null")
    @Valid
    private QuantityDTO first;

    @Valid
    private QuantityDTO second;

    @NotBlank(message = "Operation type cannot be blank")
    private String operationType;

    private String targetUnit;

    public QuantityRequestDTO() {
    }

    public QuantityDTO getFirst() {
        return first;
    }

    public void setFirst(QuantityDTO first) {
        this.first = first;
    }

    public QuantityDTO getSecond() {
        return second;
    }

    public void setSecond(QuantityDTO second) {
        this.second = second;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getTargetUnit() {
        return targetUnit;
    }

    public void setTargetUnit(String targetUnit) {
        this.targetUnit = targetUnit;
    }
}