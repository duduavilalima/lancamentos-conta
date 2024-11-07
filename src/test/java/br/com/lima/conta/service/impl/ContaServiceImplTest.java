package br.com.lima.conta.service.impl;

import br.com.lima.conta.usecase.GetSaldoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ContaServiceImplTest {

    @InjectMocks
    private ContaServiceImpl contaService;

    @Mock
    private GetSaldoUseCase getSaldoUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSaldoSuccessfully() {
        // Given
        Long contaId = 1L;
        Double expectedSaldo = 1000.0;

        // When
        when(getSaldoUseCase.execute(anyLong())).thenReturn(expectedSaldo);

        // Then
        Double actualSaldo = contaService.getSaldo(contaId);
        assertEquals(expectedSaldo, actualSaldo);
    }

    @Test
    public void testGetSaldoNotFound() {
        // Given
        Long contaId = 2L;
        Double expectedSaldo = 0.0;

        // When
        when(getSaldoUseCase.execute(anyLong())).thenReturn(expectedSaldo);

        // Then
        Double actualSaldo = contaService.getSaldo(contaId);
        assertEquals(expectedSaldo, actualSaldo);
    }
}
