package br.com.fiap.gs.service;

import br.com.fiap.gs.entity.EntidadeBase;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface EntidadeBaseService {
    EntidadeBase createEntidadeBase(EntidadeBase entidadeBase);

    EntidadeBase getById(Long id) throws ChangeSetPersister.NotFoundException;;

    List<EntidadeBase> getAllEntidades();

    EntidadeBase updateEntidadeBase(Long id, EntidadeBase entidadeBase);

    void deleteEntidadeBase(Long id);
}
