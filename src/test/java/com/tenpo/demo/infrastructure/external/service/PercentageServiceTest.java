package com.tenpo.demo.infrastructure.external.service;

import com.tenpo.demo.infrastructure.external.client.PercentageClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PercentageServiceTest {

    private PercentageClient percentageClient;
    private CacheManager cacheManager;
    private Cache cache;

    private PercentageService percentageService;

    @BeforeEach
    void setUp() {
        percentageClient = mock(PercentageClient.class);
        cacheManager = mock(CacheManager.class);
        cache = mock(Cache.class);

        when(cacheManager.getCache("percentage")).thenReturn(cache);

        percentageService = new PercentageService(percentageClient, cacheManager);
    }

    @Test
    void shouldReturnPercentageFromClient() {
        when(percentageClient.fetchPercentageFromExternalService()).thenReturn(0.1);

        Double result = percentageService.fetchPercentageFromExternalService();

        assertEquals(0.1, result);
    }

    @Test
    void shouldReturnCachedPercentageWhenClientFails() {
        when(percentageClient.fetchPercentageFromExternalService()).thenThrow(new RuntimeException("Service down"));
        when(cache.get("getPercentage", Double.class)).thenReturn(0.1);

        Double result = percentageService.fetchPercentageFromExternalService();

        assertEquals(0.1, result);
    }

    @Test
    void shouldReturnDefaultWhenClientFailsAndNoCache() {
        when(percentageClient.fetchPercentageFromExternalService()).thenThrow(new RuntimeException("Service down"));
        when(cache.get("getPercentage", Double.class)).thenReturn(null);

        Double result = percentageService.fetchPercentageFromExternalService();

        assertEquals(0.1, result); // Valor por defecto
    }
}
