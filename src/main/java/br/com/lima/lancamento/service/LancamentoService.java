package br.com.lima.lancamento.service;

import br.com.lima.lancamento.dto.LancamentoDto;
import jakarta.validation.Valid;

public interface LancamentoService {
    Boolean realizarLancamentos( @Valid LancamentoDto lancamentoDto);
}
