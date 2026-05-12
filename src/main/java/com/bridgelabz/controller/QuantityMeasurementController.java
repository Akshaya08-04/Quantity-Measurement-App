package com.bridgelabz.controller;

import com.bridgelabz.dto.QuantityRequestDTO;
import com.bridgelabz.dto.QuantityResponseDTO;
import com.bridgelabz.entity.QuantityMeasurementEntity;
import com.bridgelabz.service.IQuantityService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/quantities",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class QuantityMeasurementController {

    private final IQuantityService service;

    public QuantityMeasurementController(IQuantityService service) {
        this.service = service;
    }

    @PostMapping(value = "/operate", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Perform quantity operation like compare, convert or add")
    public QuantityResponseDTO performOperation(@Valid @RequestBody QuantityRequestDTO request) {
        Object result = service.performOperation(request);
        return new QuantityResponseDTO("Operation performed successfully", result);
    }

    @GetMapping("/history")
    @Operation(summary = "Get all operation history")
    public List<QuantityMeasurementEntity> getHistory() {
        return service.getHistory();
    }

    @GetMapping("/history/{operationType}")
    @Operation(summary = "Get operation history by type")
    public List<QuantityMeasurementEntity> getHistoryByType(@PathVariable String operationType) {
        return service.getHistoryByOperation(operationType);
    }

    @GetMapping("/count/{operationType}")
    @Operation(summary = "Get operation count by type")
    public long getOperationCount(@PathVariable String operationType) {
        return service.getOperationCount(operationType);
    }
}