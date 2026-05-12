package com.bridgelabz.dto;

public class OperationRequestDTO {

    private String operationType;

    public OperationRequestDTO() {
    }

    public OperationRequestDTO(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}