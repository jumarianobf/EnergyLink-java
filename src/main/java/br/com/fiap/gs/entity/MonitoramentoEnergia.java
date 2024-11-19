package br.com.fiap.gs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Monitoramento_Energia")
@Builder
public class MonitoramentoEnergia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_monitoramento", length = 10, nullable = false)
    private Long idMonitoramento;

    @ManyToOne
    @JoinColumn(name = "id_entidade", nullable = false)
    @NotNull
    private EntidadeBase idEntidade;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "Data_Monitoramento", nullable = false)
    @NotNull
    private LocalDate dataMonitoramento;

    @Column(name = "energia")
    @NotNull
    private BigDecimal energia;

    @Column(name = "tipo_monitoramento", length = 20)
    @NotNull
    private String tipoMonitoramento;

}
