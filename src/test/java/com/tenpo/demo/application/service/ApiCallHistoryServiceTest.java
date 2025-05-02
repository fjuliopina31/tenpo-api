package com.tenpo.demo.application.service;

import com.tenpo.demo.domain.ApiCallHistory;
import com.tenpo.demo.domain.port.IPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ApiCallHistoryServiceTest {

    private IPersistence<ApiCallHistory, Long> repository;
    private ApiCallHistoryService service;

    @BeforeEach
    void setUp() {
        repository = mock(IPersistence.class);
        service = new ApiCallHistoryService(repository);
    }

    @Test
    void shouldSaveApiCallHistory() {
        ApiCallHistory history = ApiCallHistory.builder()
                .endpoint("/api/test")
                .timestamp(LocalDateTime.now())
                .parameters("param1=value1")
                .response("OK")
                .error(false)
                .build();

        service.saveApiCallHistory(history);

        verify(repository, times(1)).save(history);
    }

    @Test
    void shouldReturnAllApiCallHistory() {
        List<ApiCallHistory> expected = Arrays.asList(
                ApiCallHistory.builder().endpoint("/api/a").build(),
                ApiCallHistory.builder().endpoint("/api/b").build()
        );

        when(repository.findAll()).thenReturn(expected);

        List<ApiCallHistory> result = service.getAllApiCallHistory();

        assertEquals(2, result.size());
        assertEquals("/api/a", result.get(0).getEndpoint());
        assertEquals("/api/b", result.get(1).getEndpoint());
    }
}
