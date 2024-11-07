package br.com.lima.lancamento.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LancamentoDto {
    private Long idConta;
    private List<LancamentoRecord> lancamentos;
}
