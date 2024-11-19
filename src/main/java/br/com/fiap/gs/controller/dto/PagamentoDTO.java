package br.com.fiap.gs.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
public class PagamentoDTO {

    @NotNull(message = "O ID do contrato não pode ser nulo")
    private Long idContrato; // ID do contrato associado ao pagamento


    @NotNull(message = "A data não pode ser nula")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataPagamento; // Data do pagamento

    @PositiveOrZero(message = "O valor do pagamento deve ser maior ou igual a zero")
    private BigDecimal valor; // Valor do pagamento

    private String statusPagamento; // Status do pagamento
}
