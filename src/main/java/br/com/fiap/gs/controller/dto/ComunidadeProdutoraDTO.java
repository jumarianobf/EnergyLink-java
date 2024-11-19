package br.com.fiap.gs.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComunidadeProdutoraDTO {

    @NotNull(message = "O id da entidade não pode ser nulo")
    private Long idEntidade;

    @NotNull(message = "O tipo de energia não pode ser nulo")
    private Long idTipoEnergia;

    @NotNull(message = "A capacidade de produção não pode ser nula")
    private BigDecimal capacidadeProducao; // Capacidade de produção da comunidade

    @NotNull(message = "A localização não pode ser nula")
    private Long idLocalizacao;
}
