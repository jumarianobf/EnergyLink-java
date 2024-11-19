package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.EstadoDTO;
import br.com.fiap.gs.entity.Estado;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface EstadoService {

    Estado createEstado(EstadoDTO request);

    Estado getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<Estado> getAllEstado();

    Estado updateEstado(Long id, EstadoDTO estadoDTO);

    void deleteEstado(Long id);
}
