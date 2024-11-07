package br.com.lima.conta.service.impl;

import br.com.lima.conta.service.ContaService;
import br.com.lima.conta.usecase.GetSaldoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ContaServiceImpl implements ContaService {

    private GetSaldoUseCase getSaldoUseCase;

    @Override
    public Double getSaldo(Long id) {
        return getSaldoUseCase.execute(id);
    }
}
