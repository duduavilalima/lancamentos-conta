package br.com.lima.conta.controller;

import br.com.lima.conta.dto.ContaDto;
import br.com.lima.conta.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/contas")
public class ContaController {

    private ContaService contaService;

    @GetMapping("/{id}")
    @Operation(summary = "Este endpoint é responsável por recuperar o saldo de uma conta específica.")
    public ResponseEntity<Double> getSaldo(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.contaService.getSaldo(id));
    }
}
