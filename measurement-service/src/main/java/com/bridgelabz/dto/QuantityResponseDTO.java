package com.bridgelabz.dto;

public class QuantityResponseDTO {

    private String message;
    private Object result;

    public QuantityResponseDTO() {
    }

    public QuantityResponseDTO(String message, Object result) {
        this.message = message;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public Object getResult() {
        return result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}