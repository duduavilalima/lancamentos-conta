package br.com.lima.conta.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "conta")
@Entity
public class Conta {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    public Long id;

    @Column(name = "saldo", nullable = false)
    private Double saldo;

    @Version
    @Column
    private Integer version;

}
