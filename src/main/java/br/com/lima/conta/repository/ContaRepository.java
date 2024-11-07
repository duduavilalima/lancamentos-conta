package br.com.lima.conta.repository;

import br.com.lima.conta.model.Conta;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Modifying
    @Query("UPDATE Conta conta SET conta.saldo = conta.saldo + :valor WHERE conta.id = :idConta")
    void updateSaldo(@Param("idConta") Long idConta, @Param("valor") Double valor);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Conta c WHERE c.id = :idConta")
    Optional<Conta> findByIdForUpdate(@Param("idConta") Long idConta);
}
