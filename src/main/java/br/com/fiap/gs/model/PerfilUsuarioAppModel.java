package br.com.fiap.gs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class PerfilUsuarioAppModel extends RepresentationModel<PerfilUsuarioAppModel> {

    private final Long idUsuario;
    private final Long idComunidade;
    private final Long idFabrica;
    private final String descricaoPerfil;

    @JsonCreator
    public PerfilUsuarioAppModel(
            @JsonProperty("idUsuario") Long idUsuario,
            @JsonProperty("idComunidade") Long idComunidade,
            @JsonProperty("idFabrica") Long idFabrica,
            @JsonProperty("decricaoPerfil") String descricaoPerfil
    ) {
        this.idUsuario = idUsuario;
        this.idComunidade = idComunidade;
        this.idFabrica = idFabrica;
        this.descricaoPerfil = descricaoPerfil;
    }
}
