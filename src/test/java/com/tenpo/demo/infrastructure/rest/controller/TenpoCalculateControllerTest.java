package com.tenpo.demo.infrastructure.rest.controller;

import com.tenpo.demo.application.usecase.ICalculateUseCase;
import com.tenpo.demo.domain.Calculate;
import com.tenpo.demo.infrastructure.rest.dto.CalculateDTO;
import com.tenpo.demo.infrastructure.utilities.CalculateMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class TenpoCalculateControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ICalculateUseCase calculateUseCase;

    @Mock
    private CalculateMapper calculateMapper;

    @InjectMocks
    private TenpoCalculateController tenpoCalculateController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tenpoCalculateController).build();
    }

    @Test
    void testCalculate() throws Exception {
        // Crear un DTO de ejemplo
        CalculateDTO calculateDTO = new CalculateDTO();
        calculateDTO.setNumber1(100);
        calculateDTO.setNumber2(35);

        Calculate calculate = new Calculate();
        calculate.setNumber1(100);
        calculate.setNumber2(35);

        when(calculateMapper.toDomain(calculateDTO)).thenReturn(calculate);
        when(calculateUseCase.calculatePercentage(calculate)).thenReturn(13.5);

        mockMvc.perform(post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"number1\": 100, \"number2\": 35}")) // Enviar el JSON con los datos
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("13.5")); // Esperamos que el resultado sea el valor calculado (10.0)
    }
}
