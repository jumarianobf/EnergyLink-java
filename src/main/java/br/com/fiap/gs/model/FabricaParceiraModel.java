package br.com.fiap.gs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Data
public class FabricaParceiraModel extends RepresentationModel<FabricaParceiraModel> {

    private final Long idEntidade;
    private final Long idLocalizacao;
    private final BigDecimal demandaEnergiaFabrica;

    @JsonCreator
    public FabricaParceiraModel(
            @JsonProperty("idEntidade") Long idEntidade,
            @JsonProperty("idLocalizacao") Long idLocalizacao,
            @JsonProperty("demandaEnergiaFabrica") BigDecimal demandaEnergiaFabrica
    ) {
        this.idEntidade = idEntidade;
        this.idLocalizacao = idLocalizacao;
        this.demandaEnergiaFabrica = demandaEnergiaFabrica;
    }
}
