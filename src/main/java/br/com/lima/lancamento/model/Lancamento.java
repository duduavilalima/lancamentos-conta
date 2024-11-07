package br.com.lima.lancamento.model;

import br.com.lima.lancamento.model.enums.TipoLancamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "lancamento")
@Entity
public class Lancamento {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    public Long id;

    @Column(nullable = false)
    private Double valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoLancamento tipo;

    @Column(name = "id_conta", nullable = false)
    private Long idConta;

    @Column(name = "data_lancamento", nullable = false)
    private LocalDateTime dataLancamento;

}
