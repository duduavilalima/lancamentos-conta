package br.com.lima.conta.usecase;

import br.com.lima.conta.model.Conta;
import br.com.lima.conta.repository.ContaRepository;
import br.com.lima.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UpdateSaldoUseCase {

    private ContaRepository contaRepository;
    private GetContaUseCase getContaUseCase;

    public void execute(Long idConta, Double valor) {
        Conta conta = getContaUseCase.execute(idConta);
        contaRepository.updateSaldo(idConta, valor);
    }
}
