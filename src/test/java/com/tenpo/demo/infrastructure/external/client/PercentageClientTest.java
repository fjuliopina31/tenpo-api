package com.tenpo.demo.infrastructure.external.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PercentageClientTest {

    private RestTemplate restTemplate;
    private PercentageClient percentageClient;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        RestTemplateBuilder builder = mock(RestTemplateBuilder.class);
        when(builder.build()).thenReturn(restTemplate);

        percentageClient = new PercentageClient(builder.build(), "http://localhost:8080/external/percentage");
    }

    @Test
    void shouldReturnPercentageFromExternalService() {
        when(restTemplate.getForObject(anyString(), eq(Double.class))).thenReturn(0.1);

        Double result = percentageClient.fetchPercentageFromExternalService();

        assertEquals(0.1, result);
    }

    @Test
    void shouldThrowExceptionWhenExternalCallFails() {
        when(restTemplate.getForObject(anyString(), eq(Double.class))).thenThrow(new RuntimeException("Service down"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            percentageClient.fetchPercentageFromExternalService();
        });

        assertTrue(exception.getMessage().contains("Error calling external percentage service"));
    }
}


