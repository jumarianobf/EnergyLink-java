package br.com.fiap.gs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MonitoramentoEnergiaModel extends RepresentationModel<MonitoramentoEnergiaModel> {

    private final Long idEntidade;
    private final LocalDate dataMonitoramento;
    private final BigDecimal energia;
    private final String tipoMonitoramento;

    @JsonCreator
    public MonitoramentoEnergiaModel(
            @JsonProperty("idEntidade") Long idEntidade,
            @JsonProperty("dataMonitoramento") LocalDate dataMonitoramento,
            @JsonProperty("energia") BigDecimal energia,
            @JsonProperty("tipoMonitoramento") String tipoMonitoramento
    ) {
        this.idEntidade = idEntidade;
        this.energia = energia;
        this.dataMonitoramento = dataMonitoramento;
        this.tipoMonitoramento = tipoMonitoramento;
    }
}
