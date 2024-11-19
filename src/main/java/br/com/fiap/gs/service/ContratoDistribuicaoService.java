package br.com.fiap.gs.service;

import br.com.fiap.gs.controller.dto.ContratoDistribuicaoDTO;
import br.com.fiap.gs.entity.ContratoDistribuicao;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ContratoDistribuicaoService {

    ContratoDistribuicao createContratoDistribuicao(ContratoDistribuicaoDTO contratoDistribuicaoDTO);

    ContratoDistribuicao getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<ContratoDistribuicao> getAllContratoDistribuicao();

    ContratoDistribuicao updateContratoDistribuicao(Long id, ContratoDistribuicaoDTO contratoDistribuicaoDTO);

    void deleteContratoDistribuicao(Long id);


}
