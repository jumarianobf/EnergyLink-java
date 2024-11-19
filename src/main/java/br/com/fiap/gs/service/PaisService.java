package br.com.fiap.gs.service;

import br.com.fiap.gs.entity.Pais;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface PaisService {
    Pais createPais (Pais request);

    Pais getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<Pais> getAllPais() ;

    Pais updatePais(Long id, Pais pais);

    void deletePais(Long id);



}
