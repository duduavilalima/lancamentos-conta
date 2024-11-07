package br.com.lima.lancamento.dto;

import br.com.lima.lancamento.model.enums.TipoLancamento;

public record LancamentoRecord(Double valor, TipoLancamento tipo) {
}
