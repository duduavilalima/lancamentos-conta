package br.com.lima.lancamento.usecase;

import br.com.lima.conta.usecase.UpdateSaldoUseCase;
import br.com.lima.lancamento.LancamentoRepository;
import br.com.lima.lancamento.dto.LancamentoDto;
import br.com.lima.lancamento.model.Lancamento;
import br.com.lima.lancamento.model.enums.TipoLancamento;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class CreateLancamentoUseCase {

    private LancamentoRepository lancamentoRepository;

    public void execute(Long idConta, Lancamento lancamento) {
        synchronized (this) {
            lancamento.setIdConta(idConta);
            lancamento.setDataLancamento(LocalDateTime.now());
            this.lancamentoRepository.save(lancamento);
        }
    }
}
