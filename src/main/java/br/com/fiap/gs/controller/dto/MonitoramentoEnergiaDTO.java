package br.com.fiap.gs.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class MonitoramentoEnergiaDTO {

    @NotNull(message = "O id da entidade não pode ser nulo")
    private Long idEntidade;

    @NotNull(message = "A data não pode ser nula")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataMonitoramento;

    @NotNull(message = "A data do monitoramento não pode ser nula")
    private BigDecimal energia;

    @NotNull(message = "A data do monitoramento não pode ser nula")
    @Size(max = 20, message = "O id da entidade deve ter no máximo 20 caracteres")
    private String tipoMonitoramento;
}
