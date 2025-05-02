package com.tenpo.demo.application.usecase;

import com.tenpo.demo.domain.Calculate;
import com.tenpo.demo.domain.port.IPercentageClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculateUseCaseTest {

    private IPercentageClientService percentageClientService;
    private CalculateUseCase calculateUseCase;

    @BeforeEach
    void setUp() {
        percentageClientService = mock(IPercentageClientService.class);
        calculateUseCase = new CalculateUseCase(percentageClientService);
    }

    @Test
    void shouldCalculatePercentageCorrectly() {
        // Arrange
        Calculate calculate = new Calculate(20, 10);
        when(percentageClientService.fetchPercentageFromExternalService()).thenReturn(0.1);

        // Act
        Double result = calculateUseCase.calculatePercentage(calculate);

        // Assert
        assertEquals(3.0, result);
        verify(percentageClientService, times(1)).fetchPercentageFromExternalService();
    }

    @Test
    void shouldThrowNullPointerIfCalculateIsNull() {
        assertThrows(NullPointerException.class, () -> calculateUseCase.calculatePercentage(null));
    }

    @Test
    void shouldThrowIfPercentageServiceFails() {
        Calculate calculate = new Calculate(10, 10);
        when(percentageClientService.fetchPercentageFromExternalService()).thenThrow(new RuntimeException("Service down"));

        assertThrows(RuntimeException.class, () -> calculateUseCase.calculatePercentage(calculate));
    }
}
