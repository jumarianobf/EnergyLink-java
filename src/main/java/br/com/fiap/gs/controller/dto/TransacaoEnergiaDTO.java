package br.com.fiap.gs.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransacaoEnergiaDTO {

    @NotNull(message = "O contrato não pode ser nulo")
    private Long idContrato; // ID do contrato de distribuição

    @NotNull(message = "A data não pode ser nula")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataTransacao; // Data da transação de energia

    @NotNull(message = "A quantidade de energia não pode ser nula")
    private BigDecimal quantidadeEnergia; // Quantidade de energia na transação

    @NotNull(message = "O custo total não pode ser nulo")
    private BigDecimal custoTotal; // Custo total da transação

    @NotNull(message = "O status da transação não pode ser nulo")
    private String statusTransacao; // Status da transação (ex: "concluída", "pendente")
}
