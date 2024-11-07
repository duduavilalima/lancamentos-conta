package br.com.lima.lancamento.service.impl;

import br.com.lima.conta.usecase.UpdateSaldoUseCase;
import br.com.lima.lancamento.dto.LancamentoDto;
import br.com.lima.lancamento.dto.LancamentoRecord;
import br.com.lima.lancamento.model.Lancamento;
import br.com.lima.lancamento.model.enums.TipoLancamento;
import br.com.lima.lancamento.usecase.CreateLancamentoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LancamentoServiceImplTest {

    @Mock
    private CreateLancamentoUseCase createLancamentoUseCase;

    @Mock
    private UpdateSaldoUseCase updateSaldoUseCase;

    @InjectMocks
    private LancamentoServiceImpl lancamentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRealizarLancamentos_Success() {
        // Arrange
        Long idConta = 1L;
        LancamentoRecord lancamentoRecord = new LancamentoRecord(100.0, TipoLancamento.CREDITO);
        LancamentoDto lancamentoDto = new LancamentoDto(idConta, List.of(lancamentoRecord));

        // Act
        Boolean result = lancamentoService.realizarLancamentos(lancamentoDto);

        // Assert
        assertTrue(result);

        // Capture and verify calls to use cases
        ArgumentCaptor<Lancamento> lancamentoCaptor = ArgumentCaptor.forClass(Lancamento.class);

        verify(createLancamentoUseCase, times(1)).execute(eq(idConta), lancamentoCaptor.capture());
        verify(updateSaldoUseCase, times(1)).execute(eq(idConta), eq(100.0));

        // Verify captured arguments
        Lancamento capturedLancamento = lancamentoCaptor.getValue();
        assertEquals(TipoLancamento.CREDITO, capturedLancamento.getTipo());
        assertEquals(100.0, capturedLancamento.getValor());
    }

    @Test
    void testRealizarLancamentos_DebitTransaction() {
        // Arrange
        Long idConta = 2L;
        LancamentoRecord lancamentoRecord = new LancamentoRecord(50.0, TipoLancamento.DEBITO);
        LancamentoDto lancamentoDto = new LancamentoDto(idConta, List.of(lancamentoRecord));

        // Act
        Boolean result = lancamentoService.realizarLancamentos(lancamentoDto);

        // Assert
        assertTrue(result);

        // Verify the debit amount passed to updateSaldoUseCase as negative
        verify(updateSaldoUseCase, times(1)).execute(eq(idConta), eq(-50.0));
    }

    @Test
    void testRealizarLancamentos_InvalidLancamentoDto_ThrowsException() {
        // Arrange
        LancamentoDto invalidDto = new LancamentoDto(null, null);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                lancamentoService.realizarLancamentos(invalidDto)
        );
        assertEquals("LancamentoDto e a lista de lancamentos devem ser informados", exception.getMessage());

        // Ensure no interactions with the use cases occurred
        verifyNoInteractions(createLancamentoUseCase, updateSaldoUseCase);
    }
}

