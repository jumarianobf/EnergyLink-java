package br.com.fiap.gs.service;

import br.com.fiap.gs.entity.TipoUsuario;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface TipoUsuarioService {
    TipoUsuario createTipoUsuario (TipoUsuario request);

    TipoUsuario getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<TipoUsuario> getAllTipoUsuario() ;

    TipoUsuario updateTipoUsuario(Long id, TipoUsuario tipoEnergiaUsuario);

    void deleteTipoUsuario (Long id);



}
