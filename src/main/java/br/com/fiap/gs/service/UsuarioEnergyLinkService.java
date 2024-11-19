package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.UsuarioEnergyLinkDTO;
import br.com.fiap.gs.entity.UsuarioEnergyLink;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface UsuarioEnergyLinkService {
    UsuarioEnergyLink createUsuario (UsuarioEnergyLinkDTO usuario);

    UsuarioEnergyLink getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<UsuarioEnergyLink> getAllUsuario() ;

    UsuarioEnergyLink updateUsuario (Long id, UsuarioEnergyLinkDTO usuario);

    void deleteUsuario (Long id);



}
