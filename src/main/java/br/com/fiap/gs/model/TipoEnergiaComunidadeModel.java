package br.com.fiap.gs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class TipoEnergiaComunidadeModel extends RepresentationModel<TipoEnergiaComunidadeModel> {

    private final String descricaoEnergia;

    @JsonCreator
    public TipoEnergiaComunidadeModel(
            @JsonProperty("descricaoEnergia") String descricaoEnergia
    ) {
        this.descricaoEnergia = descricaoEnergia;
    }
}
