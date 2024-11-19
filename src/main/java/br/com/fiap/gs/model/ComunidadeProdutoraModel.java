package br.com.fiap.gs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Data
public class ComunidadeProdutoraModel extends RepresentationModel<ComunidadeProdutoraModel> {

    private final Long idEntidade;
    private final Long idTipoEnergia;
    private final BigDecimal capacidadeProducao;
    private final Long idLocalizacao;

    @JsonCreator
    public ComunidadeProdutoraModel(
            @JsonProperty("idEntidade") Long idEntidade,
            @JsonProperty("idTipoEnergia") Long idTipoEnergia,
            @JsonProperty("capacidadeProducao") BigDecimal capacidadeProducao,
            @JsonProperty("idLocalizacao") Long idLocalizacao
    ) {
        this.idEntidade = idEntidade;
        this.idTipoEnergia = idTipoEnergia;
        this.capacidadeProducao = capacidadeProducao;
        this.idLocalizacao = idLocalizacao;
    }

}
