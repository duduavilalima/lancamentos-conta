package br.com.lima.lancamento.controller;

import br.com.lima.lancamento.dto.LancamentoDto;
import br.com.lima.lancamento.service.LancamentoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/lancamentos")
public class LancamentoController {

    private LancamentoService lancamentoService;

    @Operation(summary = "Este endpoint é responsável por realizar os lançamentos de crédito e débito em uma conta.")
    @PostMapping
    public ResponseEntity<Boolean> realizarLancamentos(@RequestBody LancamentoDto lancamentoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoService.realizarLancamentos(lancamentoDto));
    }
}
