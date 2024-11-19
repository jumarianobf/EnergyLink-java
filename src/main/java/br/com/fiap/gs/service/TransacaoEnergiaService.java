package br.com.fiap.gs.service;


import br.com.fiap.gs.controller.dto.TransacaoEnergiaDTO;
import br.com.fiap.gs.entity.TransacaoEnergia;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface TransacaoEnergiaService {

    TransacaoEnergia createTransacaoEnergia(TransacaoEnergiaDTO request);

    TransacaoEnergia getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<TransacaoEnergia> getAllTransacaoEnergia();

    TransacaoEnergia updateTransacaoEnergia (Long id, TransacaoEnergiaDTO transacaoEnergiaDTO);

    void deleteTransacaoEnergia (Long id);
}
