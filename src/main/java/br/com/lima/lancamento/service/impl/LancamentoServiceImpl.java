package br.com.lima.lancamento.service.impl;

import br.com.lima.conta.usecase.UpdateSaldoUseCase;
import br.com.lima.lancamento.dto.LancamentoDto;
import br.com.lima.lancamento.dto.LancamentoRecord;
import br.com.lima.lancamento.model.Lancamento;
import br.com.lima.lancamento.model.enums.TipoLancamento;
import br.com.lima.lancamento.service.LancamentoService;
import br.com.lima.lancamento.usecase.CreateLancamentoUseCase;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class LancamentoServiceImpl implements LancamentoService {

    private final CreateLancamentoUseCase createLancamentoUseCase;
    private UpdateSaldoUseCase updateSaldoUseCase;

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    @Override
    public synchronized Boolean realizarLancamentos(LancamentoDto lancamentoDto) {
        if (lancamentoDto == null || lancamentoDto.getLancamentos() == null || lancamentoDto.getLancamentos().isEmpty()) {
            throw new IllegalArgumentException("LancamentoDto e a lista de lancamentos devem ser informados");
        }

        lancamentoDto.getLancamentos().forEach(lancamento -> {
                    Lancamento  lancamentoEntity = mapToLancamento(lancamento);
                    createLancamentoUseCase.execute(
                            lancamentoDto.getIdConta(),
                            lancamentoEntity
                    );

                     double valor = TipoLancamento.DEBITO.equals(lancamentoEntity.getTipo())
                            ? -lancamentoEntity.getValor()
                            : lancamentoEntity.getValor();

                    this.updateSaldoUseCase.execute(lancamentoDto.getIdConta(), valor);
                }
        );

        return true;
    }

    private Lancamento mapToLancamento(LancamentoRecord lancamentoRecord) {
        return Lancamento.builder()
            .tipo(lancamentoRecord.tipo())
            .valor(lancamentoRecord.valor())
            .build();
    }
}
