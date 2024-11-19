package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.PerfilUsuarioAppDTO;
import br.com.fiap.gs.entity.PerfilUsuarioApp;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface PerfilUsuarioAppService {

    PerfilUsuarioApp createPerfilUsuarioApp(PerfilUsuarioAppDTO request);

    PerfilUsuarioApp getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<PerfilUsuarioApp> getAllPerfilUsuarioApp();

    PerfilUsuarioApp updatePerfilUsuarioApp(Long id, PerfilUsuarioAppDTO perfilUsuarioAppDTO);

    void deletePerfilUsuarioApp(Long id);
}
