package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.FabricaParceiraDTO;
import br.com.fiap.gs.entity.FabricaParceira;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;


public interface FabricaParceiraService {
    FabricaParceira createFabrica (FabricaParceiraDTO fabrica);

    FabricaParceira getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<FabricaParceira> getAllFabrica() ;

    FabricaParceira updateFabrica (Long id, FabricaParceiraDTO fabrica);

    void deleteFabrica (Long id);
}
