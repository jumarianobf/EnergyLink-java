package br.com.fiap.gs.service;


import br.com.fiap.gs.controller.dto.PagamentoDTO;
import br.com.fiap.gs.entity.Pagamento;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface PagamentoService {

    Pagamento createPagamento(PagamentoDTO request);

    Pagamento getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<Pagamento> getAllPagamento();

    Pagamento updatePagamento (Long id, PagamentoDTO monitoramentoProducaoDTO);

    void deletePagamento (Long id);
}
