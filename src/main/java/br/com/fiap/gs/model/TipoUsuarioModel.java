package br.com.fiap.gs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class TipoUsuarioModel  extends RepresentationModel<TipoUsuarioModel> {

    private final String descricaoTipo;

    @JsonCreator
    public TipoUsuarioModel(
            @JsonProperty("descricaoTipo") String descricaoTipo
    ) {
        this.descricaoTipo = descricaoTipo;
    }
}
