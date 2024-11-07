package br.com.lima.conta.usecase;

import br.com.lima.conta.model.Conta;
import br.com.lima.conta.repository.ContaRepository;
import br.com.lima.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class GetContaUseCase {

    private ContaRepository contaRepository;

    @Transactional
    public synchronized Conta execute(Long idConta) {
        return contaRepository.findByIdForUpdate(idConta)
                .orElseThrow(() -> new EntityNotFoundException(Conta.class.getName(), idConta));
    }
}
