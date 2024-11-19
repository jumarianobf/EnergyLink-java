package br.com.fiap.gs.service;

import br.com.fiap.gs.entity.TipoEnergiaComunidade;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface TipoEnergiaComunidadeService {
    TipoEnergiaComunidade createTipoEnergiaComunidade (TipoEnergiaComunidade request);

    TipoEnergiaComunidade getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<TipoEnergiaComunidade> getAllTipoEnergiaComunidade() ;

    TipoEnergiaComunidade updateTipoEnergiaComunidade(Long id, TipoEnergiaComunidade tipoEnergiaComunidade);

    void deleteTipoEnergiaComunidade (Long id);



}
