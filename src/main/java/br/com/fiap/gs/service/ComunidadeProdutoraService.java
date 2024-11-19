package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.ComunidadeProdutoraDTO;
import br.com.fiap.gs.entity.ComunidadeProdutora;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ComunidadeProdutoraService {

    ComunidadeProdutora createComunidadeProdutora(ComunidadeProdutoraDTO comunidadeProdutoraDTO);

    ComunidadeProdutora getById(Long id) throws ChangeSetPersister.NotFoundException;

    List<ComunidadeProdutora> getAllComunidadesProdutoras();

    ComunidadeProdutora updateComunidadeProdutora(Long id, ComunidadeProdutoraDTO comunidadeProdutoraDTO);

    void deleteComunidadeProdutora(Long id);
}
