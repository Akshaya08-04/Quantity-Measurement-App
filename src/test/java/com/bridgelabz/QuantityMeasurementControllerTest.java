package com.bridgelabz;

import com.bridgelabz.config.SecurityConfig;
import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.dto.QuantityRequestDTO;
import com.bridgelabz.entity.QuantityMeasurementEntity;
import com.bridgelabz.service.IQuantityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuantityMeasurementController.class)
@Import(SecurityConfig.class)
class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IQuantityService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCompareQuantities() throws Exception {
        QuantityRequestDTO request = new QuantityRequestDTO();
        request.setFirst(new QuantityDTO(12.0, "INCHES", "LengthUnit"));
        request.setSecond(new QuantityDTO(1.0, "FEET", "LengthUnit"));
        request.setOperationType("COMPARE");

        when(service.performOperation(any(QuantityRequestDTO.class))).thenReturn(true);

        mockMvc.perform(post("/api/v1/quantities/operate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Operation performed successfully"))
                .andExpect(jsonPath("$.result").value(true));
    }

    @Test
    void shouldConvertQuantity() throws Exception {
        QuantityRequestDTO request = new QuantityRequestDTO();
        request.setFirst(new QuantityDTO(24.0, "INCHES", "LengthUnit"));
        request.setOperationType("CONVERT");
        request.setTargetUnit("FEET");

        when(service.performOperation(any(QuantityRequestDTO.class))).thenReturn(2.0);

        mockMvc.perform(post("/api/v1/quantities/operate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(2.0));
    }

    @Test
    void shouldReturnHistory() throws Exception {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                1L, 12.0, "INCHES", "LengthUnit", "COMPARE",
                1.0, "FEET", 1.0, "BOOLEAN"
        );

        when(service.getHistory()).thenReturn(List.of(entity));

        mockMvc.perform(get("/api/v1/quantities/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].unit").value("INCHES"))
                .andExpect(jsonPath("$[0].operationType").value("COMPARE"));
    }

    @Test
    void shouldReturnCount() throws Exception {
        when(service.getOperationCount(eq("COMPARE"))).thenReturn(5L);

        mockMvc.perform(get("/api/v1/quantities/count/COMPARE"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }
}