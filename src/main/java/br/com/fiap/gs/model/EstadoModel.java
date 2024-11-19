package br.com.fiap.gs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class EstadoModel extends RepresentationModel<EstadoModel> {

    private final String nomeEstado;
    private final Long idPais;

    @JsonCreator
    public EstadoModel(
            @JsonProperty("nomeEstado") String nomeEstado,
            @JsonProperty("Long") Long idPais
    ) {
        this.nomeEstado = nomeEstado;
        this.idPais = idPais;
    }


}
