package br.com.lima.conta.usecase;

import br.com.lima.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GetSaldoUseCase {

    private GetContaUseCase getContaUseCase;

    public Double execute(Long idConta) throws EntityNotFoundException {
        return this.getContaUseCase.execute(idConta).getSaldo();
    }

}
