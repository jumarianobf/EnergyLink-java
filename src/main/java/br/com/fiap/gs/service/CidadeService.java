package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.CidadeDTO;
import br.com.fiap.gs.entity.Cidade;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface CidadeService {

    Cidade createCidade(CidadeDTO request);

    Cidade getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<Cidade> getAllCidade();

    Cidade updateCidade(Long id, CidadeDTO cidadeDTO);

    void deleteCidade(Long id);
}
